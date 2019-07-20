package com.my.app.service.dto;

import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "not an ignored comment")
public class CustomerAccountFilterDTO implements Serializable
{
    private GenderType genderType ;
    private AccountType accountType ;
    private String branchCode;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public CustomerAccountFilterDTO() {
    }

    public CustomerAccountFilterDTO(GenderType genderType, AccountType accountType,String branchCode) {
        this.genderType = genderType;
        this.accountType = accountType;
        this.branchCode = branchCode;

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
