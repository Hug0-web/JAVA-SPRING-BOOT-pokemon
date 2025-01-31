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
import java.util.stream.Collectors;

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
        

        List<Cards> fiveStarCards = allCards.stream()
            .filter(card -> "5 étoiles".equals(card.getRarity()))
            .collect(Collectors.toList());
        
        List<Cards> otherCards = allCards.stream()
            .filter(card -> !"5 étoiles".equals(card.getRarity()))
            .collect(Collectors.toList());
        

        Collections.shuffle(fiveStarCards);
        Collections.shuffle(otherCards);
        
        List<Cards> randomCards = new ArrayList<>();
        
        if (!fiveStarCards.isEmpty() && Math.random() < 0.2) {
            randomCards.add(fiveStarCards.get(0));
        }
        
        while (randomCards.size() < 5 && !otherCards.isEmpty()) {
            Cards card = otherCards.remove(0);
            if (!randomCards.contains(card)) {
                randomCards.add(card);
            }
        }
        
        return new ResponseEntity<>(randomCards, HttpStatus.OK);
    }
}