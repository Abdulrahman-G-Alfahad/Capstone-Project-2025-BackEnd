package NASA.Capstone.Account.AdminService.service;

import NASA.Capstone.Account.AdminService.bo.CreateFamilyAccountRequest;
import NASA.Capstone.Account.AdminService.entity.DependentEntity;
import NASA.Capstone.Account.AdminService.entity.PersonalEntity;
import NASA.Capstone.Account.AdminService.repository.DependentRepository;
import NASA.Capstone.Account.AdminService.repository.PersonalRepository;
import NASA.Capstone.Account.AdminService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;
    private final DependentRepository dependentRepository;
    private final UserRepository userRepository;

    public PersonalService(PersonalRepository personalRepository, DependentRepository dependentRepository, UserRepository userRepository) {
        this.personalRepository = personalRepository;
        this.dependentRepository = dependentRepository;
        this.userRepository = userRepository;
    }

    public DependentEntity addFamilyMember(Long userId, CreateFamilyAccountRequest request) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        DependentEntity dependent = new DependentEntity();
        dependent.setFullName(request.getFullName());
        dependent.setFaceId(request.getFaceId());
        dependent.setWalletBalance(0.0);
        dependent.setGuardian(user);
        dependent.setTransactions(new ArrayList<>());

        DependentEntity savedDependent = dependentRepository.save(dependent);

        user.addFamilyMember(savedDependent);
        personalRepository.save(user);

        return savedDependent;
    }

    public List<DependentEntity> getFamilyMembers(Long userId) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow();
        return user.getFamilyMembers();
    }

    public List<DependentEntity> removeFamilyMember(Long userId, Long familyMemberId) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow();
        DependentEntity member = dependentRepository.findById(familyMemberId).orElseThrow();
        user.removeFamilyMember(member);
        dependentRepository.delete(member);
        user = personalRepository.save(user);
        return user.getFamilyMembers();
    }

    public DependentEntity setLimit(Long userId, Long memberId, Double limit) {
        PersonalEntity user = personalRepository.findById(userId).orElseThrow();
        DependentEntity member = dependentRepository.findById(memberId).orElseThrow();
        if (user.getFamilyMembers().contains(member)) {
            member.setWalletBalance(limit);
            member = dependentRepository.save(member);
            return member;
        }
        else {
            throw new IllegalArgumentException("Member is not a family member");
        }

    }
}
