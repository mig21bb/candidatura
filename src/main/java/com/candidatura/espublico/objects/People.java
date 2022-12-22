package com.candidatura.espublico.objects;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.entities.PeopleEntity;

import lombok.Data;

import java.util.Date;

@Data
public class People {

    private int peopleId;
    private String name;
    private int mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private int homeworld;
    private Date created;
    private Date edited;
    private String url;

    public People() {}
    public People(PeopleEntity ent){
        this.peopleId=ent.getPeopleId();
        this.name=ent.getName();
        this.birthYear=ent.getBirthYear();
        this.created=ent.getCreated();
        this.edited=ent.getEdited();
        this.eyeColor=ent.getEyeColor();
        this.hairColor=ent.getHairColor();
        this.homeworld=ent.getHomeworld();
        this.mass=ent.getMass();
        this.skinColor=ent.getSkinColor();
        this.url=ent.getUrl();      
    }


}
