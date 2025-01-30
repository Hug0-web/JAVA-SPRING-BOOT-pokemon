package fr.efrei.pokemon_tcg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class CreateCards {

    @Length(min = 3, max = 30)
    private String nom;

    @NotBlank
    private String PV;

    @NotBlank
    private String rarity;

    private String attaque1Uuid;
    private String attaque2Uuid;

    public String getNom() {
        return nom;
    }

    public String getPV() {
        return PV;
    }

    public String getRarity() {
        return rarity;
    }

    public String getAttaque1Uuid() {
        return attaque1Uuid;
    }

    public String getAttaque2Uuid() {
        return attaque2Uuid;
    }
}
