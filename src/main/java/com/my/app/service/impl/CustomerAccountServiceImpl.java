package com.my.app.service.impl;

import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;
import com.my.app.service.CustomerAccountService;
import com.my.app.domain.CustomerAccount;
import com.my.app.repository.CustomerAccountRepository;
import com.my.app.service.dto.CustomerAccountDTO;
import com.my.app.service.dto.CustomerAccountsGroupByGenderAndTypeDTO;
import com.my.app.service.mapper.CustomerAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service Implementation for managing {@link CustomerAccount}.
 */
@Service
@Transactional
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final Logger log = LoggerFactory.getLogger(CustomerAccountServiceImpl.class);

    private final CustomerAccountRepository customerAccountRepository;

    private final CustomerAccountMapper customerAccountMapper;

    public CustomerAccountServiceImpl(CustomerAccountRepository customerAccountRepository, CustomerAccountMapper customerAccountMapper) {
        this.customerAccountRepository = customerAccountRepository;
        this.customerAccountMapper = customerAccountMapper;
    }

    /**
     * Save a customerAccount.
     *
     * @param customerAccountDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerAccountDTO save(CustomerAccountDTO customerAccountDTO) {
        log.debug("Request to save CustomerAccount : {}", customerAccountDTO);
        CustomerAccount customerAccount = customerAccountMapper.toEntity(customerAccountDTO);
        customerAccount = customerAccountRepository.save(customerAccount);
        return customerAccountMapper.toDto(customerAccount);
    }

    /**
     * Get all the customerAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerAccountDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerAccounts");
        return customerAccountRepository.findAll(pageable)
            .map(customerAccountMapper::toDto);
    }


    /**
     * Get one customerAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerAccountDTO> findOne(Long id) {
        log.debug("Request to get CustomerAccount : {}", id);
        return customerAccountRepository.findById(id)
            .map(customerAccountMapper::toDto);
    }

    /**
     * Delete the customerAccount by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerAccount : {}", id);
        customerAccountRepository.deleteById(id);
    }

    @Override
    public Page<CustomerAccountDTO> findByBranchCode(Pageable pageable, String branchCode) {
        log.debug("!!!");
        return customerAccountRepository.findByBranchCode(pageable, branchCode)
            .map(customerAccountMapper::toDto);
    }


    @Override
    public Page<CustomerAccountsGroupByGenderAndTypeDTO> findCustomerAccountsGroupByGenderAndTypeDTO(Pageable pageable) {
//        Page<CustomerAccount> customerAccounts = customerAccountRepository.findAll(pageable);
//        Page<CustomerAccountsGroupByGenderAndTypeDTO > p = customerAccounts.stream()
//            .map(new CustomerAccountsGroupByGenderAndTypeDTO() )
//            .collect(Collectors.toList()            );


        return null;
    }

}
