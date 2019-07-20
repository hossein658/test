package com.my.app.service.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerArticleReportDTO {


    private String accountNumber;
    private LocalDate startDateTransaction;
    private LocalDate endDateTransaction;

    public CustomerArticleReportDTO() {
    }

    public CustomerArticleReportDTO(String accountNumber, LocalDate startDateTransaction, LocalDate endDateTransaction) {
        this.accountNumber = accountNumber;
        this.startDateTransaction = startDateTransaction;
        this.endDateTransaction = endDateTransaction;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getStartDateTransaction() {
        return startDateTransaction;
    }

    public void setStartDateTransaction(LocalDate startDateTransaction) {
        this.startDateTransaction = startDateTransaction;
    }

    public LocalDate getEndDateTransaction() {
        return endDateTransaction;
    }

    public void setEndDateTransaction(LocalDate endDateTransaction) {
        this.endDateTransaction = endDateTransaction;
    }


}

