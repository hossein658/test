package com.my.app.service.dto;
import com.my.app.domain.CustomerAccount;
import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.Status;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link CustomerAccount} entity.
 */
@ApiModel(description = "not an ignored comment")
public class CustomerAccountDTO implements Serializable {

    private Long id;

    private String accountNumber;

    private Status status;

    private AccountType accountType;

    private Long balance;


    private Long bankId;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerAccountDTO customerAccountDTO = (CustomerAccountDTO) o;
        if (customerAccountDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerAccountDTO{" +
            "id=" + getId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", balance=" + getBalance() +
            ", bank=" + getBankId() +
            ", customer=" + getCustomerId() +
            "}";
    }
}
