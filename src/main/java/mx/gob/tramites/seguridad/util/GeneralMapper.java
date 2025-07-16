package mx.gob.tramites.seguridad.util;

import mx.gob.tramites.seguridad.dto.UserDto;
import mx.gob.tramites.seguridad.entity.usuario.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GeneralMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "roles", target = "roles")
    UserEntity mapperUserEntity(UserDto userDto);

}
