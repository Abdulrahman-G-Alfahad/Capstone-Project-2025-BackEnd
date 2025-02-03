package NASA.Capstone.Account.AdminService.repository;

import NASA.Capstone.Account.AdminService.entity.DependentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependentRepository extends JpaRepository<DependentEntity, Long> {
}
