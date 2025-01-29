package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.Enums.Methods;

public class MakeTransferRequest {

    private Double amount;

    private String sender;

    private String receiver;

    private Methods method;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }
}
