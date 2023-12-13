package nuricanozturk.dev.data.usermanagement.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import nuricanozturk.dev.data.usermanagement.dto.UserLoginDTO;
import nuricanozturk.dev.data.usermanagement.dto.UserSaveDTO;
import nuricanozturk.dev.data.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "Authorization")
public class AuthenticationController
{
    private final UserService m_userService;

    public AuthenticationController(UserService userService)
    {
        m_userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(UserSaveDTO userSaveDTO)
    {
        return ResponseEntity.ok(m_userService.createUser(userSaveDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(UserLoginDTO userLoginDTO)
    {
        return ResponseEntity.ok(m_userService.login(userLoginDTO));
    }
}
