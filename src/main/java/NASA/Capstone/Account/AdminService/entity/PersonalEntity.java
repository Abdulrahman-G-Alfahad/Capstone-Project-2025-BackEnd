package NASA.Capstone.Account.AdminService.entity;

import NASA.Capstone.Account.AdminService.Enums.Roles;
import NASA.Capstone.Account.AdminService.bo.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

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
    @Size(min = 12, max = 12, message = "Civil ID Must be exactly 12 digits.")
    private String civilId;

    @Column(nullable = false)
    private Double walletBalance = 0.0;

    @Column(nullable = true)
    private String faceID;

    @OneToMany
    @JoinColumn(name = "member_id")
    @JsonIgnoreProperties("familyMembers")
    private List<DependentEntity> familyMembers = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "personal_id")
    //@JsonIgnoreProperties("dateTime")
    private List<TransactionEntity> transactionHistory = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "temp_id")
    private List<TransactionEntity> tempTransactions = new ArrayList<>();

    @Column(nullable = true)
    private String bankAccountNumber;


    @Column(nullable = false)
    private Boolean privacy = true;

    @Column(nullable = true)
    private Double transactionLimit;

    @Column(nullable = true)
    private String pin;

    @Override
    public Long getId() {
        return id;
    }

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

    public List<DependentEntity> getFamilyMembers() {
        return familyMembers;
    }

    public void addFamilyMember(DependentEntity familyMember) {
        familyMembers.add(familyMember);
//        return familyMembers;
    }

    public void removeFamilyMember(DependentEntity familyMember) {
        familyMembers.remove(familyMember);
//        return familyMembers;
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

    public Boolean getPrivate() {
        return privacy;
    }

    public void changePrivacy() {
        privacy = !privacy;
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
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<TransactionEntity> getTempTransactions() {
        return tempTransactions;
    }

    public void setTempTransactions(List<TransactionEntity> tempTransactions) {
        this.tempTransactions = tempTransactions;
    }

    public void addTempTransaction(TransactionEntity transaction) {
        tempTransactions.add(transaction);
    }

    public void removeTempTransaction(TransactionEntity transaction) {
        this.tempTransactions.remove(transaction);
    }

    @Override
    public String toString() {
        return "PersonalEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", civilId='" + civilId + '\'' +
                ", walletBalance=" + walletBalance +
                ", faceID='" + faceID + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", privacy=" + privacy +
                ", transactionLimit=" + transactionLimit +
                '}';
    }
}
