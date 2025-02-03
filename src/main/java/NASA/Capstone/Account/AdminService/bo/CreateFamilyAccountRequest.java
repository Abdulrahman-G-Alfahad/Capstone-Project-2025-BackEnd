package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.PersonalEntity;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;

import java.util.List;

public class CreateFamilyAccountRequest {

    private String fullName;
    private double walletBalance;
    private String faceId;
    private PersonalEntity user;
    private List<TransactionEntity> transactions;

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

    public PersonalEntity getUser() {
        return user;
    }

    public void setUser(PersonalEntity user) {
        this.user = user;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}
