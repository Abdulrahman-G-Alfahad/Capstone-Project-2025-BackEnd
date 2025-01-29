package NASA.Capstone.Account.AdminService.controller;

import NASA.Capstone.Account.AdminService.Enums.Status;
import NASA.Capstone.Account.AdminService.GetTransactionsByReceiverResponse;
import NASA.Capstone.Account.AdminService.bo.*;
import NASA.Capstone.Account.AdminService.entity.TransactionEntity;
import NASA.Capstone.Account.AdminService.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            response.setTransaction(transactionService.getTransactionById(transactionId));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllTransactionResponse> getAllTransactions(){
        GetAllTransactionResponse response = new GetAllTransactionResponse();
        try{
            response.setTransactions(transactionService.getAllTransactions());
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
            response.setTransaction(transaction);
            response.setMessage("Transaction successful");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer")
    public ResponseEntity<MakeTransferResponse> makeTransfer(@RequestBody MakeTransferRequest request){
        MakeTransferResponse response = new MakeTransferResponse();
        try{
            TransactionEntity transaction = transactionService.makeTransfer(request);
            response.setTransaction(transaction);
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
            response.setTransactions(transactionService.getTransactionsBySender(senderId));
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
            response.setTransactions(transactionService.getTransactionsByReceiver(receiverId));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/datetime")
    public ResponseEntity<GetTransactionsByDateTimeBetweenResponse> getTransactionsByDateTimeBetween(@RequestBody GetTransactionsByDateTimeBetweenRequest request){
        GetTransactionsByDateTimeBetweenResponse response = new GetTransactionsByDateTimeBetweenResponse();
        try{
            response.setTransactions(transactionService.getTransactionsByDateTimeBetween(request.getStart(), request.getEnd()));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/associate/{associateId}")
    public ResponseEntity<GetTransactionsByAssociateResponse> getTransactionsByAssociate(@PathVariable("associateId") Long associateId){
        GetTransactionsByAssociateResponse response = new GetTransactionsByAssociateResponse();
        try{
            response.setTransactions(transactionService.getTransactionsByAssociate(associateId));
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
            response.setTransactions(transactionService.getTransactionsByStatus(Status.valueOf(status)));
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
            response.setTransaction(transactionService.updateTransactionStatus(transactionId,Status.APPROVED));
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
            response.setTransaction(transactionService.updateTransactionStatus(transactionId,Status.REJECTED));
            response.setMessage("Transaction rejected");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }


}
