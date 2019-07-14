package com.my.app.repository;

import com.my.app.domain.Bank;
import com.my.app.domain.Customer;
import com.my.app.domain.CustomerAccount;
import com.my.app.service.dto.CustomerAccountDTO;
import com.my.app.service.dto.CustomerDTO;
import com.my.app.service.dto.CustomizedAccountsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Bank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query("select ca from CustomerAccount ca")
    Page<CustomerAccount> findAccountsByBranchCode (Pageable pageable, String branchCode);

    @Query("select c from Customer c")
    Page<Customer> findCustomersByBranchCode (Pageable pageable, String branchCode);

//    @Query(value = "SELECT new com.my.app.service.dto.CustomizedAccountDTO(customer.id, " +
//        "customer.genderType , " +
//        "customerAccount.accountType, " +
//        "FROM CustomerAccount ca " +
//        "inner JOIN ca. ")

    Page<CustomizedAccountsDTO> findCustomizedAccounts(Pageable pageable
        , @Param("branchCode") String branchCode);

}
