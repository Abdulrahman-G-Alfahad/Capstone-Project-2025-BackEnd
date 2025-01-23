package NASA.Capstone.Account.AdminService.service;

import NASA.Capstone.Account.AdminService.bo.Register.Request.RegisterAssociateRequest;
import NASA.Capstone.Account.AdminService.entity.AssociateEntity;
import NASA.Capstone.Account.AdminService.entity.BusinessEntity;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.repository.AssociateRepository;
import NASA.Capstone.Account.AdminService.repository.BusinessRepository;
import NASA.Capstone.Account.AdminService.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;



    private final AuthenticationService authenticationService;

    public BusinessService(BusinessRepository businessRepository, AuthenticationService authenticationService) {
        this.businessRepository = businessRepository;
        this.authenticationService = authenticationService;
    }

    public List<TransactionEntity> getTransactions(Long businessId) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow();
        return business.getTransactions();
    }

    public List<AssociateEntity> getAssociates(Long businessId) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow();
        return business.getAssociates();
    }

    public List<AssociateEntity> createAssociate(Long businessId, RegisterAssociateRequest associate) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow();
        AssociateEntity newAssociate = (AssociateEntity) authenticationService.Register(associate, associate.getClass().getSimpleName());
        business.addAssociate(newAssociate);
        business = businessRepository.save(business);
        return business.getAssociates();
    }
}
