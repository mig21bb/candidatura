package com.candidatura.espublico.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.candidatura.espublico.RESTobjects.FilmPilotShip;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="people")
@NamedNativeQuery(
        name = "findFilmPilotShip",
        query ="select f.title as title, p.name as pilotName, s.name as shipName\n" +
            " from people p\n" +
            " inner join characters c on p.people_id=c.people_id\n" +
            " inner join films f on f.film_id=c.film_id\n" +
            " inner join pilots pl on p.people_id=pl.people_id \n" +
            " right join (\n" +
            "select count(sf.starship_id) as apariciones,sf.starship_id ship_id\n" +
            "from films f\n" +
            "inner join starshipfilm sf on sf.film_id=f.film_id\n" +
            "where sf.starship_id in (select distinct(starship_id)\n" +
            "from pilots) and f.episode_id in :films \n" +
            "group by sf.starship_id\n" +
            "order by apariciones desc, sf.starship_id asc\n" +
            "limit 1) as selected_ship\n" +
            "on pl.starship_id=selected_ship.ship_id\n" +
            "inner join starships s on s.starship_id=selected_ship.ship_id\n" +
            "where f.episode_id in :films ",
        resultSetMapping = "filmPilotShip"
)
@SqlResultSetMapping(
        name = "filmPilotShip",
        classes = @ConstructorResult(
                targetClass = FilmPilotShip.class,
                columns = {
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "pilotName", type = String.class),
                        @ColumnResult(name = "shipName", type = String.class)
                }
        )
)
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


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "characters",
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    @Getter
    @Setter
    private List<FilmEntity> films = new ArrayList<>();


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "pilots",
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "starship_id")
    )
    @Getter
    @Setter
    private List<StarshipEntity> ships = new ArrayList<>();
}
