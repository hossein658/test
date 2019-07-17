package com.my.app.service;

import com.my.app.domain.Article;
import com.my.app.service.dto.ArticleDTO;
import com.my.app.service.dto.CustomerArticleReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Article}.
 */
public interface ArticleService {

    /**
     * Save a article.
     *
     * @param articleDTO the entity to save.
     * @return the persisted entity.
     */
    ArticleDTO save(ArticleDTO articleDTO);

    /**
     * Get all the articles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ArticleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" article.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArticleDTO> findOne(Long id);

    /**
     * Delete the "id" article.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<ArticleDTO> findCustomizedArticle(Pageable page);

    Page<ArticleDTO> getListTransaction(Pageable p, CustomerArticleReportDTO customerArticleReportDTO);
}
