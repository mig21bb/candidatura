package com.candidatura.espublico.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="films")
public class FilmEntity {

    @Id
    @Getter
    @Setter
    @Column(name="film_id")
    int filmId;

    @Getter
    @Setter
    @Column(name="episode_id")
    int episodeId;

    @Getter
    @Setter
    @Column(name="title")
    String title;
    
    @Getter
    @Setter
    @Column(name="opening_crawl")
    String openingCrawl;

    @Getter
    @Setter
    @Column(name="director")
    String director;

    @Getter
    @Setter
    @Column(name="producer")
    String producer;
    
    @Getter
    @Setter
    @Column(name="release_date")
    Date releaseDate;
    
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

    @ManyToMany(mappedBy = "films")
    public List<PeopleEntity> characters = new ArrayList<>();

    public FilmEntity() {}
    public FilmEntity(int filmId) {
        this.filmId = filmId;
    }
}
