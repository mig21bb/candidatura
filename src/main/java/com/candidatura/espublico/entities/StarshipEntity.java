package com.candidatura.espublico.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="starships")
public class StarshipEntity {

    @Id
    @Getter
    @Setter
    @Column(name="starship_id")
    int starshipId;

    @Getter
    @Setter
    @Column(name="name")
    String name;

    @Getter
    @Setter
    @Column(name="model")
    String model;

    @Getter
    @Setter
    @Column(name="manufacturer")
    String manufacturer;

    @Getter
    @Setter
    @Column(name="cost_in_credits")
    String costInCredits;

    @Getter
    @Setter
    @Column(name="length")
    String length;
    
    @Getter
    @Setter
    @Column(name="max_atmosphering_speed")
    String maxAtmospheringSpeed;
    
    @Getter
    @Setter
    @Column(name="crew")
    String Crew;
        
    @Getter
    @Setter
    @Column(name="passengers")
    String passengers;
    
    @Getter
    @Setter
    @Column(name="cargo_capacity")
    String cargoCapacity;
    
    @Getter
    @Setter
    @Column(name="consumables")
    String consumables;
    
    @Getter
    @Setter
    @Column(name="hiperdrive_rating")
    String hiperdriverRating;
    
    @Getter
    @Setter
    @Column(name="mglt")
    String mglt;
    
    @Getter
    @Setter
    @Column(name="starship_class")
    String starshipClass;
    
    @Getter
    @Setter
    @Column(name="created")
    Date created;
    
    @Getter
    @Setter
    @Column(name="edited")
    Date edited;
    
    @Getter
    @Setter
    @Column(name="url")
    String url;

    @ManyToMany(mappedBy = "ships")
    public List<PeopleEntity> pilots = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "starshipfilm",
            joinColumns = @JoinColumn(name = "starship_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    @Getter
    @Setter
    private List<FilmEntity> films = new ArrayList<>();

    public StarshipEntity() {}
    public StarshipEntity(int starshipId) {
        this.starshipId = starshipId;
    }
}
