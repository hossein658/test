package com.my.app.service.dto;

import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;

public class CustomerAccountsGroupByGenderAndTypeDTO {

    private GenderType genderType;
    private AccountType accountType;
    private CustomerAccountDTO customerAccountDTO;

    public CustomerAccountsGroupByGenderAndTypeDTO(GenderType genderType, AccountType accountType, CustomerAccountDTO customerAccountDTO) {
        this.genderType = genderType;
        this.accountType = accountType;
        this.customerAccountDTO = customerAccountDTO;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public CustomerAccountDTO getCustomerAccountDTO() {
        return customerAccountDTO;
    }

    public void setCustomerAccountDTO(CustomerAccountDTO customerAccountDTO) {
        this.customerAccountDTO = customerAccountDTO;
    }

    @Override
    public String toString() {
        return "CustomerAccountsGroupByGenderAndTypeDTO{" +
            "genderType=" + genderType +
            ", accountType=" + accountType +
            ", customerAccountDTO=" + customerAccountDTO +
            '}';
    }
}
