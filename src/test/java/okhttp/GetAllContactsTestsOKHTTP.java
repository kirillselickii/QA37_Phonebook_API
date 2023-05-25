package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ErrorDTO;
import dto.GetAllContactsDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class GetAllContactsTestsOKHTTP {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicG9jaHRhZGw5dGVzdG92QGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjg1NjMzNDgwLCJpYXQiOjE2ODUwMzM0ODB9.bAKnUxuBJgQuGIVsZtK7gfD-C2vZoyrnOy6vodV82II";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient(); // ispoln9et zapros

    public void getAllCotactSuccess() throws IOException {
        Request zapros = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .get()
                .addHeader("Authorization", token)
                .build();
        Response otvet = client.newCall(zapros).execute();
        Assert.assertTrue(otvet.isSuccessful());
        Assert.assertEquals(otvet.code(), 200);
        GetAllContactsDTO kontaktiDTO = gson.fromJson(otvet.body().string(),GetAllContactsDTO.class);
        List<ContactDTO> contacts = kontaktiDTO.getContacts();
    for(ContactDTO c : contacts) {
        System.out.println(c.getId());
        System.out.println(c.getEmail());
    }
    }
    public void getAllCotactwrongToken() throws IOException {
        Request zapros = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .get()
                .addHeader("Authorization", "dsfsg")
                .build();
        Response otvet = client.newCall(zapros).execute();
        Assert.assertFalse(otvet.isSuccessful());
        Assert.assertEquals(otvet.code(), 401);
        ErrorDTO errorDTO = gson.fromJson(otvet.body().string(),ErrorDTO.class);
        Assert.assertEquals(errorDTO.getError(), "Unauthorized");
    }
}
