package com.candidatura.espublico.objects;

import com.candidatura.espublico.entities.FilmEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Film {

    private Integer episode_id;
    private String title;
    private String opening_crawl;
    private String director;
    private String producer;
    private Date release_date;
    private Date created;
    private Date edited;
    private String url;
    private String[] characters;
    private String[] starships;

    public Film() {}
    
    public Film(FilmEntity ent){
        this.episode_id=ent.getEpisodeId();
        this.title=ent.getTitle();
        this.director=ent.getDirector();
        this.producer=ent.getProducer();
        this.release_date=ent.getReleaseDate();
        this.created=ent.getCreated();
        this.edited=ent.getEdited();
        this.url=ent.getUrl();
        this.opening_crawl=ent.getOpeningCrawl();        
    }


}
