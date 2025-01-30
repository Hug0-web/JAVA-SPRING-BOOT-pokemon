package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Tirages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @OneToMany
    private List<Cards> cards;

    @ManyToOne
    @JoinColumn(name = "dresseur_uuid")
    private Dresseur dresseur;

    
    private LocalDateTime dateDeTirage;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public LocalDateTime getDateDeTirage() {
        return dateDeTirage;
    }

    public void setDateDeTirage(LocalDateTime dateDeTirage) {
        this.dateDeTirage = dateDeTirage;
    }
}