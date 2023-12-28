package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;
    private String name;
    private float prix ;
    @OneToMany(mappedBy = "article")
    private List<CommandeArticle> commandeArticles;

    public Article() {
        // Default constructor is required by JPA
    }

    public Article(int quantity, String name, float prix) {
        this.quantity = quantity;
        this.name = name;
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
