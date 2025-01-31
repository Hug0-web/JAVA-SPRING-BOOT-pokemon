package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EchangeRepository extends JpaRepository<Echange, String> {
    @Query("SELECT e FROM Echange e WHERE " +
           "((e.dresseur1 = :dresseur1 AND e.dresseur2 = :dresseur2) OR " +
           "(e.dresseur1 = :dresseur2 AND e.dresseur2 = :dresseur1)) " +
           "AND e.dateEchange = :date")
    Optional<Echange> findEchangeByDresseursAndDate(Dresseur dresseur1, Dresseur dresseur2, LocalDate date);
}