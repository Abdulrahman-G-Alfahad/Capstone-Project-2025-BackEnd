package NASA.Capstone.Account.AdminService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DependentEntity extends Accounts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private double walletBalance;
    private String faceId;

    @ManyToOne
    @JoinColumn(name = "guardian_id")
    @JsonIgnore
    private PersonalEntity guardian;

    @OneToMany
    @JoinColumn(name = "dependant_id")
    private List<TransactionEntity> transactions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public PersonalEntity getGuardian() {
        return guardian;
    }

    public void setGuardian(PersonalEntity guardian) {
        this.guardian = guardian;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(TransactionEntity transaction) {
        transactions.add(transaction);
    }
}
