package com.AATransferAPI.AATransferAPI.TransfersModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Transfers {

    // @NotBlank = The annotated element must not be null and must contain at least one non-whitespace character. Accepts CharSequence.
    // @Min = The annotated element must be a number whose value must be higher or equal to the specified minimum.
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private UUID transferID;
    @Column(name = "transferDate")
    private Date transferDate;
    @NotBlank(message = "Description of transfer is mandatory.")
    private String transferRef;
    @NotBlank(message = "Source account is required.")
    private Long accountFrom;
    @NotBlank(message = "Destination account is required.")
    private Long accountTo;
    @NotBlank(message = "Destination account is required.")
    private String currency;
    @Min(value = 0, message = "Destination account is required.")
    private Double amount;
    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    private String createdBy;

    public Transfers(@JsonProperty("transferRef") String transferRef,
                     @JsonProperty("accountFrom") Long accountFrom,
                     @JsonProperty("accountTo") Long accountTo,
                     @JsonProperty("currency") @NotBlank(message = "Destination account is required.") String currency,
                     @JsonProperty("amount") Double amount) {

        this.transferID = UUID.randomUUID();
        this.transferRef = transferRef;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currency = currency;
        this.amount = amount;
    }

    public UUID get_transferID() {
        return transferID;
    }

    @PrePersist
    void transferDate() {
        this.transferDate = new Date();
    }

    public String get_transferRef() {
        return transferRef;
    }

    public Long get_accountFrom() {
        return accountFrom;
    }

    public Long get_accountTo() {
        return accountTo;
    }

    public String get_currency() {
        return currency;
    }

    public Double get_amount() {
        return amount;
    }
}
