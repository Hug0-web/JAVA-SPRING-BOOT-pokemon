package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Echange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "dresseur1_uuid")
    private Dresseur dresseur1;

    @ManyToOne
    @JoinColumn(name = "dresseur2_uuid")
    private Dresseur dresseur2;

    @Column(name = "date_echange")
    private LocalDate dateEchange;

    public String getUuid() { 
        return uuid; 
    }
    public void setUuid(String uuid) { 
        this.uuid = uuid; 
    }
    public Dresseur getDresseur1() { 
        return dresseur1; 
    }
    public void setDresseur1(Dresseur dresseur1) { 
        this.dresseur1 = dresseur1; 
    }
    public Dresseur getDresseur2() { 
        return dresseur2; 
    }
    public void setDresseur2(Dresseur dresseur2) { 
        this.dresseur2 = dresseur2; 
    }
    public LocalDate getDateEchange() { 
        return dateEchange; 
    }
    public void setDateEchange(LocalDate dateEchange) { 
        this.dateEchange = dateEchange; 
    }
}