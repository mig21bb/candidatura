package com.candidatura.espublico.bl;

import com.candidatura.espublico.RESTobjects.Film;
import com.candidatura.espublico.DTO.PeopleDTO;
import com.candidatura.espublico.RESTobjects.People;
import com.candidatura.espublico.RESTobjects.Starship;
import com.candidatura.espublico.entities.PeopleEntity;
import com.candidatura.espublico.repositories.FilmRepository;
import com.candidatura.espublico.repositories.PeopleRepository;
import com.candidatura.espublico.repositories.StarshipRepository;
import com.candidatura.espublico.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ConsultaBL {

    private static final Logger log = LoggerFactory.getLogger(ConsultaBL.class);
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    StarshipRepository starshipRepository;

    @Autowired
    Utils utils;


    @Transactional
    public List<People> consultaPeople()  {

        //Consulta de todos los personajes en el repositorio y transformación a objeto People
        List<People> characters=StreamSupport.stream(peopleRepository.findAll().spliterator(), false).map(People::new).collect(Collectors.toList());

        StreamSupport.stream(characters.spliterator(),false).forEach(p -> {
            p.setFilmList(fillCharacterFilms(utils.getIdFromUrl(p.getUrl())));
        });

        return characters;

    }

    @Transactional
    public List<Film> consultaPelis()  {

        List<Film> peliculas=StreamSupport.stream(filmRepository.findByOrderByEpisodeIdAsc().spliterator(), false).map(Film::new).collect(Collectors.toList());

        return peliculas;

    }

    public List<Film> fillCharacterFilms(int people_id){
        List<Film> pelis = StreamSupport.stream(peopleRepository.findById(people_id).get().getFilms().spliterator(), false).map(Film::new).collect(Collectors.toList());
        pelis = pelis.stream()
                .sorted((o1, o2)->o1.getEpisode_id().compareTo(o2.getEpisode_id()))
                .collect(Collectors.toList());
        return pelis;
    }

}
