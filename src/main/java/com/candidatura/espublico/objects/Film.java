package com.candidatura.espublico.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
public class Film {

    @Getter
    @Setter
    private int episode_id;
    @Getter
    @Setter
    private String  title;
    @Getter
    @Setter
    private String opening_crawl;
    @Getter
    @Setter
    private String director;
    @Getter
    @Setter
    private String producer;
    @Getter
    @Setter
    private Date release_date;
    @Getter
    @Setter
    private Date created;
    @Getter
    @Setter
    private Date edited;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String[] characters;
    @Getter
    @Setter
    private String[] starships;
}
