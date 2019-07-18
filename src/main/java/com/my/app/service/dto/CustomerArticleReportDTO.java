package com.my.app.service.dto;
import java.time.LocalDate;
import java.util.Objects;

public class CustomerArticleReportDTO {

    private Long id;
    private String accountNumber;
    private LocalDate startDateTransaction;
    private LocalDate endDateTransaction;

    public CustomerArticleReportDTO(Long id, String accountNumber, LocalDate startDateTransaction, LocalDate endDateTransaction) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.startDateTransaction = startDateTransaction;
        this.endDateTransaction = endDateTransaction;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerArticleReportDTO)) return false;
        CustomerArticleReportDTO that = (CustomerArticleReportDTO) o;
        return id.equals(that.id) &&
            getAccountNumber().equals(that.getAccountNumber()) &&
            getStartDateTransaction().equals(that.getStartDateTransaction()) &&
            getEndDateTransaction().equals(that.getEndDateTransaction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getAccountNumber(), getStartDateTransaction(), getEndDateTransaction());
    }

    @Override
    public String toString() {
        return "CustomerArticleReportDTO{" +
            "id=" + id +
            ", accountNumber='" + accountNumber + '\'' +
            ", startDateTransaction=" + startDateTransaction +
            ", endDateTransaction=" + endDateTransaction +
            '}';
    }
}

