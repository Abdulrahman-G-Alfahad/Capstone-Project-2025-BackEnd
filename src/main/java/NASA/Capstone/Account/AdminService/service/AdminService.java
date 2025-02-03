package NASA.Capstone.Account.AdminService.service;


import NASA.Capstone.Account.AdminService.bo.Register.Request.RegisterBusinessRequest;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

}
