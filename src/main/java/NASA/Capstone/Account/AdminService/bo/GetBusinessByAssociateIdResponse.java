package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.BusinessEntity;

public class GetBusinessByAssociateIdResponse {

    private BusinessDTO business;

    private String message;

    public BusinessDTO getBusiness() {
        return business;
    }

    public void setBusiness(BusinessDTO business) {
        this.business = business;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
