package mx.gob.tramites.seguridad.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import mx.gob.tramites.seguridad.enums.ERole;

@Data
@Builder
public class RoleDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
