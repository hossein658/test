package com.my.app.web.rest;

import com.my.app.PillpalServerApp;
import com.my.app.domain.CustomerAccount;
import com.my.app.repository.CustomerAccountRepository;
import com.my.app.service.CustomerAccountService;
import com.my.app.service.dto.CustomerAccountDTO;
import com.my.app.service.mapper.CustomerAccountMapper;
import com.my.app.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.my.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.my.app.domain.enumeration.Status;
import com.my.app.domain.enumeration.AccountType;
/**
 * Integration tests for the {@Link CustomerAccountResource} REST controller.
 */
@SpringBootTest(classes = PillpalServerApp.class)
public class CustomerAccountResourceIT {

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.INACTIVE;

    private static final AccountType DEFAULT_ACCOUNT_TYPE = AccountType.LONG;
    private static final AccountType UPDATED_ACCOUNT_TYPE = AccountType.SHORT;

    private static final Long DEFAULT_BALANCE = 1L;
    private static final Long UPDATED_BALANCE = 2L;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCustomerAccountMockMvc;

    private CustomerAccount customerAccount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerAccountResource customerAccountResource = new CustomerAccountResource(customerAccountService);
        this.restCustomerAccountMockMvc = MockMvcBuilders.standaloneSetup(customerAccountResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerAccount createEntity(EntityManager em) {
        CustomerAccount customerAccount = new CustomerAccount()
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .status(DEFAULT_STATUS)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .balance(DEFAULT_BALANCE);
        return customerAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerAccount createUpdatedEntity(EntityManager em) {
        CustomerAccount customerAccount = new CustomerAccount()
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .status(UPDATED_STATUS)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .balance(UPDATED_BALANCE);
        return customerAccount;
    }

    @BeforeEach
    public void initTest() {
        customerAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerAccount() throws Exception {
        int databaseSizeBeforeCreate = customerAccountRepository.findAll().size();

        // Create the CustomerAccount
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);
        restCustomerAccountMockMvc.perform(post("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerAccount in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerAccount testCustomerAccount = customerAccountList.get(customerAccountList.size() - 1);
        assertThat(testCustomerAccount.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testCustomerAccount.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCustomerAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testCustomerAccount.getBalance()).isEqualTo(DEFAULT_BALANCE);
    }

    @Test
    @Transactional
    public void createCustomerAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerAccountRepository.findAll().size();

        // Create the CustomerAccount with an existing ID
        customerAccount.setId(1L);
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerAccountMockMvc.perform(post("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerAccount in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomerAccounts() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);

        // Get all the customerAccountList
        restCustomerAccountMockMvc.perform(get("/api/customer-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())));
    }
    
    @Test
    @Transactional
    public void getCustomerAccount() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);

        // Get the customerAccount
        restCustomerAccountMockMvc.perform(get("/api/customer-accounts/{id}", customerAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerAccount.getId().intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE.toString()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerAccount() throws Exception {
        // Get the customerAccount
        restCustomerAccountMockMvc.perform(get("/api/customer-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerAccount() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);

        int databaseSizeBeforeUpdate = customerAccountRepository.findAll().size();

        // Update the customerAccount
        CustomerAccount updatedCustomerAccount = customerAccountRepository.findById(customerAccount.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerAccount are not directly saved in db
        em.detach(updatedCustomerAccount);
        updatedCustomerAccount
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .status(UPDATED_STATUS)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .balance(UPDATED_BALANCE);
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(updatedCustomerAccount);

        restCustomerAccountMockMvc.perform(put("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerAccount in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeUpdate);
        CustomerAccount testCustomerAccount = customerAccountList.get(customerAccountList.size() - 1);
        assertThat(testCustomerAccount.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testCustomerAccount.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCustomerAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testCustomerAccount.getBalance()).isEqualTo(UPDATED_BALANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerAccount() throws Exception {
        int databaseSizeBeforeUpdate = customerAccountRepository.findAll().size();

        // Create the CustomerAccount
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerAccountMockMvc.perform(put("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerAccount in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerAccount() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);

        int databaseSizeBeforeDelete = customerAccountRepository.findAll().size();

        // Delete the customerAccount
        restCustomerAccountMockMvc.perform(delete("/api/customer-accounts/{id}", customerAccount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerAccount.class);
        CustomerAccount customerAccount1 = new CustomerAccount();
        customerAccount1.setId(1L);
        CustomerAccount customerAccount2 = new CustomerAccount();
        customerAccount2.setId(customerAccount1.getId());
        assertThat(customerAccount1).isEqualTo(customerAccount2);
        customerAccount2.setId(2L);
        assertThat(customerAccount1).isNotEqualTo(customerAccount2);
        customerAccount1.setId(null);
        assertThat(customerAccount1).isNotEqualTo(customerAccount2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerAccountDTO.class);
        CustomerAccountDTO customerAccountDTO1 = new CustomerAccountDTO();
        customerAccountDTO1.setId(1L);
        CustomerAccountDTO customerAccountDTO2 = new CustomerAccountDTO();
        assertThat(customerAccountDTO1).isNotEqualTo(customerAccountDTO2);
        customerAccountDTO2.setId(customerAccountDTO1.getId());
        assertThat(customerAccountDTO1).isEqualTo(customerAccountDTO2);
        customerAccountDTO2.setId(2L);
        assertThat(customerAccountDTO1).isNotEqualTo(customerAccountDTO2);
        customerAccountDTO1.setId(null);
        assertThat(customerAccountDTO1).isNotEqualTo(customerAccountDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerAccountMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerAccountMapper.fromId(null)).isNull();
    }
}
