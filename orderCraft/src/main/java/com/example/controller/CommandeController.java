package com.example.controller;

import com.example.entity.Article;
import com.example.entity.Commande;
import com.example.entity.CommandeArticle;
import com.example.repository.CommandeArticleRepository;
import com.example.service.ArticleService;
import com.example.service.ClientService;
import com.example.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private CommandeArticleRepository commandeArticleRepository;
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
        System.out.println("hello from /commandes/addForm");
        model.addAttribute("commande", new Commande());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("articles", articleService.getAllArticles());
        return "commande/AddCommande";
    }
    @PostMapping("/addCommande")
    public String addCommande(@RequestParam("articles" )String[] articles ,
                              @RequestParam("quantites") int[] quontities ,
                              @RequestParam("client") String client) {
   Commande commande= new Commande(clientService.getClientByName(client).get(), LocalDate.now());
        commandeService.addCommande(commande);
        ArrayList<Article> listeArticles = new ArrayList<>();
        int[] listequontite = new int[15];
        for (int i = 0; i < articles.length; i++) {
            Article article = articleService.getArticleByName(articles[i]).get();
            CommandeArticle commandeArticle = new CommandeArticle(commande, article,quontities[i]);
            commandeArticleRepository.save(commandeArticle);
            article.setQuantity(article.getQuantity()-quontities[i]);
            articleService.updateArticle(article.getId(),article);
        }
        commande.setPrixTotale(commandeService.calculateTotalPrice(commande));
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
        existingCommande.setStats(updatedCommande.getStats());

        existingCommande.setPrixTotale((float) commandeService.calculateTotalPrice(existingCommande));

        commandeService.updateCommande(id,existingCommande);

        return "redirect:/commande/GereCommande"; // Redirect to the list page or another page as needed
    }
    @GetMapping("/raport")
    public String generateCommandReport(Model model) {
        List<Commande> commandes = commandeService.getAllCommandes();
        List<Article> mostAddedArticles = articleService.getMostAddedArticlesToCommandes(); // Adjust the method according to your service

        model.addAttribute("commandes", commandes);
        model.addAttribute("mostAddedArticles", mostAddedArticles);
        return "commande/rapport";
    }
}
