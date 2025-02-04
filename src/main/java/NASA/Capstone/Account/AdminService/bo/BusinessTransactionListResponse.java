package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.TransactionEntity;

import java.util.List;

public class BusinessTransactionListResponse {

    private List<TransactionDTO> transactionList;

    private String message;

    public List<TransactionDTO> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionDTO> transactionList) {
        this.transactionList = transactionList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
