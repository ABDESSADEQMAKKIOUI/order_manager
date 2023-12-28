package com.example.repository;

import com.example.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    // You can add custom query methods if needed
}
