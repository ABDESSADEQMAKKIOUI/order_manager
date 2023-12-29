package com.example.repository;

import com.example.entity.CommandeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeArticleRepository extends JpaRepository<CommandeArticle, Integer> {
    @Query("SELECT ca.article, COUNT(ca) " +
            "FROM CommandeArticle ca " +
            "GROUP BY ca.article " +
            "ORDER BY COUNT(ca) DESC")
    List<Object[]> findMostAddedArticles();
}
