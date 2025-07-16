package mx.gob.tramites.seguridad.service.user.impl;

import lombok.AllArgsConstructor;
import mx.gob.tramites.seguridad.dto.UserDto;
import mx.gob.tramites.seguridad.entity.usuario.RoleEntity;
import mx.gob.tramites.seguridad.entity.usuario.UserEntity;
import mx.gob.tramites.seguridad.enums.ERole;
import mx.gob.tramites.seguridad.repository.UserRepository;
import mx.gob.tramites.seguridad.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserDto userDto) {

        Set<RoleEntity> roles = null;
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            roles = userDto.getRoles().stream()
                    .map(role -> RoleEntity.builder()
                            .name(ERole.valueOf(role.getName().name())).build())
                    .collect(Collectors.toSet());
        }

        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nombre(userDto.getNombre())
                .apellidoPaterno(userDto.getApellidoPaterno())
                .apellidoMaterno(userDto.getApellidoMaterno())
                .roles(roles)
                .build();

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserDto userDto) {

        return null;
    }

    @Override
    public UserEntity updatePassword(UserDto userDto) {

        Optional<UserEntity> optionalUserEntity = getUser(userDto.getId());

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(userEntity);
            return userEntity;
        }

        return null;
    }

    private Optional<UserEntity> getUser(Long id) {
        return userRepository.findById(id);
    }


}
