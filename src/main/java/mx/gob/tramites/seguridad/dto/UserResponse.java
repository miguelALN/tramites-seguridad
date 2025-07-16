package mx.gob.tramites.seguridad.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String message;
    private String username;
    private String token;
}
