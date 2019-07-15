package com.my.app.web.rest;

import com.my.app.domain.CustomerAccount;
import com.my.app.domain.enumeration.AccountType;
import com.my.app.service.CustomerAccountService;
import com.my.app.service.dto.CustomerAccountDTO;
import com.my.app.service.dto.CustomerAccountFilterDTO;
import com.my.app.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing {@link CustomerAccount}.
 */
@RestController
@RequestMapping("/api")
public class CustomerAccountResource {

    private final Logger log = LoggerFactory.getLogger(CustomerAccountResource.class);

    private static final String ENTITY_NAME = "customerAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerAccountService customerAccountService;

    public CustomerAccountResource(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    /**
     * {@code POST  /customer-accounts} : Create a new customerAccount.
     *
     * @param customerAccountDTO the customerAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerAccountDTO, or with status {@code 400 (Bad Request)} if the customerAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-accounts")
    public ResponseEntity<CustomerAccountDTO> createCustomerAccount(@RequestBody CustomerAccountDTO customerAccountDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerAccount : {}", customerAccountDTO);
        if (customerAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerAccountDTO result = customerAccountService.save(customerAccountDTO);
        return ResponseEntity.created(new URI("/api/customer-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-accounts} : Updates an existing customerAccount.
     *
     * @param customerAccountDTO the customerAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerAccountDTO,
     * or with status {@code 400 (Bad Request)} if the customerAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-accounts")
    public ResponseEntity<CustomerAccountDTO> updateCustomerAccount(@RequestBody CustomerAccountDTO customerAccountDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerAccount : {}", customerAccountDTO);
        if (customerAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerAccountDTO result = customerAccountService.save(customerAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-accounts} : get all the customerAccounts.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerAccounts in body.
     */
    @GetMapping("/customer-accounts")
    public ResponseEntity<List<CustomerAccountDTO>> getAllCustomerAccounts(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of CustomerAccounts");
        Page<CustomerAccountDTO> page = customerAccountService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-accounts/:id} : get the "id" customerAccount.
     *
     * @param id the id of the customerAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-accounts/{id}")
    public ResponseEntity<CustomerAccountDTO> getCustomerAccount(@PathVariable Long id) {
        log.debug("REST request to get CustomerAccount : {}", id);
        Optional<CustomerAccountDTO> customerAccountDTO = customerAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerAccountDTO);
    }

    /**
     * {@code DELETE  /customer-accounts/:id} : delete the "id" customerAccount.
     *
     * @param id the id of the customerAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-accounts/{id}")
    public ResponseEntity<Void> deleteCustomerAccount(@PathVariable Long id) {
        log.debug("REST request to delete CustomerAccount : {}", id);
        customerAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * get a branchCode
     *
     * @param pageable
     * @param branchCode
     * @param uriBuilder
     * @return the list of persisted entity
     */
    @GetMapping("/customer-accounts/by-branch-code")
    public ResponseEntity<List<CustomerAccountDTO>> getCustomerAccountByBracnhCode(Pageable pageable,
                                                                                   @RequestBody String branchCode, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to delete SideEffect : {}");
        Page<CustomerAccountDTO> page = customerAccountService.findByBranchCode(pageable, branchCode);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder, page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/customer-accounts/by-gender-type-and-account-type")
    public ResponseEntity<List<CustomerAccountDTO>> getCustomerAccountByGenderTypeAndAccountType(Pageable pageable,
                                                                                                 @RequestBody CustomerAccountFilterDTO customerAccountFilterDTO, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to delete SideEffect : {}");
        Page<CustomerAccountDTO> page = customerAccountService.findByGenderAndAccountType(pageable,
            customerAccountFilterDTO.getGenderType(),
            customerAccountFilterDTO.getAccountType());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder, page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/customer-accounts/counting-by-account-type")
    public ResponseEntity<Map<AccountType, Long>> getCustomerAccountCountByAccountType() {
        log.debug("REST request to delete SideEffect : {}");
        Optional<Map<AccountType, Long>> result = customerAccountService.countByCustomerAccountType();
        return ResponseUtil.wrapOrNotFound(result);
    }
}
