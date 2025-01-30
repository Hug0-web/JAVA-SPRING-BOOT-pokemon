package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Attaque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String nom;

    private Integer degat;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getDegat() {
        return degat;
    }

    public void setDegat(Integer degat) {
        this.degat = degat;
    }

    public String getUuid() {
        return uuid;
    }

}
