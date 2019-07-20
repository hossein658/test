package com.my.app.service.impl;

import com.my.app.domain.Bank;
import com.my.app.domain.CustomerAccount;
import com.my.app.domain.enumeration.AccountType;
import com.my.app.domain.enumeration.GenderType;
import com.my.app.repository.CustomerAccountRepository;
import com.my.app.service.BankService;
import com.my.app.service.CustomerAccountService;
import com.my.app.service.dto.CustomerAccountDTO;
import com.my.app.service.mapper.CustomerAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Service Implementation for managing {@link CustomerAccount}.
 */
@Service
@Transactional
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final Logger log = LoggerFactory.getLogger(CustomerAccountServiceImpl.class);

    private final CustomerAccountRepository customerAccountRepository;

    private final CustomerAccountMapper customerAccountMapper;

    @Autowired
    private BankService bankService;

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


 /*   @Override
    public Page<CustomerAccountsGroupByGenderAndTypeDTO> findCustomerAccountsGroupByGenderAndTypeDTO(Pageable pageable) {
//        Page<CustomerAccount> customerAccounts = customerAccountRepository.findAll(pageable);
//        Page<CustomerAccountsGroupByGenderAndTypeDTO > p = customerAccounts.stream()
//            .map(new CustomerAccountsGroupByGenderAndTypeDTO() )
//            .collect(Collectors.toList()            );


        return null;
    }*/

    /**
     * get a branchCode
     *
     * @param p
     * @param branchCode
     * @return the list of persisted entity
     */
    @Override
    public Page<CustomerAccountDTO> findByBranchCode(Pageable p, String branchCode) {
        log.debug("Request to delete CustomerAccount : {}");
        return customerAccountRepository.findByBranchCode(p, branchCode)
            .map(customerAccountMapper::toDto);
    }

    /**
     * get genderType and accountType
     *
     * @param genderType
     * @param accountType
     * @return the list of persisted entity
     */
    @Override
    public Page<CustomerAccountDTO> findByGenderAndAccountType(Pageable pageable, GenderType genderType, AccountType accountType) {
        log.debug("Request to delete CustomerAccount : {}");
        return customerAccountRepository.findByGenderTypeAndAccountType(pageable, genderType, accountType)
            .map(customerAccountMapper::toDto);
    }

    /**
     * @return
     */

    @Override
    public Optional<Map<AccountType, Long>> countByAccountTypeAndByBank() {
        log.debug("Request to delete CustomerAccount : {}");

        List<CustomerAccount> page = customerAccountRepository.findAll();

        Map<AccountType, Long> countAccount = page.stream()
            .map(customerAccountMapper::toDto)
            .collect(
                Collectors.groupingBy(CustomerAccountDTO::getAccountType, Collectors.counting()));

        return Optional.of(countAccount);
    }


    @Override
    public Page<CustomerAccount> findAllByBank(String branchCode, Pageable pageable) {
        Bank bank = bankService.findBankByBranchCode(branchCode);
        Page<CustomerAccount> customerAccounts = customerAccountRepository.findAllByBank(bank, pageable);
        return customerAccounts;
    }

    @Override
    public Page<CustomerAccount> findAllByBankAndAccountType(String branchCode, AccountType accountType, Pageable pageable) {
        Bank bank = bankService.findBankByBranchCode(branchCode);
        Page<CustomerAccount> customerAccounts= customerAccountRepository.findAllByBankAndAccountType(bank,accountType,pageable);
        return customerAccounts;
    }


    @Override
    public Optional<Map<String, Map<AccountType, Long>>> countByAccountTypeByBranchCode() {
        log.debug("Request to delete CustomerAccount : {}");

        List<CustomerAccount> customerAccounts = customerAccountRepository.findAll();

        Map<String, Map<AccountType, Long>> result = customerAccounts.stream()
            .collect(Collectors.groupingBy(ca -> ca.getBank().getBranchCode(),
                Collectors.groupingBy(ca -> ca.getAccountType(), Collectors.counting())));

        return Optional.of(result);
    }


/*    public Optional<Map<Bank ,Map<AccountType, Long>>> countByAccountTypeAndBranchCode() {
        log.debug("Request to delete CustomerAccount : {}");

        List<CustomerAccount> customerAccountList =
            customerAccountRepository.findAll();

        Map<Bank ,Map<AccountType, Long>> result = customerAccountList.stream()
            .map(customerAccountFilterMapper::toDto)
            .collect(
                Collectors.groupingBy(CustomerAccountFilterDTO::getBranchCode,
                    Collectors.groupingBy(CustomerAccountFilterDTO::getAccountType)),
                Collectors.counting());
        return Optional.of(result);
    }*/


}
