package NASA.Capstone.Account.AdminService.repository;

import NASA.Capstone.Account.AdminService.Enums.Methods;
import NASA.Capstone.Account.AdminService.Enums.Status;
import NASA.Capstone.Account.AdminService.entity.AssociateEntity;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    TransactionEntity findByDateTime(LocalDateTime dateTime);
    List<TransactionEntity> findByStatus(Status status);
    List<TransactionEntity> findByMethod(Methods method);
    List<TransactionEntity> findBySender(UserEntity sender);
    List<TransactionEntity> findByReceiver(UserEntity receiver);
    List<TransactionEntity> findByAssociateId(AssociateEntity associate);
    List<TransactionEntity> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

}
