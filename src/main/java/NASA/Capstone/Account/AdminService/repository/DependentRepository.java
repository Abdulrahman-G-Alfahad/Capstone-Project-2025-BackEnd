package NASA.Capstone.Account.AdminService.repository;

import NASA.Capstone.Account.AdminService.entity.DependentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DependentRepository extends JpaRepository<DependentEntity, Long> {

    Optional<DependentEntity> findByFaceId(String civilId);
}
