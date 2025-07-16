package mx.gob.tramites.seguridad.service.user;

import mx.gob.tramites.seguridad.dto.UserDto;
import mx.gob.tramites.seguridad.entity.usuario.UserEntity;

public interface UserService {
    UserEntity save(UserDto userDto);
    UserEntity update(UserDto userDto);
    UserEntity updatePassword(UserDto userDto);
}
