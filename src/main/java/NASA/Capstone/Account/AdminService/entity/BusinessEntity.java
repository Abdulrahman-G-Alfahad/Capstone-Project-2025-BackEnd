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
    @Column(nullable = false)
    private List<AssociateEntity> Associates = new ArrayList<>();

    //@Column(nullable = false)
    //private List<TransactionEntity> transactions;


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
        return Associates;
    }

    public void addAssociate(AssociateEntity associate) {
        Associates.add(associate);
    }

    @Override
    public String getRole() {
        return "Business";
    }
}
