package NASA.Capstone.Account.AdminService.service;

import NASA.Capstone.Account.AdminService.Enums.Methods;
import NASA.Capstone.Account.AdminService.Enums.Status;
import NASA.Capstone.Account.AdminService.bo.MakeBusinessTransactionRequest;
import NASA.Capstone.Account.AdminService.bo.MakeTransferRequest;
import NASA.Capstone.Account.AdminService.entity.AssociateEntity;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.entity.UserEntity;
import NASA.Capstone.Account.AdminService.repository.TransactionRepository;
import NASA.Capstone.Account.AdminService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    public TransactionService (TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public TransactionEntity makeBusinessTransaction(MakeBusinessTransactionRequest request){
        LocalDateTime datetime = LocalDateTime.now();
        TransactionEntity transaction = new TransactionEntity();
        UserEntity associate = userRepository.findById(Long.valueOf(request.getAssociateId())).orElseThrow();
        UserEntity sender = userRepository.findById(Long.valueOf(request.getSenderId())).orElseThrow();
        UserEntity receiver = userRepository.findById(Long.valueOf(request.getReceiverId())).orElseThrow();
        transaction.setAmount(request.getAmount());
        transaction.setAssociateId((AssociateEntity) associate);
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setMethod(request.getMethod());
        transaction.setStatus(Status.PENDING);
        transaction.setDateTime(datetime);
        return transactionRepository.save(transaction);
    }

    public TransactionEntity getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow();
    }

    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public TransactionEntity makeTransfer(MakeTransferRequest request){
        LocalDateTime dateTime = LocalDateTime.now();
        TransactionEntity transaction = new TransactionEntity();
        UserEntity sender = userRepository.findById(Long.valueOf(request.getSender())).orElseThrow();
        UserEntity receiver = userRepository.findById(Long.valueOf(request.getReceiver())).orElseThrow();
        transaction.setAmount(request.getAmount());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setMethod(request.getMethod());
        transaction.setStatus(Status.PENDING);
        transaction.setDateTime(dateTime);
        return transactionRepository.save(transaction);
    }

    public List<TransactionEntity> getTransactionsBySender(Long senderId) {
        UserEntity sender = userRepository.findById(senderId).orElseThrow();
        return transactionRepository.findBySender(sender);
    }

    public List<TransactionEntity> getTransactionsByReceiver(Long receiverId) {
        UserEntity receiver = userRepository.findById(receiverId).orElseThrow();
        return transactionRepository.findByReceiver(receiver);
    }

    public List<TransactionEntity> getTransactionsByDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByDateTimeBetween(start, end);
    }

    public List<TransactionEntity> getTransactionsByAssociate(Long associateId) {
        UserEntity associate = userRepository.findById(associateId).orElseThrow();
        return transactionRepository.findByAssociateId((AssociateEntity) associate);
    }

    public List<TransactionEntity> getTransactionsByStatus(Status status) {
        return transactionRepository.findByStatus(status);
    }

    public List<TransactionEntity> getTransactionsByMethod(Methods method) {
        return transactionRepository.findByMethod(method);
    }

    public TransactionEntity updateTransactionStatus(Long transactionId, Status status) {
        TransactionEntity transaction = transactionRepository.findById(transactionId).orElseThrow();
        transaction.setStatus(status);
        return transactionRepository.save(transaction);
    }
}
