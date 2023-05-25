package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkhttp {
    Gson gson = new Gson();
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    @Test
    public void loginSuccess() throws IOException {
        AuthRequestDTO auth = AuthRequestDTO.builder().username("pochtadl9testov@gmail.com").password("12345&Yes").build();
        RequestBody recBody = RequestBody.create(gson.toJson(auth), JSON);
        Request zapros = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(recBody)
                .build();
        Response otvet = client.newCall(zapros).execute();
        Assert.assertTrue(otvet.isSuccessful());  // ili
        Assert.assertEquals(otvet.code(), 200);
        AuthResponseDTO responseDTO = gson.fromJson(otvet.body().string(), AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());
    }
        @Test
        public void loginWrongEmail() throws IOException {
            AuthRequestDTO auth = AuthRequestDTO.builder().username("pochtadl9testovgmail.com").password("12345&Yes").build();
            RequestBody recBody = RequestBody.create(gson.toJson(auth), JSON);
            Request zapros = new Request.Builder()
                    .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                    .post(recBody)
                    .build();
            Response otvet = client.newCall(zapros).execute();
            Assert.assertFalse(otvet.isSuccessful());  // ili
            Assert.assertEquals(otvet.code(), 401);

            ErrorDTO errorDTO = gson.fromJson(otvet.body().string(), ErrorDTO.class);
            Assert.assertEquals(errorDTO.getStatus(), 401);
            Assert.assertEquals(errorDTO.getMessage(), "Login or Password incorrect");
            Assert.assertEquals(errorDTO.getPath(), "/v1/user/login/usernamepassword" );

    }
        @Test
            public void loginWrongPassword() throws IOException {
            AuthRequestDTO auth = AuthRequestDTO.builder().username("pochtadl9testov@gmail.com").password("123").build();
            RequestBody recBody = RequestBody.create(gson.toJson(auth), JSON);
            Request zapros = new Request.Builder()
                    .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                    .post(recBody)
                    .build();
            Response otvet = client.newCall(zapros).execute();
            Assert.assertFalse(otvet.isSuccessful());  // ili
            Assert.assertEquals(otvet.code(), 401);
            ErrorDTO errorDTO = gson.fromJson(otvet.body().string(), ErrorDTO.class);
            Assert.assertEquals(errorDTO.getMessage(), "Login or Password incorrect");
        }
        @Test
                public void loginUnregisteredUser() throws IOException {
                AuthRequestDTO auth = AuthRequestDTO.builder().username("omnom@gmail.com").password("12345&Yes").build();
                RequestBody recBody = RequestBody.create(gson.toJson(auth), JSON);
                Request zapros = new Request.Builder()
                        .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                        .post(recBody)
                        .build();
                Response otvet = client.newCall(zapros).execute();
            Assert.assertFalse(otvet.isSuccessful());  // ili
            Assert.assertEquals(otvet.code(), 401);
            ErrorDTO errorDTO = gson.fromJson(otvet.body().string(), ErrorDTO.class);
            Assert.assertEquals(errorDTO.getMessage(), "Login or Password incorrect");
            }
    };

