package NASA.Capstone.Account.AdminService.repository;

import NASA.Capstone.Account.AdminService.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {
    BusinessEntity findByUsername(String username);
    BusinessEntity findByName(String businessName);
    BusinessEntity findByBusinessLicenseId(String businesslicenseId);
}
