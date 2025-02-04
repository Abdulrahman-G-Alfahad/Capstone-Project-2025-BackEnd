package NASA.Capstone.Account.AdminService.controller;

import NASA.Capstone.Account.AdminService.Enums.Status;
import NASA.Capstone.Account.AdminService.bo.GetTransactionsByReceiverResponse;
import NASA.Capstone.Account.AdminService.bo.*;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }


    @GetMapping("/{transactionId}")
    public ResponseEntity<GetTransactionByIdResponse> getTransactionById(@PathVariable("transactionId") Long transactionId){
        GetTransactionByIdResponse response = new GetTransactionByIdResponse();
        try{
            TransactionDTO dto = transactionService.fillTransactionDto(transactionService.getTransactionById(transactionId));
            response.setTransaction(dto);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllTransactionResponse> getAllTransactions(){
        GetAllTransactionResponse response = new GetAllTransactionResponse();
        try{
            List<TransactionDTO> transactions = new ArrayList<>();
            for (TransactionEntity transaction : transactionService.getAllTransactions()){
                transactions.add(transactionService.fillTransactionDto(transaction));
            }
            response.setTransactions(transactions);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/business")
    public ResponseEntity<MakeBusinessTransactionResponse> makeBusinessTransaction(@RequestBody MakeBusinessTransactionRequest request){
        MakeBusinessTransactionResponse response = new MakeBusinessTransactionResponse();
        try{
            TransactionEntity transaction = transactionService.makeBusinessTransaction(request);
            TransactionDTO dto = transactionService.fillTransactionDto(transaction);
            System.out.println(dto);
            response.setTransaction(dto);
            response.setMessage("Transaction successful");
            System.out.println(response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<MakeTransferResponse> makeTransfer(@RequestBody MakeTransferRequest request){
        MakeTransferResponse response = new MakeTransferResponse();
        try{
            TransactionEntity transaction = transactionService.makeTransfer(request);
            TransactionDTO dto = transactionService.fillTransactionDto(transaction);
            response.setTransaction(dto);
            response.setMessage("Transfer successful");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sender/{senderId}")
    public ResponseEntity<GetTransactionsBySenderResponse> getTransactionsBySender(@PathVariable("senderId") Long senderId){
        GetTransactionsBySenderResponse response = new GetTransactionsBySenderResponse();
        try{
            List<TransactionDTO> transactions = new ArrayList<>();
            for (TransactionEntity transaction : transactionService.getTransactionsBySender(senderId)){
                transactions.add(transactionService.fillTransactionDto(transaction));
            }
            response.setTransactions(transactions);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<GetTransactionsByReceiverResponse> getTransactionsByReceiver(@PathVariable("receiverId") Long receiverId){
        GetTransactionsByReceiverResponse response = new GetTransactionsByReceiverResponse();
        try{
            List<TransactionDTO> transactions = new ArrayList<>();
            for (TransactionEntity transaction : transactionService.getTransactionsByReceiver(receiverId)){
                transactions.add(transactionService.fillTransactionDto(transaction));
            }
            response.setTransactions(transactions);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/datetime")
//    public ResponseEntity<GetTransactionsByDateTimeBetweenResponse> getTransactionsByDateTimeBetween(@RequestBody GetTransactionsByDateTimeBetweenRequest request){
//        GetTransactionsByDateTimeBetweenResponse response = new GetTransactionsByDateTimeBetweenResponse();
//        try{
//            List<TransactionDTO> transactions = new ArrayList<>();
//            for (TransactionEntity transaction : transactionService.getTransactionsByDateTimeBetween(request.getStart(),request.getEnd())){
//                transactions.add(transactionService.fillTransactionDto(transaction));
//            }
//            response.setTransactions(transactions);
//        } catch (Exception e) {
//            response.setMessage(e.getMessage());
//            return ResponseEntity.status(404).body(response);
//        }
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/associate/{associateId}")
    public ResponseEntity<GetTransactionsByAssociateResponse> getTransactionsByAssociate(@PathVariable("associateId") Long associateId){
        GetTransactionsByAssociateResponse response = new GetTransactionsByAssociateResponse();
        try{
            List<TransactionDTO> transactions = new ArrayList<>();
            for (TransactionEntity transaction : transactionService.getTransactionsByAssociate(associateId)){
                transactions.add(transactionService.fillTransactionDto(transaction));
            }
            response.setTransactions(transactions);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<GetTransactionsByStatusResponse> getTransactionsByStatus(@PathVariable("status") String status){
        GetTransactionsByStatusResponse response = new GetTransactionsByStatusResponse();
        try{
            List<TransactionDTO> transactions = new ArrayList<>();
            for (TransactionEntity transaction : transactionService.getTransactionsByStatus(Status.valueOf(status))){
                transactions.add(transactionService.fillTransactionDto(transaction));
            }
            response.setTransactions(transactions);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/approve/{transactionId}")
    public ResponseEntity<ChangeTransactionStatusResponse> approveTransaction(@PathVariable("transactionId") Long transactionId){
        ChangeTransactionStatusResponse response = new ChangeTransactionStatusResponse();
        try{
            response.setTransaction(transactionService.fillTransactionDto(transactionService.updateTransactionStatus(transactionId,Status.APPROVED)));
            response.setMessage("Transaction approved");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/reject/{transactionId}")
    public ResponseEntity<ChangeTransactionStatusResponse> rejectTransaction(@PathVariable("transactionId") Long transactionId){
        ChangeTransactionStatusResponse response = new ChangeTransactionStatusResponse();
        try{
            response.setTransaction(transactionService.fillTransactionDto(transactionService.updateTransactionStatus(transactionId,Status.REJECTED)));
            response.setMessage("Transaction rejected");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }


}
