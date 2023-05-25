package dto;

import java.awt.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString


public class GetAllContactsDTO {
    private List<ContactDTO> contacts;

}
