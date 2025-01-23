package NASA.Capstone.Account.AdminService.entity;

import NASA.Capstone.Account.AdminService.Enums.Roles;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonalEntity extends UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String civilId;

    @Column(nullable = false)
    private Double walletBalance = 0.0;

    @Column(nullable = true)
    private String faceID;

    @OneToMany
    @JoinColumn(name = "business_id")
    private List<PersonalEntity> familyMembers = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "business_id")
    private List<TransactionEntity> transactionHistory = new ArrayList<>();

    @Column(nullable = true)
    private String bankAccountNumber;

    @Enumerated(EnumType.STRING)
    private Roles type;

    @Column(nullable = true)
    private Double transactionLimit;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public Double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getFaceID() {
        return faceID;
    }

    public void setFaceID(String faceID) {
        this.faceID = faceID;
    }

    public List<PersonalEntity> getFamilyMembers() {
        return familyMembers;
    }

    public List<PersonalEntity> addFamilyMember(PersonalEntity familyMember) {
        familyMembers.add(familyMember);
        return familyMembers;
    }

    public List<PersonalEntity> removeFamilyMember(PersonalEntity familyMember) {
        familyMembers.remove(familyMember);
        return familyMembers;
    }

    public List<TransactionEntity> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(TransactionEntity transaction) {
        transactionHistory.add(transaction);
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Roles getType() {
        return type;
    }

    public void setType(Roles type) {
        this.type = type;
    }

    public Double getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(Double transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    @Override
    public String getRole() {
        return "Personal";
    }
}
