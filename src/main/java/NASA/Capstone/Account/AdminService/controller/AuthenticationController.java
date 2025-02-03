package NASA.Capstone.Account.AdminService.controller;

import NASA.Capstone.Account.AdminService.bo.Login.Request.LoginRequest;
import NASA.Capstone.Account.AdminService.bo.Login.Response.LoginResponse;
import NASA.Capstone.Account.AdminService.bo.Register.Request.RegisterRequest;
import NASA.Capstone.Account.AdminService.bo.Register.Response.RegisterResponse;
import NASA.Capstone.Account.AdminService.entity.UserEntity;
import NASA.Capstone.Account.AdminService.service.AuthenticationService;
import NASA.Capstone.Account.AdminService.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> signup(@RequestBody RegisterRequest request) {
        UserEntity registeredUser = authenticationService.Register(request, request.getClass().getSimpleName());
        String jwtToken = authenticationService.generateTokenWithAccountType(registeredUser);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setToken(jwtToken);
        registerResponse.setUser(registeredUser);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();

        try {
            UserEntity user = authenticationService.authenticate(request);
            String jwtToken = authenticationService.generateTokenWithAccountType(user);

            loginResponse.setToken(jwtToken);
        } catch (Exception e) {
            loginResponse.setMessage(e.getMessage());
            return ResponseEntity.status(401).body(loginResponse);
        }


        return ResponseEntity.ok(loginResponse);
    }
}