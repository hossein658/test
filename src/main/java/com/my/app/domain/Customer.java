package com.my.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.app.domain.enumeration.GenderType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_nmae")
    private String fatherNmae;

    @Column(name = "national_code")
    private String nationalCode;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "id_number")
    private Integer idNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_type")
    private GenderType genderType;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CustomerAccount> customerAccounts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("customers")
    private Bank bank;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherNmae() {
        return fatherNmae;
    }

    public Customer fatherNmae(String fatherNmae) {
        this.fatherNmae = fatherNmae;
        return this;
    }

    public void setFatherNmae(String fatherNmae) {
        this.fatherNmae = fatherNmae;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public Customer nationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Customer birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public Customer idNumber(Integer idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public Customer genderType(GenderType genderType) {
        this.genderType = genderType;
        return this;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getAddress() {
        return address;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public Customer customerAccounts(Set<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
        return this;
    }

    public Customer addCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccounts.add(customerAccount);
        customerAccount.setCustomer(this);
        return this;
    }

    public Customer removeCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccounts.remove(customerAccount);
        customerAccount.setCustomer(null);
        return this;
    }

    public void setCustomerAccounts(Set<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    public Bank getBank() {
        return bank;
    }

    public Customer bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", fatherNmae='" + getFatherNmae() + "'" +
            ", nationalCode='" + getNationalCode() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", idNumber=" + getIdNumber() +
            ", genderType='" + getGenderType() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
