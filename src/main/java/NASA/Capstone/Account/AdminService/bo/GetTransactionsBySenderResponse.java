package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.TransactionEntity;

import java.util.List;

public class GetTransactionsBySenderResponse {

    private List<TransactionEntity> transactions;
    private String message;

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
