package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.UserEntity;

public class GetProfileResponse {

    private String message;

    private UserEntity user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
