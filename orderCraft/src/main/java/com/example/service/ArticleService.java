package com.example.service;

import com.example.entity.Article;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(int id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElse(null);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(int id, Article updatedArticle) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article existingArticle = optionalArticle.get();
            existingArticle.setQuantity(updatedArticle.getQuantity());
            existingArticle.setName(updatedArticle.getName());
            return articleRepository.save(existingArticle);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }
}

