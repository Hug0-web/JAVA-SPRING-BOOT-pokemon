package fr.efrei.pokemon_tcg.dto;

import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class CreateAttaque {

    @Length(min = 3, max = 30)
    private String nom;

    @Positive
    private Integer degat;

    public String getNom() {
        return nom;
    }

    public Integer getDegat() {
        return degat;
    }
}
