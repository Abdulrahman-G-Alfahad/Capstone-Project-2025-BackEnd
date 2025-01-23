package NASA.Capstone.Account.AdminService.bo;

import NASA.Capstone.Account.AdminService.entity.PersonalEntity;

import java.util.List;

public class FamilyMemberResponse {

    private List<PersonalEntity> familyMembers;

    private String message;

    public List<PersonalEntity> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<PersonalEntity> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
