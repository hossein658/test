package com.my.app.repository;

import com.my.app.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


/**
 * Spring Data  repository for the Article entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select article from Article article")
    Page<Article> findCustomizedArticle(Pageable page);


    @Query("select ca from Article ca " +
        "inner join ca.customerAccount c " +
        "where  ca.dateTransaction between  :startdatetransaction and :enddatetransaction " +
        "and  c.accountNumber = :customeraccount ")
    Page<Article> getListTransaction(Pageable page,
                                     @Param("startdatetransaction") LocalDate startDateTransaction,
                                     @Param("enddatetransaction") LocalDate enddatetransaction,
                                     @Param("customeraccount") String customerAccount);

}
