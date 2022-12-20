package com.candidatura.espublico.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name="director")
    String director;

    @Getter
    @Setter
    @Column(name="producer")
    String producer;


}
