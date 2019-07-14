package com.my.app.service.dto;

import com.my.app.domain.Customer;
import com.my.app.domain.enumeration.GenderType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link Customer} entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String fatherNmae;

    private String nationalCode;

    private LocalDate birthDate;

    private Integer idNumber;

    private GenderType genderType;

    private String address;


    private Long bankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherNmae() {
        return fatherNmae;
    }

    public void setFatherNmae(String fatherNmae) {
        this.fatherNmae = fatherNmae;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", fatherNmae='" + getFatherNmae() + "'" +
            ", nationalCode='" + getNationalCode() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", idNumber=" + getIdNumber() +
            ", genderType='" + getGenderType() + "'" +
            ", address='" + getAddress() + "'" +
            ", bank=" + getBankId() +
            "}";
    }
}
