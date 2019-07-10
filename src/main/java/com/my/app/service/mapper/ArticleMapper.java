package com.my.app.service.mapper;

import com.medicai.pillpal.domain.*;
import com.my.app.domain.Article;
import com.my.app.service.dto.ArticleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerAccountMapper.class})
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

    @Mapping(source = "customerAccount.id", target = "customerAccountId")
    ArticleDTO toDto(Article article);

    @Mapping(source = "customerAccountId", target = "customerAccount")
    Article toEntity(ArticleDTO articleDTO);

    default Article fromId(Long id) {
        if (id == null) {
            return null;
        }
        Article article = new Article();
        article.setId(id);
        return article;
    }
}
