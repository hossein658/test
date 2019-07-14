package com.my.app.service.dto;

import com.my.app.domain.Article;
import com.my.app.domain.enumeration.TransActionType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link Article} entity.
 */
public class ArticleDTO implements Serializable {

    private Long id;

    private LocalDate dateTransaction;

    private Long debtorAmount;

    private Long creditorAmount;

    private TransActionType transActionType;

    private String description;


    private Long customerAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDate dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Long getDebtorAmount() {
        return debtorAmount;
    }

    public void setDebtorAmount(Long debtorAmount) {
        this.debtorAmount = debtorAmount;
    }

    public Long getCreditorAmount() {
        return creditorAmount;
    }

    public void setCreditorAmount(Long creditorAmount) {
        this.creditorAmount = creditorAmount;
    }

    public TransActionType getTransActionType() {
        return transActionType;
    }

    public void setTransActionType(TransActionType transActionType) {
        this.transActionType = transActionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Long customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleDTO articleDTO = (ArticleDTO) o;
        if (articleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), articleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
            "id=" + getId() +
            ", dateTransaction='" + getDateTransaction() + "'" +
            ", debtorAmount=" + getDebtorAmount() +
            ", creditorAmount=" + getCreditorAmount() +
            ", transActionType='" + getTransActionType() + "'" +
            ", description='" + getDescription() + "'" +
            ", customerAccount=" + getCustomerAccountId() +
            "}";
    }
}
