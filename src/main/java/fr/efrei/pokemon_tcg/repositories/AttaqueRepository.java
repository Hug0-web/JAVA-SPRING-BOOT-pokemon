package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Attaque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttaqueRepository extends JpaRepository<Attaque, String> {
}
