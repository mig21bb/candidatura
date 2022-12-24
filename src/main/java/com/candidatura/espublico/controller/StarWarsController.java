package com.candidatura.espublico.controller;

import com.candidatura.espublico.bl.CargaBL;
import com.candidatura.espublico.objects.Film;
import com.candidatura.espublico.objects.People;
import com.candidatura.espublico.objects.Starship;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StarWarsController {



    private static final Logger log = LoggerFactory.getLogger(StarWarsController.class);

    @Autowired
    CargaBL cargaBL;

    /**
     * Cargar todos los personajes
     * @param model
     * @return
     */
    @GetMapping("/cargarPeople")
    public String cargarPeople( Model model) {

        String uri="https://swapi.py4e.com/api/people/";
        try{
            List<People> characters = cargaBL.cargarPeople(uri);
	        log.debug("People cargados: "+characters.size());

        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return "people";
    }
    @GetMapping("/cargarFilms")
    public String cargarFilms( Model model) {

        String uri="https://swapi.py4e.com/api/films/";
        try{
            List<Film> films = cargaBL.cargarFilms(uri);
            log.debug("People cargados: "+films.size());

        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return "films";
    }

    @GetMapping("/cargarStarships")
    public String cargarStarships( Model model) {

        String uri="https://swapi.py4e.com/api/starships/";
        try{
            List<Starship> ships = cargaBL.cargarShips(uri);
            log.debug("Starships cargados: "+ships.size());

        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return "ships";
    }

    @GetMapping("/cargarBBDD")
    public String cargarBBDD( Model model) {

        String uri="https://swapi.py4e.com/api/people/";
        try{
            cargaBL.cargarPeople(uri);
            uri="https://swapi.py4e.com/api/Films/";
            cargaBL.cargarFilms(uri);
            uri="https://swapi.py4e.com/api/starships/";
            cargaBL.cargarShips(uri);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return "ships";
    }

    @GetMapping("/dropDataBase")
    public String dropDataBase(Model model) {

        try{
            cargaBL.dropDataBase();
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return "people";
    }


}
