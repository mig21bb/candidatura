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
        this.title=ent.getTitle();
        this.director=ent.getDirector();
        this.producer=ent.getProducer();
    }


}
