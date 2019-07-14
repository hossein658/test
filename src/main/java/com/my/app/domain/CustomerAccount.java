package com.my.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "customer_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustomerAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "balance")
    private Long balance;

    @OneToMany(mappedBy = "customerAccount")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Article> articles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("customerAccounts")
    private Bank bank;

    @ManyToOne
    @JsonIgnoreProperties("customerAccounts")
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public CustomerAccount accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Status getStatus() {
        return status;
    }

    public CustomerAccount status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public CustomerAccount accountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getBalance() {
        return balance;
    }

    public CustomerAccount balance(Long balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public CustomerAccount articles(Set<Article> articles) {
        this.articles = articles;
        return this;
    }

    public CustomerAccount addArticle(Article article) {
        this.articles.add(article);
        article.setCustomerAccount(this);
        return this;
    }

    public CustomerAccount removeArticle(Article article) {
        this.articles.remove(article);
        article.setCustomerAccount(null);
        return this;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Bank getBank() {
        return bank;
    }

    public CustomerAccount bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerAccount customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerAccount)) {
            return false;
        }
        return id != null && id.equals(((CustomerAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
            "id=" + getId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", balance=" + getBalance() +
            "}";
    }
}
