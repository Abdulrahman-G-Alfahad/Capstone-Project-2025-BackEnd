package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.TransactionEntity;

import java.util.List;

public class GetTransactionsByDateTimeBetweenResponse {

    private List<TransactionDTO> transactions;

    private String message;

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
