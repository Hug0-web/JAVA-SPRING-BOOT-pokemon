package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Cards, String> {
}
