package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Cards;
import fr.efrei.pokemon_tcg.repositories.CardsRepository;
import fr.efrei.pokemon_tcg.services.ICardsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardsServiceImpl implements ICardsService {

    private final CardsRepository repository;

    public CardsServiceImpl(CardsRepository repository) {
        this.repository = repository;
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
    public void save(Cards card) {
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
