package mx.gob.tramites.seguridad.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String password;
}
