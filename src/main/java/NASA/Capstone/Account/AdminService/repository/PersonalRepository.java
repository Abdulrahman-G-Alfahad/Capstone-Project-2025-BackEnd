package NASA.Capstone.Account.AdminService.repository;

import NASA.Capstone.Account.AdminService.entity.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalRepository extends JpaRepository<PersonalEntity, Long> {
    Optional<PersonalEntity> findByCivilId(String civilId);
    Optional<PersonalEntity> findByPhoneNumber(String phoneNumber);
}
