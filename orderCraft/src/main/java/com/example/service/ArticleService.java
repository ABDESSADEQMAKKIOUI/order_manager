package com.example.service;

import com.example.entity.Article;
import com.example.repository.ArticleRepository;
import com.example.repository.CommandeArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommandeArticleRepository commandeArticleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CommandeArticleRepository commandeArticleRepository) {
        this.articleRepository = articleRepository;
        this.commandeArticleRepository = commandeArticleRepository;
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
    public Optional<Article> getArticleByName(String name) {
        return articleRepository.findByName(name);
    }

    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }
    public List<Article> getMostAddedArticlesToCommandes() {
        // Fetch a list of articles ordered by the count of associated CommandeArticles

        List<Object[]> result = commandeArticleRepository.findMostAddedArticles();

        // Map the result to Article entities
        List<Article> mostAddedArticles = result.stream()
                .map(objArray -> (Article) objArray[0])
                .collect(Collectors.toList());

        return mostAddedArticles;
    }
}

