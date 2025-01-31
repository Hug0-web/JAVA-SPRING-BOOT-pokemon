package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Cards;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.repositories.CardsRepository;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.EchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class EchangeService {
    private final EchangeRepository echangeRepository;
    private final DresseurRepository dresseurRepository;
    private final CardsRepository cardsRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EchangeService(
        EchangeRepository echangeRepository,
        DresseurRepository dresseurRepository,
        CardsRepository cardsRepository,
        JdbcTemplate jdbcTemplate
    ) {
        this.echangeRepository = echangeRepository;
        this.dresseurRepository = dresseurRepository;
        this.cardsRepository = cardsRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public boolean echangerCartes(String dresseur1Id, String dresseur2Id, String carteD1Id, String carteD2Id) {
        if (dresseur1Id == null || dresseur2Id == null || carteD1Id == null || carteD2Id == null) {
            throw new RuntimeException("Tous les paramètres sont obligatoires");
        }

        Dresseur dresseur1 = dresseurRepository.findById(dresseur1Id)
            .orElseThrow(() -> new RuntimeException("Dresseur 1 non trouvé"));
        Dresseur dresseur2 = dresseurRepository.findById(dresseur2Id)
            .orElseThrow(() -> new RuntimeException("Dresseur 2 non trouvé"));

        Cards carte1 = cardsRepository.findById(carteD1Id)
            .orElseThrow(() -> new RuntimeException("Carte 1 non trouvée"));
        Cards carte2 = cardsRepository.findById(carteD2Id)
            .orElseThrow(() -> new RuntimeException("Carte 2 non trouvée"));

        boolean carte1Appartient = dresseur1.getCards().stream()
            .anyMatch(c -> c.getUuid().equals(carteD1Id));
        boolean carte2Appartient = dresseur2.getCards().stream()
            .anyMatch(c -> c.getUuid().equals(carteD2Id));

        if (!carte1Appartient) {
            throw new RuntimeException("La carte 1 n'appartient pas au dresseur 1");
        }
        if (!carte2Appartient) {
            throw new RuntimeException("La carte 2 n'appartient pas au dresseur 2");
        }


        String deleteQuery1 = "DELETE FROM dresseur_cards WHERE dresseur_uuid = ? AND cards_uuid = ?";
        String deleteQuery2 = "DELETE FROM dresseur_cards WHERE dresseur_uuid = ? AND cards_uuid = ?";
        
        jdbcTemplate.update(deleteQuery1, dresseur1.getUuid(), carteD1Id);
        jdbcTemplate.update(deleteQuery2, dresseur2.getUuid(), carteD2Id);

        String insertQuery1 = "INSERT INTO dresseur_cards (dresseur_uuid, cards_uuid) VALUES (?, ?)";
        String insertQuery2 = "INSERT INTO dresseur_cards (dresseur_uuid, cards_uuid) VALUES (?, ?)";
        
        jdbcTemplate.update(insertQuery1, dresseur1.getUuid(), carteD2Id);
        jdbcTemplate.update(insertQuery2, dresseur2.getUuid(), carteD1Id);

        dresseur1.getCards().remove(carte1);
        dresseur1.getCards().add(carte2);
        dresseur2.getCards().remove(carte2);
        dresseur2.getCards().add(carte1);

        Echange nouvelEchange = new Echange();
        nouvelEchange.setDresseur1(dresseur1);
        nouvelEchange.setDresseur2(dresseur2);
        nouvelEchange.setDateEchange(LocalDate.now());
        echangeRepository.save(nouvelEchange);

  
        dresseurRepository.save(dresseur1);
        dresseurRepository.save(dresseur2);

        return true;
    }
}