package com.my.app.service.impl;

import com.my.app.domain.Article;
import com.my.app.domain.Customer;
import com.my.app.domain.CustomerAccount;
import com.my.app.repository.ArticleRepository;
import com.my.app.repository.CustomerRepository;
import com.my.app.service.ArticleService;
import com.my.app.service.dto.ArticleDTO;
import com.my.app.service.mapper.ArticleMapper;
import com.my.app.service.mapper.CustomerAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Article}.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    @Autowired
    private CustomerRepository customerRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    /**
     * Save a article.
     *
     * @param articleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        log.debug("Request to save Article : {}", articleDTO);
        Article article = articleMapper.toEntity(articleDTO);
        article = articleRepository.save(article);
        return articleMapper.toDto(article);
    }

    /**
     * Get all the articles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Articles");
        return articleRepository.findAll(pageable)
            .map(articleMapper::toDto);
    }


    /**
     * Get one article by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleDTO> findOne(Long id) {
        log.debug("Request to get Article : {}", id);
        return articleRepository.findById(id)
            .map(articleMapper::toDto);
    }

    /**
     * Delete the article by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Article : {}", id);
        articleRepository.deleteById(id);
    }

    public Page<ArticleDTO> findCustomizedArticle(Pageable page) {
        return articleRepository.findCustomizedArticle(page)
            .map(articleMapper::toDto);
    }

    @Override
    public List<ArticleDTO> findArticleByCustomerIdNumberAndAccountNumber(Integer idNumber, String accountNumber) {
        Optional<Customer> customer = customerRepository.findByIdNumber(idNumber);
        Optional<CustomerAccount> customerAccount = customer.get().getCustomerAccounts().stream()
            .filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst();
        List articles = new ArrayList(customerAccount.get().getArticles());
        return  articleMapper.toDto(articles);
    }
}
