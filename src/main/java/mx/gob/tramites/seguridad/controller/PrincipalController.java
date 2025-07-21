package mx.gob.tramites.seguridad.controller;

import lombok.AllArgsConstructor;
import mx.gob.tramites.seguridad.dto.UserDto;
import mx.gob.tramites.seguridad.entity.usuario.RoleEntity;
import mx.gob.tramites.seguridad.entity.usuario.UserEntity;
import mx.gob.tramites.seguridad.enums.ERole;
import mx.gob.tramites.seguridad.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/principal")
@AllArgsConstructor
public class PrincipalController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @GetMapping("hello")
    public String hello() {
        return "Hello world not secured jenkins";
    }

    @GetMapping("helloSecured")
    public String helloSecured() {
        return "Hello worl secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> creaUser(@RequestBody UserDto userDto) {

        Set<RoleEntity> roles = userDto.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role.getName().name())).build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @DeleteMapping("deleteUser")
    public String deleteUser(@RequestParam String id) {
        userRepository.deleteById(Long.parseLong(id));
        return "Se ha borrado el user con id ".concat(id);
    }

}
