package com.candidatura.espublico.DTO;

import com.candidatura.espublico.RESTobjects.Film;
import com.candidatura.espublico.RESTobjects.Starship;
import com.candidatura.espublico.entities.PeopleEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PeopleDTO {

    //private Integer peopleId;
    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private String homeworld;
    private Date created;
    private Date edited;
    private String url;

    private List<Starship> starships;

    private List<Film> films;


    public PeopleDTO() {}

    public PeopleDTO(PeopleEntity peopleEntity) {

    }
}
