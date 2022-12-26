package com.candidatura.espublico.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FilmDTO {

    private Integer episode_id;
    private String title;
    private String openingCrawl;
    private String director;
    private String producer;
    private Date releaseDate;
    private Date created;
    private Date edited;
    private String url;

    private List<PeopleDTO> people;

    public FilmDTO() {}

    public FilmDTO(Integer episode_id, String title, String openingCrawl, String director, String producer, Date releaseDate, Date created, Date edited, String url, List<PeopleDTO> people) {
        this.episode_id = episode_id;
        this.title = title;
        this.openingCrawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.created = created;
        this.edited = edited;
        this.url = url;
        this.people = people;
    }
}
