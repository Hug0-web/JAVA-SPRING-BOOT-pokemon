package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private Integer PV;

    private String nom;

    private String rarity;

    @ManyToOne
    @JoinColumn(name = "attaque1_uuid")
    private Attaque attaque1;

    @ManyToOne
    @JoinColumn(name = "attaque2_uuid")
    private Attaque attaque2;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getPV() {
        return PV;
    }

    public void setPV(Integer PV) {
        this.PV = PV;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Attaque getAttaque1() {
        return attaque1;
    }

    public void setAttaque1(Attaque attaque1) {
        this.attaque1 = attaque1;
    }

    public Attaque getAttaque2() {
        return attaque2;
    }

    public void setAttaque2(Attaque attaque2) {
        this.attaque2 = attaque2;
    }
}
