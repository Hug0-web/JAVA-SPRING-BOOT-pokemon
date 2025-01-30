package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Attaque;
import fr.efrei.pokemon_tcg.services.IAttaqueService;
import fr.efrei.pokemon_tcg.services.implementations.AttaqueServiceImpl;
import fr.efrei.pokemon_tcg.dto.CreateAttaque;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attaques")
public class AttaqueController {

    private final IAttaqueService attaqueService;

    public AttaqueController(AttaqueServiceImpl attaqueService) {
        this.attaqueService = attaqueService;
    }

    @GetMapping
    public ResponseEntity<List<Attaque>> getAll() {
        return new ResponseEntity<>(attaqueService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Attaque> getById(@PathVariable String uuid) {
        Attaque attaque = attaqueService.findById(uuid);
        if(attaque == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attaque, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAttaque(@Valid @RequestBody CreateAttaque createAttaque) {
        attaqueService.create(createAttaque);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateAttaque(@PathVariable String uuid, @RequestBody Attaque attaque) {
        boolean isModified = attaqueService.update(uuid, attaque);
        if(!isModified) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteAttaque(@PathVariable String uuid) {
        boolean isDeleted = attaqueService.delete(uuid);
        if(!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
