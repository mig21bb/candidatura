package com.candidatura.espublico.objects;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.entities.PeopleEntity;

import lombok.Data;

import java.util.Date;

@Data
public class People {

    //private Integer peopleId;
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private Date created;
    private Date edited;
    private String url;
    private String[] films;
    private String[] starships;
    private String[] species;
    private String[] vehicles;



    public People() {}
    
    public People(PeopleEntity ent){
        //this.peopleId=ent.getPeopleId();
        this.name=ent.getName();
        this.birth_year=ent.getBirthYear();
        this.created=ent.getCreated();
        this.edited=ent.getEdited();
        this.eye_color=ent.getEyeColor();
        this.hair_color=ent.getHairColor();
        this.homeworld="https://swapi.dev/api/planets/"+ent.getHomeworld();
        this.mass=ent.getMass();
        this.height=ent.getHeight();
        this.skin_color=ent.getSkinColor();
        this.gender=ent.getGender();
        this.url=ent.getUrl();      
    }


}
