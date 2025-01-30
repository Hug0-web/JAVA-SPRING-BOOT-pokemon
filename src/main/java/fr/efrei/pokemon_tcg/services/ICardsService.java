package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Cards;
import fr.efrei.pokemon_tcg.dto.CreateCards;
import java.util.List;

public interface ICardsService {
    List<Cards> findAll();
    Cards findById(String uuid);
    void create(CreateCards card);
    boolean update(String uuid, Cards card);
    boolean delete(String uuid);
}
