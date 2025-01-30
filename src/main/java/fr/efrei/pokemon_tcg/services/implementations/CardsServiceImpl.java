package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Cards;
import fr.efrei.pokemon_tcg.repositories.CardsRepository;
import fr.efrei.pokemon_tcg.services.ICardsService;
import fr.efrei.pokemon_tcg.dto.CreateCards;
import fr.efrei.pokemon_tcg.models.Attaque;
import fr.efrei.pokemon_tcg.services.IAttaqueService;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class CardsServiceImpl implements ICardsService {

    private final CardsRepository repository;
    private final IAttaqueService attaqueService;

    public CardsServiceImpl(CardsRepository repository, IAttaqueService attaqueService) {
        this.repository = repository;
        this.attaqueService = attaqueService;
    }

    @Override
    public List<Cards> findAll() {
        return repository.findAll();
    }

    @Override
    public Cards findById(String uuid) {
        return repository.findById(uuid).orElse(null);
    }

    @Override
    public void create(CreateCards createCards) {
        Cards card = new Cards();
        card.setNom(createCards.getNom());
        card.setPV(createCards.getPV());
        card.setRarity(createCards.getRarity());
        
        if (createCards.getAttaque1Uuid() != null) {
            Attaque attaque1 = attaqueService.findById(createCards.getAttaque1Uuid());
            if (attaque1 == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la première attaque n'a pas été trouvée avec l'UUID " + createCards.getAttaque1Uuid());
            }
            card.setAttaque1(attaque1);
        }
        
        if (createCards.getAttaque2Uuid() != null) {
            Attaque attaque2 = attaqueService.findById(createCards.getAttaque2Uuid());
            if (attaque2 == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la deuxième attaque n'a pas été trouvée avec l'UUID " + createCards.getAttaque2Uuid());
            }
            card.setAttaque2(attaque2);
        }
        
        repository.save(card);
    }

    @Override
    public boolean update(String uuid, Cards card) {
        Cards cardToUpdate = findById(uuid);
        if (cardToUpdate == null) {
            return false;
        }
        cardToUpdate.setNom(card.getNom());
        cardToUpdate.setPV(card.getPV());
        cardToUpdate.setRarity(card.getRarity());
        cardToUpdate.setAttaque1(card.getAttaque1());
        cardToUpdate.setAttaque2(card.getAttaque2());
        repository.save(cardToUpdate);
        return true;
    }

    @Override
    public boolean delete(String uuid) {
        if (repository.existsById(uuid)) {
            repository.deleteById(uuid);
            return true;
        }
        return false;
    }
}
