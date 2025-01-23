package NASA.Capstone.Account.AdminService.service;

import NASA.Capstone.Account.AdminService.entity.PersonalEntity;
import NASA.Capstone.Account.AdminService.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public List<PersonalEntity> addFamilyMember(Long userId, Long familyMemberId) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow();
        user.addFamilyMember(personalRepository.findById(familyMemberId).orElseThrow());
        user = personalRepository.save(user);
        return user.getFamilyMembers();
    }

    public List<PersonalEntity> getFamilyMembers(Long userId) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow();
        return user.getFamilyMembers();
    }

    public List<PersonalEntity> removeFamilyMember(Long userId, Long familyMemberId) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow();
        user.removeFamilyMember(personalRepository.findById(familyMemberId).orElseThrow());
        user = personalRepository.save(user);
        return user.getFamilyMembers();
    }

    public PersonalEntity setLimit(Long userId, Long memberId, Double limit) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow();
        PersonalEntity member = personalRepository.findById(memberId).orElseThrow();
        if (user.getFamilyMembers().contains(member)) {
            member.setTransactionLimit(limit);
            member = personalRepository.save(member);
            return member;
        }
        else {
            throw new IllegalArgumentException("Member is not a family member");
        }

    }
}
