package NASA.Capstone.Account.AdminService.repository;

import NASA.Capstone.Account.AdminService.Enums.Methods;
import NASA.Capstone.Account.AdminService.Enums.Status;
import NASA.Capstone.Account.AdminService.entity.AssociateEntity;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    TransactionEntity findByDateTime(Date dateTime);
    TransactionEntity findByStatus(Status status);
    TransactionEntity findByMethod(Methods method);
    TransactionEntity findBySender(UserEntity sender);
    TransactionEntity findByReceiver(UserEntity receiver);
    TransactionEntity findByAssociateId(AssociateEntity associate);
}
