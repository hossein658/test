package com.my.app.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.app.domain.enumeration.TransActionType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_transaction")
    private LocalDate dateTransaction;

    @Column(name = "debtor_amount")
    private Long debtorAmount;

    @Column(name = "creditor_amount")
    private Long creditorAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "trans_action_type")
    private TransActionType transActionType;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("articles")
    private CustomerAccount customerAccount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateTransaction() {
        return dateTransaction;
    }

    public Article dateTransaction(LocalDate dateTransaction) {
        this.dateTransaction = dateTransaction;
        return this;
    }

    public void setDateTransaction(LocalDate dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Long getDebtorAmount() {
        return debtorAmount;
    }

    public Article debtorAmount(Long debtorAmount) {
        this.debtorAmount = debtorAmount;
        return this;
    }

    public void setDebtorAmount(Long debtorAmount) {
        this.debtorAmount = debtorAmount;
    }

    public Long getCreditorAmount() {
        return creditorAmount;
    }

    public Article creditorAmount(Long creditorAmount) {
        this.creditorAmount = creditorAmount;
        return this;
    }

    public void setCreditorAmount(Long creditorAmount) {
        this.creditorAmount = creditorAmount;
    }

    public TransActionType getTransActionType() {
        return transActionType;
    }

    public Article transActionType(TransActionType transActionType) {
        this.transActionType = transActionType;
        return this;
    }

    public void setTransActionType(TransActionType transActionType) {
        this.transActionType = transActionType;
    }

    public String getDescription() {
        return description;
    }

    public Article description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public Article customerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
        return this;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        return id != null && id.equals(((Article) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + getId() +
            ", dateTransaction='" + getDateTransaction() + "'" +
            ", debtorAmount=" + getDebtorAmount() +
            ", creditorAmount=" + getCreditorAmount() +
            ", transActionType='" + getTransActionType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
