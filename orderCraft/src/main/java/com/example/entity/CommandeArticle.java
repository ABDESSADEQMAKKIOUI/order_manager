package com.example.entity;



import javax.persistence.*;

@Entity
@Table(name = "commande_article")
public class CommandeArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    // additional column
    private int quantity; // You can replace this with the actual type you need

    public CommandeArticle() {
        // Default constructor is required by JPA
    }

    public CommandeArticle(Commande commande, Article article, int quantity) {
        this.commande = commande;
        this.article = article;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
