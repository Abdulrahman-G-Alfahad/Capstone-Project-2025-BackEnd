package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.TransactionEntity;

public class ChangeTransactionStatusResponse {

    private TransactionDTO transaction;

    private String message;

    public TransactionDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDTO transaction) {
        this.transaction = transaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
