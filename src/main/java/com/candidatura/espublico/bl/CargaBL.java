package com.candidatura.espublico.bl;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.entities.PeopleEntity;
import com.candidatura.espublico.entities.StarshipEntity;
import com.candidatura.espublico.RESTobjects.Film;
import com.candidatura.espublico.RESTobjects.People;
import com.candidatura.espublico.RESTobjects.Starship;
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
import java.util.Optional;

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
    @Transactional
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
            PeopleEntity ent = new PeopleEntity();
            if(array.isArray()){
                for(JsonNode node: array){
                    p = mapper.treeToValue(node, People.class);
                    ent = utils.fillPeopleEntity(p);
                    this.cargaPelis(ent, p.getFilms());
                    this.cargaShips(ent, p.getStarships());
                    peopleRepository.save(ent);
                    characters.add(p);
                }
            }
            uri=next.asText();
        }while(!next.isNull());

        log.debug("People cargados: "+characters.size());

        return characters;

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
            StarshipEntity ent = new StarshipEntity();
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
                    ent = utils.fillStarshipEntity(s);
                    this.cargaStarshipFilms(ent, s.getFilms());
                    starshipRepository.save(ent);
                    ships.add(s);
                }
            }
            uri=next.asText();
        }while(!next.isNull());

        log.debug("films cargados: "+ships.size());

        return ships;

    }

    private PeopleEntity cargaPelis(PeopleEntity ent, String[] films) {
        for(String film: films){
            Optional<FilmEntity> f = filmRepository.findById(utils.getIdFromUrl(film));
            ent.getFilms().add(f.get());
        }
        return ent;
    }

    private PeopleEntity cargaShips(PeopleEntity ent, String[] ships) {
        for(String ship: ships){
            Optional<StarshipEntity> s = starshipRepository.findById(utils.getIdFromUrl(ship));
            ent.getShips().add(s.get());
        }
        return ent;
    }
    private StarshipEntity cargaStarshipFilms(StarshipEntity ent, String[] films) {
        for(String film: films){
            Optional<FilmEntity> f = filmRepository.findById(utils.getIdFromUrl(film));
            ent.getFilms().add(f.get());
        }
        return ent;
    }

    @Transactional
    public boolean deleteDataBase(){
        filmRepository.deleteAll();
        starshipRepository.deleteAll();
        peopleRepository.deleteAll();
        return true;
    }


}
