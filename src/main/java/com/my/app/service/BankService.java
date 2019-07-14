package com.my.app.service;

import com.my.app.domain.Customer;
import com.my.app.service.dto.BankDTO;

import com.my.app.domain.Bank;
import com.my.app.service.dto.CustomerAccountDTO;
import com.my.app.service.dto.CustomerDTO;
import com.my.app.service.dto.CustomizedAccountsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Service Interface for managing {@link Bank}.
 */
public interface BankService {

    /**
     * Save a bank.
     *
     * @param bankDTO the entity to save.
     * @return the persisted entity.
     */
    BankDTO save(BankDTO bankDTO);

    /**
     * Get all the banks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BankDTO> findAll(Pageable pageable);


    /**
     * Get the "id" bank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BankDTO> findOne(Long id);

    /**
     * Delete the "id" bank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * get a banchCode
     *
     * @param pageable
     * @param branchCode
     * @return a list of persisted entities
     */
    Page<CustomerAccountDTO> findAccountsByBranchCode(Pageable pageable, @Param("branchCode") String branchCode);

    /**
     * get a banchCode
     *
     * @param pageable
     * @param branchCode
     * @return a list of persisted entities
     */
    Page<CustomerDTO> findCustomersByBranchCode(Pageable pageable, @Param("branchCode") String branchCode);

    /**
     * get a banchCode
     *
     * @param pageable
     * @param branchCode
     * @return a list of persisted entities
     */
    Page<CustomizedAccountsDTO> findCustomizedAccounts(Pageable pageable, @Param("branchCode") String branchCode);
}
