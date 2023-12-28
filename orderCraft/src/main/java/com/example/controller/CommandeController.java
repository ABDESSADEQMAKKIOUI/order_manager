package com.example.controller;

import com.example.entity.Commande;
import com.example.service.ArticleService;
import com.example.service.ClientService;
import com.example.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    @Autowired
    private ClientService clientService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable int id) {
        Optional<Commande> commande = commandeService.getCommandeById(id);
        return commande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/addForm")
    public String showAddCommandeForm(Model model) {
        model.addAttribute("commande", new Commande());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("articles", articleService.getAllArticles());
        return "commande/addCommandeForm";
    }
    @PostMapping("/addCommande")
    public String addCommande(Commande commande) {
        // You can add any additional logic here before saving the commande
        // For example, you may want to set the dateC, code, and stats based on your requirements
        commandeService.addCommande(commande);
        return "redirect:/commande/GereCommande"; // Redirect to the list page or another page as needed
    }

    @GetMapping("/list")
    public String listCommandes(Model model) {
        model.addAttribute("commandes", commandeService.getAllCommandes());
        return "commande/GereCommande";
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable int id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/updateForm/{id}")
    public String showUpdateCommandeForm(@PathVariable int id, Model model) {
        Commande commande = commandeService.getCommandeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid commande id: " + id));

        model.addAttribute("commande", commande);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("articles", articleService.getAllArticles());

        return "updateCommandeForm";
    }

    // Endpoint to handle the update form submission
    @PostMapping("/updateCommande/{id}")
    public String updateCommande(@PathVariable int id, Commande updatedCommande) {
        Commande existingCommande = commandeService.getCommandeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid commande id: " + id));

        // Set the properties of existingCommande with the updated values
        existingCommande.setClient(updatedCommande.getClient());
        existingCommande.setCommandeArticles(updatedCommande.getCommandeArticles());
        existingCommande.setDateC(updatedCommande.getDateC());
        existingCommande.setCode(updatedCommande.getCode());
        existingCommande.setStats(updatedCommande.getStats());

        // You may want to recalculate the total price here if needed
        existingCommande.setPrixTotale((float) commandeService.calculateTotalPrice(existingCommande));

        commandeService.updateCommande(id,existingCommande);

        return "redirect:/commande/GereCommande"; // Redirect to the list page or another page as needed
    }
}
