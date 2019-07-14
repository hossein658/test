package com.my.app.service.dto;

import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;

import java.util.Objects;

public class CustomizedAccountsDTO {
    private Long id;
    private GenderType genderType;
    private AccountType accountType;
    private String branchCode;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public CustomizedAccountsDTO(String branchCode) {
        this.branchCode = branchCode;
    }

    public CustomizedAccountsDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CustomizedAccountsDTO(GenderType genderType, AccountType accountType) {
        this.genderType = genderType;
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomizedAccountsDTO that = (CustomizedAccountsDTO) o;
        return id.equals( that.id ) &&
            genderType == that.genderType &&
            accountType == that.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, genderType, accountType );
    }


    @Override
    public String toString() {
        return "CustomizedAccountsDTO{" +
            "id=" + id +
            ", genderType=" + genderType +
            ", accountType=" + accountType +
            ", branchCode='" + branchCode + '\'' +
            '}';
    }
}
