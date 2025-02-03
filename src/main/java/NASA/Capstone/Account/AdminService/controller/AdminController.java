package NASA.Capstone.Account.AdminService.controller;


import NASA.Capstone.Account.AdminService.bo.BusinessProfileResponse;
import NASA.Capstone.Account.AdminService.bo.Register.Request.RegisterBusinessRequest;
import NASA.Capstone.Account.AdminService.entity.BusinessEntity;
import NASA.Capstone.Account.AdminService.entity.UserEntity;
import NASA.Capstone.Account.AdminService.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AuthenticationService authenticationService;

    public AdminController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register-business")
    public ResponseEntity<BusinessEntity> registerBusiness(@RequestBody RegisterBusinessRequest registerBusinessRequest) {
//        try {
            UserEntity registeredUser= authenticationService.Register(registerBusinessRequest, "RegisterBusinessRequest");

            BusinessEntity business = (BusinessEntity) registeredUser;
            return ResponseEntity.status(201).body(business);
//        }
//        catch (Exception e) {
//            return ResponseEntity.status(500).body("Error registering business account.");
//        }
    }
}
