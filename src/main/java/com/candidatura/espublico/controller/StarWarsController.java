package com.candidatura.espublico.controller;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.entities.PeopleEntity;
import com.candidatura.espublico.entities.StarshipEntity;
import com.candidatura.espublico.repositories.FilmRepository;
import com.candidatura.espublico.repositories.PeopleRepository;
import com.candidatura.espublico.repositories.StarshipRepository;
import com.candidatura.espublico.objects.Film;
import com.candidatura.espublico.objects.People;
import com.candidatura.espublico.objects.Starship;
import com.candidatura.espublico.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class StarWarsController {

    @Autowired
    FilmRepository filmRepository;
    
    @Autowired
    PeopleRepository peopleRepository;
    
    @Autowired
    StarshipRepository starshipRepository;

    @Autowired
    Utils utils;

    private static final Logger log = LoggerFactory.getLogger(StarWarsController.class);
    
    /**
     * Obtener un objeto film del servicio rest y guardarlo en la BBDD
     * @param film_id
     * @param model
     * @return
     */
    @GetMapping("/getFilm/{id}")
    public String getFilm(@PathVariable(name="id", required=false) int id, Model model) {


        String uri = "https://swapi.py4e.com/api/films/"+id;
        RestTemplate restTemplate = new RestTemplate();

        Film film = new Film();
        film = restTemplate.getForObject(uri, Film.class);
        log.debug(film.toString());

        model.addAttribute("film", film);

        FilmEntity filmEntity = utils.fillFilmEntity(film);
        try{
            filmRepository.save(filmEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return "film";
    }
    
    /**
     * Obtener un objeto people del servicio rest y guardarlo en la BBDD
     * @param film_id
     * @param model
     * @return
     */
    @GetMapping("/getPeople/{id}")
    public String getPoeple(@PathVariable(name="id", required=false) int id, Model model) {


        String uri = "https://swapi.py4e.com/api/people/"+id;
        RestTemplate restTemplate = new RestTemplate();
	    try{
	        People people = new People();
	        people = restTemplate.getForObject(uri, People.class);
	        log.debug(people.toString());
	
	        model.addAttribute("people", people);
	
	        PeopleEntity peopleEntity = utils.fillPeopleEntity(people);

        	peopleRepository.save(peopleEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return "people";
    }
    
    /**
     * Obtener un objeto film del servicio rest y guardarlo en la BBDD
     * @param film_id
     * @param model
     * @return
     */
    @GetMapping("/getStarship/{id}")
    public String getStarship(@PathVariable(name="id", required=false) int id, Model model) {


        String uri = "https://swapi.py4e.com/api/starships/"+id;
        RestTemplate restTemplate = new RestTemplate();
        try{
	        Starship ship = new Starship();
	        ship = restTemplate.getForObject(uri, Starship.class);
	        log.debug(ship.toString());
	
	        model.addAttribute("ship", ship);
	
	        StarshipEntity filmEntity = utils.fillStarshipEntity(ship);

            starshipRepository.save(filmEntity);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return "starship";
    }
    
    /**
     * Obtener un objeto film del servicio rest y guardarlo en la BBDD
     * @param film_id
     * @param model
     * @return
     */
    @GetMapping("/cargarPeople")
    public String cargarPeople( Model model) {

        String uri="https://swapi.py4e.com/api/people/";
        RestTemplate restTemplate = new RestTemplate();
        try{
	        List<People> characters = new ArrayList<>();
	        characters = (List<People>) restTemplate.getForEntity(uri, People.class);
	        log.debug(characters.toString());
	
	        model.addAttribute("characters", characters);
	
	        //StarshipEntity filmEntity = utils.fillStarshipEntity(ship);

            //starshipRepository.save(filmEntity);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return "starship";
    }

    @GetMapping("/dropDataBase")
    public String dropDataBase(Model model) {

        try{
            filmRepository.deleteAll();
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return "people";
    }


}
