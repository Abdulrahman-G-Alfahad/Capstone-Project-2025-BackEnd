package NASA.Capstone.Account.AdminService.controller;

import NASA.Capstone.Account.AdminService.bo.BusinessAssociateListResponse;
import NASA.Capstone.Account.AdminService.bo.BusinessProfileResponse;
import NASA.Capstone.Account.AdminService.bo.BusinessTransactionListResponse;
import NASA.Capstone.Account.AdminService.bo.Register.Request.RegisterAssociateRequest;
import NASA.Capstone.Account.AdminService.bo.TransactionDTO;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.repository.BusinessRepository;
import NASA.Capstone.Account.AdminService.service.BusinessService;
import NASA.Capstone.Account.AdminService.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/business")
@RestController
public class BusinessController {
    private final BusinessService businessService;
    private final BusinessRepository businessRepository;
    private final TransactionService transactionService;

    public BusinessController(BusinessService businessService, BusinessRepository businessRepository, TransactionService transactionService) {
        this.businessService = businessService;
        this.businessRepository = businessRepository;
        this.transactionService = transactionService;
    }

    @GetMapping("/profile/{businessId}")
    public ResponseEntity<BusinessProfileResponse> getBusinessProfile(@PathVariable("businessId") Long businessId) {
        try {
            businessRepository.findById(businessId).orElseThrow();
        } catch (Exception e) {
            BusinessProfileResponse response = new BusinessProfileResponse();
            response.setMessage("Business not found");
            return ResponseEntity.status(404).body(response);
        }
        BusinessProfileResponse response = new BusinessProfileResponse();
        response.setBusinessEntity(businessRepository.findById(businessId).orElseThrow());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile/{businessId}/transactions")
    public ResponseEntity<BusinessTransactionListResponse> getBusinessTransactions(@PathVariable("businessId") Long businessId) {
        try {
            businessRepository.findById(businessId).orElseThrow();
        } catch (Exception e) {
            BusinessTransactionListResponse response = new BusinessTransactionListResponse();
            response.setMessage("Business not found");
            return ResponseEntity.status(404).body(response);
        }
        BusinessTransactionListResponse response = new BusinessTransactionListResponse();
        List<TransactionDTO> transactions = new ArrayList<>();
        for (TransactionEntity transaction : businessService.getTransactions(businessId)) {
            transactions.add(transactionService.fillTransactionDto(transaction));
        }
        response.setTransactionList(transactions);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile/{businessId}/associates")
    public ResponseEntity<BusinessAssociateListResponse> getBusinessAssociates(@PathVariable("businessId") Long businessId) {
        try {
            businessRepository.findById(businessId).orElseThrow();
        } catch (Exception e) {
            BusinessAssociateListResponse response = new BusinessAssociateListResponse();
            response.setMessage("Business not found");
            return ResponseEntity.status(404).body(response);
        }
        BusinessAssociateListResponse response = new BusinessAssociateListResponse();
        response.setAssociateList(businessService.getAssociates(businessId));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profile/{businessId}/associate")
    public ResponseEntity<BusinessAssociateListResponse> createAssociate(@PathVariable("businessId") Long businessId, @RequestBody RegisterAssociateRequest associate) {
        BusinessAssociateListResponse response = new BusinessAssociateListResponse();
        try {
            response.setAssociateList(businessService.createAssociate(businessId, associate));
            response.setMessage("Associate created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }
}
