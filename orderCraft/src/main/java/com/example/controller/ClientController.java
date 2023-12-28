package com.example.controller;

import com.example.entity.Article;
import com.example.entity.Client;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/addClientform")
    public String showAddClientForm() {
        return "/client/AddClient";
    }
    @PostMapping("/add")
    public String saveClient(
            @RequestParam("email") String email
            , @RequestParam("name") String name) {
        Client client = new Client(name , email);
         clientService.saveClient(client);
        return "redirect:/client/GereClient";
    }
    @PostMapping("/update")
    public String updateClient(@ModelAttribute Client client) {
        System.out.println(client);
        clientService.updateClient(client);
        return "redirect:/clients/";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Client client = clientService.getClientById(id).get();
        model.addAttribute("client", client);
        return "client/UpdateClient";
    }
    @GetMapping ("delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") int id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
