package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Attaque;
import fr.efrei.pokemon_tcg.dto.CreateAttaque;
import java.util.List;

public interface IAttaqueService {
    List<Attaque> findAll();
    Attaque findById(String uuid);
    void create(CreateAttaque attaque);
    boolean update(String uuid, Attaque attaque);
    boolean delete(String uuid);
}
