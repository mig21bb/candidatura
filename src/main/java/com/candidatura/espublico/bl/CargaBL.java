package com.candidatura.espublico.bl;

import com.candidatura.espublico.controller.StarWarsController;
import com.candidatura.espublico.objects.Film;
import com.candidatura.espublico.objects.People;
import com.candidatura.espublico.objects.Starship;
import com.candidatura.espublico.repositories.FilmRepository;
import com.candidatura.espublico.repositories.PeopleRepository;
import com.candidatura.espublico.repositories.StarshipRepository;
import com.candidatura.espublico.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CargaBL {

    private static final Logger log = LoggerFactory.getLogger(CargaBL.class);
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    StarshipRepository starshipRepository;

    @Autowired
    Utils utils;



    /**
     * Carga los objetos people en la BBDD
     * @param uri
     * @return
     */
    public List<People> cargarPeople(String uri) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        List<People> characters = new ArrayList<>();

        JsonNode root ;
        JsonNode next ;
        JsonNode array ;
        do{
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(response.getBody());
            next = root.path("next");
            array = root.path("results");
            People p = new People();
            if(array.isArray()){
                for(JsonNode node: array){
                    p = mapper.treeToValue(node, People.class);
                    peopleRepository.save(utils.fillPeopleEntity(p));
                    characters.add(p);
                }
            }
            uri=next.asText();
        }while(!next.isNull());

        log.debug("People cargados: "+characters.size());

        return characters;

    }

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
                    s = mapper.treeToValue(node, Starship.class);
                    starshipRepository.save(utils.fillStarshipEntity(s));
                    ships.add(s);
                }
            }
            uri=next.asText();
        }while(!next.isNull());

        log.debug("films cargados: "+ships.size());

        return ships;

    }

    public boolean dropDataBase(){
        filmRepository.deleteAll();
        peopleRepository.deleteAll();
        starshipRepository.deleteAll();
        return true;
    }
}
