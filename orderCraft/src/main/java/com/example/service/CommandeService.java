package com.example.service;

import com.example.entity.Commande;
import com.example.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(int id) {
        return commandeRepository.findById(id);
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }
    public double calculateTotalPrice(Commande commande) {
        // Implement your logic to calculate the total price based on the articles in the commande
        // You may iterate through commandeArticles and sum up the prices
        // For simplicity, assuming there is a getPrice() method in CommandeArticle
        return commande.getCommandeArticles().stream()
                .mapToDouble(commandeArticle -> commandeArticle.getArticle().getPrix() * commandeArticle.getQuantity())
                .sum();
    }

    public Commande addCommande(Commande commande) {
        // Implement your logic to add a new commande
        // You may want to set the dateC, code, and stats based on your requirements
        commande.setPrixTotale((float) calculateTotalPrice(commande));
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(int id, Commande updatedCommande) {
        // Implement your logic to update an existing commande by ID
        // You may want to recalculate the total price before saving
        Commande existingCommande = commandeRepository.findById(id).orElse(null);

        if (existingCommande != null) {
            existingCommande.setClient(updatedCommande.getClient());
            existingCommande.setCommandeArticles(updatedCommande.getCommandeArticles());
            existingCommande.setDateC(updatedCommande.getDateC());
            existingCommande.setStats(updatedCommande.getStats());
            existingCommande.setPrixTotale((float) calculateTotalPrice(existingCommande));

            return commandeRepository.save(existingCommande);
        }

        return null; // Handle not found scenario according to your requirements
    }
}
