package mx.gob.tramites.seguridad.controller;

import lombok.AllArgsConstructor;
import mx.gob.tramites.seguridad.dto.UserDto;
import mx.gob.tramites.seguridad.dto.UserResponse;
import mx.gob.tramites.seguridad.entity.usuario.UserEntity;
import mx.gob.tramites.seguridad.security.jwt.JwtUtils;
import mx.gob.tramites.seguridad.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping(value = "/login")
    public String login() {
        return "auth login";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {

        UserEntity userEntity = userService.save(userDto);
        UserResponse userResponse = UserResponse.builder()
                .token(jwtUtils.generateAccesToken(userEntity.getUsername()))
                .username(userEntity.getUsername())
                .message("Autenticación corrects")
                .build();
        return ResponseEntity.ok(userResponse);

    }

    @PutMapping("changePassword")
    public ResponseEntity<?> changePassword(@RequestBody UserDto userDto) {

        UserEntity userEntity = userService.updatePassword(userDto);
        UserResponse userResponse = UserResponse.builder()
                .token(jwtUtils.generateAccesToken(userEntity.getUsername()))
                .username(userEntity.getUsername())
                .message("Autenticación corrects")
                .build();

        return ResponseEntity.ok(userResponse);

    }

}
