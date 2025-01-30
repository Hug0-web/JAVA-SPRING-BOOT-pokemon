package fr.efrei.pokemon_tcg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCards {

    @NotBlank
    @Length(min = 3, max = 30)
    private String nom;

    @NotNull
    @Min(10)
    @Max(300)
    @JsonProperty("pv")
    private Integer PV;

    @NotBlank
    private String rarity;

    @JsonProperty("attaque1")
    private AttaqueRef attaque1;

    @JsonProperty("attaque2")
    private AttaqueRef attaque2;

    public static class AttaqueRef {
        private String uuid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPV() {
        return PV;
    }

    public void setPV(Integer PV) {
        this.PV = PV;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getAttaque1Uuid() {
        return attaque1 != null ? attaque1.getUuid() : null;
    }

    public String getAttaque2Uuid() {
        return attaque2 != null ? attaque2.getUuid() : null;
    }

    public void setAttaque1(AttaqueRef attaque1) {
        this.attaque1 = attaque1;
    }

    public void setAttaque2(AttaqueRef attaque2) {
        this.attaque2 = attaque2;
    }
}
