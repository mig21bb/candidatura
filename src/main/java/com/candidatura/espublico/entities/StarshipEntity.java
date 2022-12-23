package com.candidatura.espublico.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    int length;
    
    @Getter
    @Setter
    @Column(name="max_atmosphering_speed")
    int maxAtmospheringSpeed;
    
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
    long cargoCapacity;
    
    @Getter
    @Setter
    @Column(name="consumables")
    String consumables;
    
    @Getter
    @Setter
    @Column(name="hiperdrive_rating")
    BigDecimal hiperdriverRating;
    
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
    
}
