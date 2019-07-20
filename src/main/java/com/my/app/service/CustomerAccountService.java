package com.my.app.service;

import com.my.app.domain.CustomerAccount;
import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;
import com.my.app.service.dto.CustomerAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

/**
 * Service Interface for managing {@link CustomerAccount}.
 */
public interface CustomerAccountService {

    /**
     * Save a customerAccount.
     *
     * @param customerAccountDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerAccountDTO save(CustomerAccountDTO customerAccountDTO);

    /**
     * Get all the customerAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerAccountDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerAccountDTO> findOne(Long id);

    /**
     * Delete the "id" customerAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<CustomerAccountDTO> findByBranchCode (Pageable pageable , String branchCode);

    Page<CustomerAccountDTO> findByGenderAndAccountType(Pageable pageable, GenderType genderType, AccountType accountType);

/*
    Page<CustomerAccountsGroupByGenderAndTypeDTO> findCustomerAccountsGroupByGenderAndTypeDTO(Pageable pageable);

    Optional<Map<AccountType,Long>> findByAccountTypeAndByBranchCode(Pageable pageable, AccountType accountType, String branchCode);
*/

    Optional<Map<AccountType,Long>> countByAccountTypeAndByBank();

    Optional<Map<String, Map<AccountType, Long>>> countByAccountTypeByBranchCode();

    Page<CustomerAccount> findAllByBank(String branchCode, Pageable pageable);

    Page<CustomerAccount> findAllByBankAndAccountType(String branchCode, AccountType accountType, Pageable pageable);


}
