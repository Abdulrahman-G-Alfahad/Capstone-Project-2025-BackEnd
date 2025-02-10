package NASA.Capstone.Account.AdminService.service;

import NASA.Capstone.Account.AdminService.bo.BusinessDTO;
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

    private final AssociateRepository associateRepository;

    private final AuthenticationService authenticationService;

    public BusinessService(BusinessRepository businessRepository, AuthenticationService authenticationService, AssociateRepository associateRepository) {
        this.businessRepository = businessRepository;
        this.authenticationService = authenticationService;
        this.associateRepository = associateRepository;
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

    public BusinessDTO getBusiness(Long associateId) {
        AssociateEntity associate = associateRepository.findById(associateId).orElseThrow();
        return fillBusinessDTO(associate.getBusiness());
    }

    public BusinessDTO fillBusinessDTO(BusinessEntity businessEntity) {
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setId(businessEntity.getId().toString());
        businessDTO.setName(businessEntity.getName());
        businessDTO.setAddress(businessEntity.getAddress());
        return businessDTO;
    }

    public AssociateEntity getAssociate(Long associateId) {
        return associateRepository.findById(associateId).orElseThrow();
    }
}
