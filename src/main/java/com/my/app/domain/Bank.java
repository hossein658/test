package com.my.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Bank.
 */
@Entity
@Table(name = "bank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "branch_name")
    private String branchName;

    @OneToMany(mappedBy = "bank")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "bank")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CustomerAccount> customerAccounts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public Bank branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public Bank branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Bank customers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public Bank addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setBank(this);
        return this;
    }

    public Bank removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.setBank(null);
        return this;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public Bank customerAccounts(Set<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
        return this;
    }

    public Bank addCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccounts.add(customerAccount);
        customerAccount.setBank(this);
        return this;
    }

    public Bank removeCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccounts.remove(customerAccount);
        customerAccount.setBank(null);
        return this;
    }

    public void setCustomerAccounts(Set<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id) &&
            Objects.equals(branchCode, bank.branchCode) &&
            Objects.equals(branchName, bank.branchName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branchCode, branchName);
    }

    @Override
    public String toString() {
        return "Bank{" +
            "id=" + getId() +
            ", branchCode='" + getBranchCode() + "'" +
            ", branchName='" + getBranchName() + "'" +
            "}";
    }
}
