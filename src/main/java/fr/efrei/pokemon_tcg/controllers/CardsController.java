package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Cards;
import fr.efrei.pokemon_tcg.services.ICardsService;
import fr.efrei.pokemon_tcg.services.implementations.CardsServiceImpl;
import fr.efrei.pokemon_tcg.dto.CreateCards;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    private final ICardsService cardsService;

    public CardsController(CardsServiceImpl cardsService) {
        this.cardsService = cardsService;
    }

    @GetMapping
    public ResponseEntity<List<Cards>> getAll() {
        return new ResponseEntity<>(cardsService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Cards> getById(@PathVariable String uuid) {
        Cards card = cardsService.findById(uuid);
        if(card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCard(@Valid @RequestBody CreateCards createCards) {
        cardsService.create(createCards);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateCard(@PathVariable String uuid, @RequestBody Cards card) {
        boolean isModified = cardsService.update(uuid, card);
        if(!isModified) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteCard(@PathVariable String uuid) {
        boolean isDeleted = cardsService.delete(uuid);
        if(!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
