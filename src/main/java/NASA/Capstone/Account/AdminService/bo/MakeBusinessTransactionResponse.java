package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.TransactionEntity;

public class MakeBusinessTransactionResponse {

    private TransactionEntity transaction;

    private String message;

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
