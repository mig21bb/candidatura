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

        List<Film> peliculas=StreamSupport.stream(filmRepository.findAll().spliterator(), false).map(Film::new).collect(Collectors.toList());

        return peliculas;

    }



    @Transactional
    public List<Film> cargarFilms(String uri) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        List<Film> films = new ArrayList<>();

        JsonNode root ;
        JsonNode next ;
        JsonNode array ;
        do{
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(response.getBody());
            next = root.path("next");
            array = root.path("results");
            Film f = new Film();
            if(array.isArray()){
                for(JsonNode node: array){
                    f = mapper.treeToValue(node, Film.class);
                    filmRepository.save(utils.fillFilmEntity(f));
                    films.add(f);
                }
            }
            uri=next.asText();
        }while(!next.isNull());

            log.debug("films cargados: "+films.size());

        return films;

    }

    @Transactional
    public List<Starship> cargarShips(String uri) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        List<Starship> ships = new ArrayList<>();

        JsonNode root ;
        JsonNode next ;
        JsonNode array ;
        do{
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(response.getBody());
            next = root.path("next");
            array = root.path("results");
            Starship s = new Starship();
            if(array.isArray()){
                for(JsonNode node: array){
                    String MGLT = "";
                    if (node instanceof ObjectNode){
                        ObjectNode object = (ObjectNode) node;
                        MGLT = object.findValue("MGLT").textValue();
                        object.remove("MGLT");
                    }
                    s = mapper.treeToValue(node, Starship.class);
                    s.setMGLT(MGLT);
                    starshipRepository.save(utils.fillStarshipEntity(s));
                    ships.add(s);
                }
            }
            uri=next.asText();
        }while(!next.isNull());

        log.debug("films cargados: "+ships.size());

        return ships;

    }

    @Transactional
    public boolean deleteDataBase(){
        filmRepository.deleteAll();
        starshipRepository.deleteAll();
        peopleRepository.deleteAll();
        return true;
    }

    public List<Film> fillCharacterFilms(int people_id){
        List<Film> pelis = new ArrayList<>();
        pelis = StreamSupport.stream(peopleRepository.findById(people_id).get().getFilms().spliterator(), false).map(Film::new).collect(Collectors.toList());
        return pelis;
    }

}
