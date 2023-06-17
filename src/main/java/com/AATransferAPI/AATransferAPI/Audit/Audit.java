package com.AATransferAPI.AATransferAPI.Audit;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Embeddable
public class Audit {

    @NotNull
    private LocalDateTime createdOn;

    @NotNull
    private String createdBy;

    private LocalDateTime updatedOn;

    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
        createdBy = LoggedUser.get();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = LoggedUser.get();
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}