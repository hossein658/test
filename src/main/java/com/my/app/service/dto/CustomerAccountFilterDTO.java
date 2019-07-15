package com.my.app.service.dto;

import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;

public class CustomerAccountFilterDTO {
    private GenderType genderType;
    private AccountType accountType;

    public CustomerAccountFilterDTO(GenderType genderType, AccountType accountType) {
        this.genderType = genderType;
        this.accountType = accountType;
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
}
