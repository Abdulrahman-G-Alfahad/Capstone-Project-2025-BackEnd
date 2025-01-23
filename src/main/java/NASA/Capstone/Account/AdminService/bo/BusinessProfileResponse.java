package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.BusinessEntity;

public class BusinessProfileResponse {

    private BusinessEntity businessEntity;

    private String message;

    public BusinessEntity getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(BusinessEntity businessEntity) {
        this.businessEntity = businessEntity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
