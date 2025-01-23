package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.PersonalEntity;

public class SetLimitResponse {

    private PersonalEntity familyMember;
    private String message;

    public PersonalEntity getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(PersonalEntity familyMember) {
        this.familyMember = familyMember;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
