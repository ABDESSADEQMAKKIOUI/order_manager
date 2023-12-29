package com.example.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private com.example.entity.Client client;

    @OneToMany(mappedBy = "commande")
    private List<CommandeArticle> commandeArticles;

    private LocalDate dateC;



    private String stats;
    private double prixTotale ;

    public Commande() {
        // Default constructor is required by JPA
    }

    public Commande(Client client, LocalDate dateC) {
        this.client = client;
        this.dateC = dateC;
        this.stats = "Pending";
    }

    public double getPrixTotale() {
        return prixTotale;
    }

    public void setPrixTotale(double prixTotale) {
        this.prixTotale = prixTotale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public List<CommandeArticle> getCommandeArticles() {
        return commandeArticles;
    }

    public void setCommandeArticles(List<CommandeArticle> commandeArticles) {
        this.commandeArticles = commandeArticles;
    }
    public LocalDate getDateC() {
        return dateC;
    }

    public void setDateC(LocalDate dateC) {
        this.dateC = dateC;
    }


    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
