package com.my.app.service;

import com.my.app.service.dto.CustomerAccountDTO;

import com.my.app.domain.CustomerAccount;
import com.my.app.service.dto.CustomerAccountsGroupByGenderAndTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

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

    Page<CustomerAccountsGroupByGenderAndTypeDTO> findCustomerAccountsGroupByGenderAndTypeDTO(Pageable pageable);
}
