package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.PersonalEntity;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;

import java.util.List;

public class CreateFamilyAccountRequest {

    private String fullName;
    private double walletBalance;
    private String faceId;

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

}
