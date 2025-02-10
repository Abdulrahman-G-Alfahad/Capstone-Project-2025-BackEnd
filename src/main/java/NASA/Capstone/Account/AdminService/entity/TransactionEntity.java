package NASA.Capstone.Account.AdminService.entity;

import NASA.Capstone.Account.AdminService.Enums.Methods;
import NASA.Capstone.Account.AdminService.Enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String dateTime;

    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties(value = {"transactionHistory", "transactions", "familyMembers"})
    private Accounts sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonIgnoreProperties(value = {"transactionHistory", "transactions", "familyMembers"})
    private UserEntity receiver;

    @Column(nullable = false)
//    @JsonIgnore
    private Methods method;

    @ManyToOne
    @JoinColumn(name = "associate_id", nullable = true)
    @JsonIgnoreProperties(value = {"transactions", "associates"})
    private AssociateEntity associateId;

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Accounts getSender() {
        return sender;
    }

    public void setSender(Accounts sender) {
        this.sender = sender;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    public AssociateEntity getAssociateId() {
        return associateId;
    }

    public void setAssociateId(AssociateEntity associateId) {
        this.associateId = associateId;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", method=" + method +
                '}';
    }
}
