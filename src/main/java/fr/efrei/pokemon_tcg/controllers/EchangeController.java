package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.services.EchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/echanges")
public class EchangeController {
    private final EchangeService echangeService;

    @Autowired
    public EchangeController(EchangeService echangeService) {
        this.echangeService = echangeService;
    }

    public static class EchangeRequest {
        private String dresseur1Id;
        private String dresseur2Id;
        private String carteD1Id;
        private String carteD2Id;

        public String getDresseur1Id() { 
            return dresseur1Id; 
        }
        public void setDresseur1Id(String dresseur1Id) { 
            this.dresseur1Id = dresseur1Id; 
        }
        public String getDresseur2Id() { 
            return dresseur2Id; 
        }
        public void setDresseur2Id(String dresseur2Id) { 
            this.dresseur2Id = dresseur2Id; 
        }
        public String getCarteD1Id() { 
            return carteD1Id; 
        }
        public void setCarteD1Id(String carteD1Id) { 
            this.carteD1Id = carteD1Id; 
        }
        public String getCarteD2Id() { 
            return carteD2Id; 
        }
        public void setCarteD2Id(String carteD2Id) { 
            this.carteD2Id = carteD2Id; 
        }
    }

    @PostMapping
    public ResponseEntity<?> echangerCartes(@RequestBody EchangeRequest request) {
        try {
            boolean echangeReussi = echangeService.echangerCartes(
                request.getDresseur1Id(), 
                request.getDresseur2Id(), 
                request.getCarteD1Id(), 
                request.getCarteD2Id()
            );
            return ResponseEntity.ok("Échange réussi");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}