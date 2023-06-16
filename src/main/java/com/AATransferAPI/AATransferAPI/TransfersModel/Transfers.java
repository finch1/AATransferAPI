package com.AATransferAPI.AATransferAPI.TransfersModel;

import com.fasterxml.jackson.annotation.JsonProperty;
//:)import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//:)@Entity
public class Transfers {

    // @NotBlank = The annotated element must not be null and must contain at least one non-whitespace character. Accepts CharSequence.
    // @Min = The annotated element must be a number whose value must be higher or equal to the specified minimum.
    private UUID _transferID;
    @NotBlank(message = "Description of transfer is mandatory.")
    private String _transferRef;
    @NotBlank(message = "Source account is required.")
    private UUID _accountFrom;
    @NotBlank(message = "Destination account is required.")
    private UUID _accountTo;
    @NotBlank(message = "Destination account is required.")
    private Enum _currency;
    @Min(value = 0, message = "Destination account is required.")
    private Double _amount;

    public Transfers(@JsonProperty("transferRef") String transferRef,
                     @JsonProperty("accountFrom") UUID accountFrom,
                     @JsonProperty("accountTo") UUID accountTo,
                     @JsonProperty("currency") Enum currency,
                     @JsonProperty("amount") Double amount) {

        this._transferID = UUID.randomUUID();
        this._transferRef = transferRef;
        this._accountFrom = accountFrom;
        this._accountTo = accountTo;
        this._currency = currency;
        this._amount = amount;
    }

    public UUID get_transferID() {
        return _transferID;
    }

    public String get_transferRef() {
        return _transferRef;
    }

    public UUID get_accountFrom() {
        return _accountFrom;
    }

    public UUID get_accountTo() {
        return _accountTo;
    }

    public Enum get_currency() {
        return _currency;
    }

    public Double get_amount() {
        return _amount;
    }
}
