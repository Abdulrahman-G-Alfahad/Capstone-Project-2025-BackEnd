package NASA.Capstone.Account.AdminService.service;

import NASA.Capstone.Account.AdminService.Enums.Methods;
import NASA.Capstone.Account.AdminService.Enums.Status;
import NASA.Capstone.Account.AdminService.bo.*;
import NASA.Capstone.Account.AdminService.entity.*;
import NASA.Capstone.Account.AdminService.repository.DependentRepository;
import NASA.Capstone.Account.AdminService.repository.PersonalRepository;
import NASA.Capstone.Account.AdminService.repository.TransactionRepository;
import NASA.Capstone.Account.AdminService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    private final DependentRepository dependentRepository;

    private final PersonalRepository personalRepository;

    private final PasswordEncoder passwordEncoder;

    public TransactionService (TransactionRepository transactionRepository, UserRepository userRepository, DependentRepository dependentRepository, PersonalRepository personalRepository, PasswordEncoder passwordEncoder) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.dependentRepository = dependentRepository;
        this.personalRepository = personalRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TransactionDTO fillTransactionDto(TransactionEntity transaction){
        Accounts sender = transaction.getSender();
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTransactionDate(transaction.getDateTime().toString());
        transactionDTO.setMethod(transaction.getMethod());

        if (transaction.getReceiver() instanceof BusinessEntity) {
            transactionDTO.setReceiverId(transaction.getReceiver().getId());
        } else {
            transactionDTO.setReceiverId(0L);
        }

        transactionDTO.setSenderId(sender.getId());
        transactionDTO.setStatus(transaction.getStatus());
        transactionDTO.setTransactionId(transaction.getId());
        if (transaction.getAssociateId() == null){
            transactionDTO.setAssociateId(0L);
        }
        else {
            transactionDTO.setAssociateId(transaction.getAssociateId().getId());
        }
//        System.out.println(transactionDTO);
        return transactionDTO;
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
        transaction.setDateTime(datetime.toString());
        transaction = transactionRepository.save(transaction);
        AssociateEntity associateEntity = (AssociateEntity) associate;
        PersonalEntity senderEntity = (PersonalEntity) sender;
        BusinessEntity receiverEntity = (BusinessEntity) receiver;
        associateEntity.addTransaction(transaction);
        senderEntity.addTransaction(transaction);
        receiverEntity.addTransaction(transaction);
        userRepository.save(associateEntity);
        userRepository.save(senderEntity);
        userRepository.save(receiverEntity);
        return transaction;
    }

    public TransactionEntity makeBusinessTransactionWithFaceId(MakeFaceIdTransactionRequest request) throws Exception {

        Optional<PersonalEntity> personalEntity = personalRepository.findByFaceID(request.getFaceId());
        Optional<DependentEntity> dependentEntity = dependentRepository.findByFaceId(request.getFaceId());

        PersonalEntity personal;

        if (personalEntity.isEmpty() && dependentEntity.isEmpty()) {
            throw new Exception("FaceId not found in either repository");
        }

        AssociateEntity associate = (AssociateEntity) userRepository.findById(Long.valueOf(request.getAssociateId())).orElseThrow();

        BusinessEntity business = (BusinessEntity) userRepository.findById(Long.valueOf(request.getReceiverId())).orElseThrow();

        LocalDateTime datetime = LocalDateTime.now();
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(request.getAmount());
        transaction.setAssociateId(associate);

        if (personalEntity.isPresent()) {
            personal = personalEntity.get();
            Double newBalance = personal.getWalletBalance() - request.getAmount();
            personal.setWalletBalance(newBalance);
            personal = personalRepository.save(personal);
            transaction.setSender(personal);
            transaction.setReceiver(business);
//            if (personal.getPin() == null || !passwordEncoder.matches(request.getPin(), personal.getPin())) {
//                throw new Exception("Incorrect pin");
//            }
        } else {
            personal = dependentEntity.get().getGuardian();
            Double newBalance = personal.getWalletBalance() - request.getAmount();
            personal.setWalletBalance(newBalance);
            personal = personalRepository.save(personal);
            transaction.setSender(personal);
            transaction.setReceiver(business);
//            if (personal.getPin() == null || !passwordEncoder.matches(request.getPin(), personal.getPin())) {
//                throw new Exception("Incorrect pin");
//            }
        }

        transaction.setMethod(Methods.FACEID);
        transaction.setStatus(Status.PENDING);
        transaction.setDateTime(datetime.toString());
        transaction = transactionRepository.save(transaction);
        if (personalEntity.isPresent()) {
            personal.addTransaction(transaction);
            userRepository.save(personal);
        } else {
            dependentEntity.get().addTransaction(transaction);
            dependentRepository.save(dependentEntity.get());
        }
        return transaction;
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
        transaction.setDateTime(dateTime.toString());
        transaction = transactionRepository.save(transaction);
        PersonalEntity senderEntity = (PersonalEntity) sender;
        PersonalEntity receiverEntity = (PersonalEntity) receiver;
        senderEntity.addTransaction(transaction);
        receiverEntity.addTransaction(transaction);
        userRepository.save(senderEntity);
        userRepository.save(receiverEntity);
        return transaction;
    }

    public List<TransactionEntity> getTransactionsBySender(Long senderId) {
        UserEntity sender = userRepository.findById(senderId).orElseThrow();
        return transactionRepository.findBySender(sender);
    }

    public List<TransactionEntity> getTransactionsByReceiver(Long receiverId) {
        UserEntity receiver = userRepository.findById(receiverId).orElseThrow();
        return transactionRepository.findByReceiver(receiver);
    }

//    public List<TransactionEntity> getTransactionsByDateTimeBetween(LocalDateTime start, LocalDateTime end) {
//        return transactionRepository.findByDateTimeBetween(start.toString(), end.toString());
//    }

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

    public TransactionEntity addDeposit(MakeDepositRequest request, Long userId) {
        TransactionEntity deposit = new TransactionEntity();
        LocalDateTime dateTime = LocalDateTime.now();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        deposit.setAmount(request.getAmount());
        deposit.setDateTime(dateTime.toString());
        deposit.setMethod(Methods.DEPOSIT);
        deposit.setReceiver(user);
        deposit.setSender(user);
        deposit.setStatus(Status.PENDING);
        deposit = transactionRepository.save(deposit);
        PersonalEntity personalEntity = (PersonalEntity) user;
        personalEntity.addTransaction(deposit);
        personalEntity.setWalletBalance(personalEntity.getWalletBalance() + request.getAmount());
        userRepository.save(personalEntity);
        return deposit;
    }

    public TransactionEntity makePersonalTransactionWithQRCode(StartQrCodeTransactionRequest request){
        LocalDateTime datetime = LocalDateTime.now();
        TransactionEntity transaction = new TransactionEntity();
        UserEntity sender = userRepository.findById(Long.valueOf(request.getSenderId())).orElseThrow();
        transaction.setAmount(request.getAmount());
        transaction.setSender(sender);
        transaction.setMethod(Methods.BARCODE);
        transaction.setStatus(Status.PENDING);
        transaction.setDateTime(datetime.toString());
        transactionRepository.save(transaction);
        PersonalEntity senderEntity = (PersonalEntity) sender;
        senderEntity.addTempTransaction(transaction);
        userRepository.save(senderEntity);
        return transaction;
    }

    @Transactional
    public TransactionEntity makeBusinessTransactionWithQRCode(MakeQRCodeTransactionRequest request) throws Exception {
        UserEntity associate = userRepository.findById(Long.valueOf(request.getAssociateId())).orElseThrow();
        UserEntity receiver = userRepository.findById(Long.valueOf(request.getReceiverId())).orElseThrow();

        Optional<PersonalEntity> personalEntity = personalRepository.findById(Long.valueOf(request.getSenderId()));
        Optional<DependentEntity> dependentEntity = dependentRepository.findById(Long.valueOf(request.getSenderId()));

        if (personalEntity.isEmpty() && dependentEntity.isEmpty()) {
            throw new Exception("User not found in either repository");
        }

        List<TransactionEntity> tempTransactions;
        PersonalEntity sender;

        if (personalEntity.isPresent()) {
            sender = personalEntity.get();
            tempTransactions = sender.getTempTransactions();
        } else {
            sender = dependentEntity.get().getGuardian();
            tempTransactions = sender.getTempTransactions();
        }

        // Check if tempTransactions is empty
        if (tempTransactions.isEmpty()) {
            throw new Exception("No temporary transactions found");
        }

        LocalDateTime datetime = LocalDateTime.now();
        TransactionEntity transaction = new TransactionEntity();

        TransactionEntity matchedTransaction = null;

        for (TransactionEntity tempTransaction : tempTransactions) {
            if (tempTransaction.getAmount().equals(request.getAmount())) {
                matchedTransaction = tempTransaction;
                break;
            }
        }

        if (matchedTransaction == null) {
            throw new Exception("Transaction not found in tempTransactions");
        }

        // Set transaction details
        transaction.setAmount(request.getAmount());
        transaction.setAssociateId((AssociateEntity) associate);
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setMethod(Methods.BARCODE);
        transaction.setStatus(Status.APPROVED);
        transaction.setDateTime(datetime.toString());

        // Save transaction first
        transaction = transactionRepository.save(transaction);

        // Remove tempTransaction from sender
        sender.removeTempTransaction(matchedTransaction);

        // Persist sender changes before deleting temp transaction
        userRepository.save(sender);

        // Ensure tempTransaction is managed before deleting
        matchedTransaction = transactionRepository.findById(matchedTransaction.getId()).orElse(null);
        if (matchedTransaction != null) {
            transactionRepository.delete(matchedTransaction);
        }

        // Update wallet balance
        if (personalEntity.isPresent()) {
            sender.setWalletBalance(sender.getWalletBalance() - request.getAmount());
        }

        // Add the transaction to sender and receiver
        sender.addTransaction(transaction);
        ((BusinessEntity) receiver).addTransaction(transaction);

        // Save all changes in one transaction
        userRepository.save(associate);
        userRepository.save(sender);
        userRepository.save(receiver);

        return transaction;
    }

}
