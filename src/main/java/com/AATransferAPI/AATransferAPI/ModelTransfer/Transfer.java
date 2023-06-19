package com.AATransferAPI.AATransferAPI.ModelTransfer;

import com.AATransferAPI.AATransferAPI.Audit.Audit;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transfers")
public class Transfer {

    // @NotBlank = The annotated element must not be null and must contain at least one non-whitespace character. Accepts CharSequence.
    // @Min = The annotated element must be a number whose value must be higher or equal to the specified minimum.
    @Id
    private UUID transferID;
    @Length(min = 3, max = 255, message = "Description of transfer is mandatory.")
    @Column(nullable = false, updatable = false)
    private String transferRef;
    @Positive(message = "Source account is required.")
    @Column(nullable = false, updatable = false)
    private Long accountFrom;
    @Positive(message = "Destination account is required.")
    @Column(nullable = false, updatable = false)
    private Long accountTo;
    @NotBlank(message = "Destination account is required.")
    @Column(nullable = false, updatable = false)
    private String currency;
    @Min(value = 0, message = "Destination account is required.")
    @Column(nullable = false, updatable = false)
    private Double amount;

    @Embedded
    private Audit audit = new Audit();

    public Transfer() {
    }

    public Transfer(@JsonProperty("transferRef") String transferRef,
                    @JsonProperty("accountFrom") Long accountFrom,
                    @JsonProperty("accountTo") Long accountTo,
                    @JsonProperty("amount") Double amount) {

        this.transferRef = transferRef;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public UUID get_transferID() {
        return transferID;
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
