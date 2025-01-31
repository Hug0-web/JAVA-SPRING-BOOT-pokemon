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
        List<Cards> fourStarCards = allCards.stream()
            .filter(card -> "4 étoiles".equals(card.getRarity()))
            .collect(Collectors.toList());
        List<Cards> thirdStarCards = allCards.stream()
            .filter(card -> "3 étoiles".equals(card.getRarity()))
            .collect(Collectors.toList());
        List<Cards> twoStarCards = allCards.stream()
            .filter(card -> "2 étoiles".equals(card.getRarity()))
            .collect(Collectors.toList());
        List<Cards> oneStarCards = allCards.stream()
            .filter(card -> "1 étoile".equals(card.getRarity()))
            .collect(Collectors.toList());
        

        Collections.shuffle(fiveStarCards);
        Collections.shuffle(fourStarCards);
        Collections.shuffle(thirdStarCards);
        Collections.shuffle(twoStarCards);
        Collections.shuffle(oneStarCards);
        
        List<Cards> randomCards = new ArrayList<>();
        int random = (int)(Math.random()+ 1) * 100;

        if (!fiveStarCards.isEmpty() && random < 2) {
            randomCards.add(fiveStarCards.get(0));
        }
        if (!fourStarCards.isEmpty() && random < 3) {
            randomCards.add(fourStarCards.get(0));
        }
        if (!thirdStarCards.isEmpty() && random < 5) {
            randomCards.add(thirdStarCards.get(0));
        }
        if (!twoStarCards.isEmpty() && random < 20 ) {
            randomCards.add(twoStarCards.get(0));
        }
        if (!oneStarCards.isEmpty() && random < 70) {
            randomCards.add(oneStarCards.get(0));
        }

        
        while (randomCards.size() < 5) {
            List<Cards> remainingCards = new ArrayList<>();
            remainingCards.addAll(fiveStarCards);
            remainingCards.addAll(fourStarCards);
            remainingCards.addAll(thirdStarCards);
            remainingCards.addAll(twoStarCards);
            remainingCards.addAll(oneStarCards);
        
            remainingCards.removeAll(randomCards);
        
            if (remainingCards.isEmpty()) {
                break;
            }
        
            Collections.shuffle(remainingCards);
            Cards card = remainingCards.get(0);
            randomCards.add(card);
        }
        
        return new ResponseEntity<>(randomCards, HttpStatus.OK);
    }
}