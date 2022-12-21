package com.candidatura.espublico.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name="homeworld")
    int homeworld;
    
    @Getter
    @Setter
    @Column(name="mass")
    int mass;

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
