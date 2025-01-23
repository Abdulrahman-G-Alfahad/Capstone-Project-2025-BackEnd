package NASA.Capstone.Account.AdminService.repository;

import NASA.Capstone.Account.AdminService.entity.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {
    AssociateEntity findByUsername(String username);
}
