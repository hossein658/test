package com.my.app.service.dto;

import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Objects;

@ApiModel(description = "not an ignored comment")
public class CustomerAccountFilterDTO implements Serializable
{
    private GenderType genderType ;
    private AccountType accountType ;
    private String branchCode;



    public CustomerAccountFilterDTO(GenderType genderType, AccountType accountType, String branchCode) {
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

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAccountFilterDTO that = (CustomerAccountFilterDTO) o;
        return genderType == that.genderType &&
            accountType == that.accountType &&
            branchCode.equals(that.branchCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genderType, accountType, branchCode);
    }

    @Override
    public String toString() {
        return "CustomerAccountFilterDTO{" +
            "genderType=" + genderType +
            ", accountType=" + accountType +
            ", branchCode='" + branchCode + '\'' +
            '}';
    }
}
