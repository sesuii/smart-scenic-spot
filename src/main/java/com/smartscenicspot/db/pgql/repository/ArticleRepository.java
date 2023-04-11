package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}