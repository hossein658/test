package com.my.app.repository;

import com.my.app.domain.Bank;
import com.my.app.domain.CustomerAccount;
import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomerAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {


    @Query("select ca from CustomerAccount ca " +
        "inner join ca.bank b " +
        "where b.branchCode = :branchCode ")
    Page<CustomerAccount> findByBranchCode(Pageable page, @Param("branchCode") String branchCode);

    @Query("select ca from CustomerAccount ca " +
        "inner join ca.customer c " +
        "where ca.accountType  = :accountType " +
        "and c.genderType = :genderType ")
    Page<CustomerAccount> findByGenderTypeAndAccountType(Pageable page,
                                                         @Param("genderType") GenderType genderType,
                                                         @Param("accountType") AccountType accountType);


//    Page<CustomerAccount> findByBankAndAccountType(Bank bank, AccountType accountType, Pageable pageable);
/*
    @Query("select ca from CustomerAccount ca " +
        "inner join ca.bank b " +
        "where ca.accountType  = :accountType " +
        "and b.branchCode = :branchCode " )
    Page<CustomerAccount> findByAccountTypeAndBranchCode(Pageable page , @Param("accountType") AccountType accountType,
                                                         @Param("branchCode") String branchCode);*/

    Page<CustomerAccount> findAllByBank(Bank bank, Pageable pageable);

    Page<CustomerAccount> findAllByBankAndAccountType(Bank bank, AccountType accountType, Pageable pageable);


/*

    @Query("select ca from CustomerAccount ca " +
        "inner join fetch ca.bank b " +
        "where ca.accountType  = :accountType " +
        "and b.branchCode = :branchCode ")
    Page<CustomerAccount> findByAccountTypeAndBranch(Pageable page,
                                                     @Param("accountType") AccountType accountType,
                                                     @Param("branchCode") String branchCode);
*/

 /*   @Query("select ca from CustomerAccount ca " +
        "inner join fetch ca.bank b")
    Page<CustomerAccount> findAllByLoadedBank();*/


 /*   Page<CustomerAccount> findAllByBankAndBranchCode(Bank bank, AccountType accountType,
                                                     String branchCode, Pageable pageable);
*/

}
