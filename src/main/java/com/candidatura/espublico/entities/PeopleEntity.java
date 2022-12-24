package com.candidatura.espublico.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="people")
public class PeopleEntity {

    @Id
    @Getter
    @Setter
    @Column(name="people_id")
    int peopleId;
    
    @Getter
    @Setter
    @Column(name="name")
    String name;

    @Getter
    @Setter
    @Column(name="homeworld")
    int homeworld;

    @Getter
    @Setter
    @Column(name="height")
    String height;
    
    @Getter
    @Setter
    @Column(name="mass")
    String mass;

    @Getter
    @Setter
    @Column(name="hair_color")
    String hairColor;
    
    @Getter
    @Setter
    @Column(name="skin_color")
    String skinColor;

    @Getter
    @Setter
    @Column(name="eye_color")
    String eyeColor;

    @Getter
    @Setter
    @Column(name="birth_year")
    String birthYear;

    @Getter
    @Setter
    @Column(name="gender")
    String gender;
    
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

    @Getter
    @Setter
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "characters",
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<FilmEntity> films = new ArrayList<>();

    @Getter
    @Setter
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "pilots",
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "starship_id")
    )
    private List<StarshipEntity> ships = new ArrayList<>();
}
