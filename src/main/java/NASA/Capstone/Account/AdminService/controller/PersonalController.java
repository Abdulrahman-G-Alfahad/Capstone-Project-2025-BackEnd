package NASA.Capstone.Account.AdminService.controller;


import NASA.Capstone.Account.AdminService.bo.CreateFamilyAccountRequest;
import NASA.Capstone.Account.AdminService.bo.FamilyMemberResponse;
import NASA.Capstone.Account.AdminService.bo.GetProfileResponse;
import NASA.Capstone.Account.AdminService.bo.SetLimitResponse;
import NASA.Capstone.Account.AdminService.entity.DependentEntity;
import NASA.Capstone.Account.AdminService.repository.DependentRepository;
import NASA.Capstone.Account.AdminService.repository.PersonalRepository;
import NASA.Capstone.Account.AdminService.service.PersonalService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/personal")
@RestController
public class PersonalController {
    private final PersonalService personalService;

    private final PersonalRepository personalRepository;
    private final DependentRepository dependentRepository;

    public PersonalController(PersonalService personalService, PersonalRepository personalRepository, DependentRepository dependentRepository) {
        this.personalService = personalService;
        this.personalRepository = personalRepository;
        this.dependentRepository = dependentRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetProfileResponse> getProfile(@PathVariable("userId") Long userId) {
        GetProfileResponse response = new GetProfileResponse();
        try{
            System.out.println(personalRepository.findById(userId));
            personalRepository.findById(userId);
        } catch (Exception e) {
            response.setMessage("User does not exist");
            return ResponseEntity.badRequest().body(response);
        }
        response.setUser(personalRepository.findById(userId).get());
        response.setMessage("User retrieved successfully");
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addFamilyMember/{userId}")
    public ResponseEntity<FamilyMemberResponse> addFamilyMember(@PathVariable("userId") Long userId, @RequestBody CreateFamilyAccountRequest request) {
        FamilyMemberResponse response = new FamilyMemberResponse();
        try {
            DependentEntity dependent = personalService.addFamilyMember(userId, request);
            response.setFamilyMembers(List.of(dependent));
            response.setMessage("Family member added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/getFamilyMembers/{userId}")
    public ResponseEntity<FamilyMemberResponse> getFamilyMembers(@PathVariable("userId") Long userId) {
        FamilyMemberResponse response = new FamilyMemberResponse();
        try{
            personalRepository.findById(userId).orElseThrow();
        } catch (Exception e) {
            response.setMessage("User does not exist");
            return ResponseEntity.badRequest().body(response);
        }
        response.setFamilyMembers(personalService.getFamilyMembers(userId));
        response.setMessage("Family members retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/removeFamilyMember/{userId}/{familyMemberId}")
    public ResponseEntity<FamilyMemberResponse> removeFamilyMember(@PathVariable("userId") Long userId, @PathVariable("familyMemberId") Long familyMemberId) {
        FamilyMemberResponse response = new FamilyMemberResponse();
        try{
            personalRepository.findById(userId).orElseThrow();
            personalRepository.findById(familyMemberId).orElseThrow();
        } catch (Exception e) {
            response.setMessage("User or family member does not exist");
            return ResponseEntity.badRequest().body(response);
        }
        response.setFamilyMembers(personalService.removeFamilyMember(userId, familyMemberId));
        response.setMessage("Family member removed successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/setLimit/{userId}/{memberId}/{limit}")
    public ResponseEntity<SetLimitResponse> setLimit(@PathVariable("userId") Long userId, @PathVariable("memberId") Long memberId, @PathVariable("limit") Double limit) {
        SetLimitResponse response = new SetLimitResponse();
        try{
            personalRepository.findById(userId).orElseThrow();
            personalRepository.findById(memberId).orElseThrow();
        } catch (Exception e) {
            response.setMessage("User or family member does not exist");
            return ResponseEntity.badRequest().body(response);
        }
        response.setFamilyMember(personalService.setLimit(userId, memberId, limit));
        response.setMessage("Limit set successfully");
        return ResponseEntity.ok(response);
    }
}
