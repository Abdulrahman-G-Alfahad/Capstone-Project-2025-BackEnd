package NASA.Capstone.Account.AdminService.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BusinessEntity extends UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String businessLicenseId;

    @Column(nullable = false)
    private String bankAccountNumber;

    @OneToMany
    @JoinColumn(name = "business_id")
    private List<AssociateEntity> associates = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private List<TransactionEntity> transactions = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessLicenseId() {
        return businessLicenseId;
    }

    public void setBusinessLicenseId(String businessLicenseId) {
        this.businessLicenseId = businessLicenseId;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public List<AssociateEntity> getAssociates() {
        return associates;
    }

    public void addAssociate(AssociateEntity associate) {
        associates.add(associate);
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void addTransaction(TransactionEntity transaction) {
        transactions.add(transaction);
    }

    @Override
    public String getRole() {
        return "Business";
    }
}
