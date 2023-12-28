package com.example.controller;

import com.example.entity.Article;
import com.example.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/list")
    public String getAllArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "/Article/list"; // Assuming you have a Thymeleaf template named "list.html"
    }
    @PostMapping("/addArticle")
    public String addArticle(
            @RequestParam("quantity") int quantity,
            @RequestParam("name") String name,
            @RequestParam("prix") float prix) {
           Article article = new Article(quantity,name,prix)  ;
           articleService.createArticle(article);
        return "redirect:/Article/AddArticle";
    }
    @GetMapping("/{id}")
    public String getArticleById(@PathVariable int id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article/details"; // Assuming you have a Thymeleaf template named "details.html"
    }

    @GetMapping("/addArticleform")
    public String showAddArticleForm() {
        return "/Article/AddArticle";
    }
    @GetMapping("/updateArticleForm/{id}")
    public String showUpdateArticleForm(@PathVariable("id") int id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "/Article/updateArticleForm";
    }
        @PostMapping("/updateArticle")
    public String updateArticle(@PathVariable int id, @ModelAttribute Article updatedArticle) {
        articleService.updateArticle(id, updatedArticle);
        return "redirect:/Article/list";
    }



    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable("id") int id) {
        articleService.deleteArticle(id);
        return "redirect:/Article/list";
    }
}
