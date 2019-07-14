package com.my.app.service.impl;

import com.my.app.domain.Bank;
import com.my.app.domain.Customer;
import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;
import com.my.app.repository.BankRepository;
import com.my.app.service.BankService;
import com.my.app.service.dto.BankDTO;
import com.my.app.service.dto.CustomerAccountsGroupByGenderAndTypeDTO;
import com.my.app.service.mapper.BankMapper;
import com.my.app.service.mapper.CustomerAccountMapper;
import com.my.app.service.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bank}.
 */
@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final Logger log = LoggerFactory.getLogger(BankServiceImpl.class);
    private final BankRepository bankRepository;
    private final BankMapper bankMapper;
    private final Bank bank;
    private final CustomerAccountMapper customerAccountMapper;
    private final CustomerMapper customerMapper;
    private final GenderType genderType;
    private final AccountType accountType;
    private final Customer customer;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BankServiceImpl(BankRepository bankRepository, BankMapper bankMapper, Bank bank, CustomerAccountMapper customerAccountMapper, CustomerMapper customerMapper, GenderType genderType, AccountType accountType, Customer customer) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
        this.bank = bank;
        this.customerAccountMapper = customerAccountMapper;
        this.customerMapper = customerMapper;
        this.genderType = genderType;
        this.accountType = accountType;
        this.customer = customer;
    }

    /**
     * Save a bank.
     *
     * @param bankDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BankDTO save(BankDTO bankDTO) {
        log.debug("Request to save Bank : {}", bankDTO);
        Bank bank = bankMapper.toEntity(bankDTO);
        bank = bankRepository.save(bank);
        return bankMapper.toDto(bank);
    }

    /**
     * Get all the banks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Banks");
        return bankRepository.findAll(pageable)
            .map(bankMapper::toDto);
    }


    /**
     * Get one bank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankDTO> findOne(Long id) {
        log.debug("Request to get Bank : {}", id);
        return bankRepository.findById(id)
            .map(bankMapper::toDto);
    }

    /**
     * Delete the bank by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bank : {}", id);
        bankRepository.deleteById(id);
    }

    /**
     * get a branchCode
     *
     * @param pageable
     * @param branchCode
     * @return a list of persisted entities
     */
    public Page<CustomerAccountsGroupByGenderAndTypeDTO> findCustomizedAccounts(Pageable pageable, String branchCode) {
        log.debug("Request to get Bank : {}", pageable);
//        return bankRepository.findCustomersByBranchCode( pageable, branchCode )
//            .filter( bankRepository -> !genderType.equals( genderType )And -> !accountType.equals( accountType );

        return null;
    }
}
