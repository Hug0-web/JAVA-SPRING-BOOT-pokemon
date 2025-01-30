package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Cards;
import fr.efrei.pokemon_tcg.services.ICardsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tirages")
public class TiragesController {

    private final ICardsService cardsService;

    public TiragesController(ICardsService cardsService) {
        this.cardsService = cardsService;
    }

    @GetMapping("/random")
    public ResponseEntity<List<Cards>> getTirageAleatoire() {
      
        List<Cards> allCards = cardsService.findAll();
        
        Collections.shuffle(allCards);
        
        List<Cards> randomCards = allCards.stream().limit(5).toList();
        
        return new ResponseEntity<>(randomCards, HttpStatus.OK);
    }
}