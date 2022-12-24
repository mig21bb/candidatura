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

    @GetMapping("/cargarBBDD")
    public String cargarBBDD( Model model) {

        cargaBL.deleteDataBase();
        String uri="https://swapi.py4e.com/api/films/";
        try{
            cargaBL.cargarFilms(uri);
            uri="https://swapi.py4e.com/api/starships/";
            cargaBL.cargarShips(uri);
            uri="https://swapi.py4e.com/api/people/";
            cargaBL.cargarPeople(uri);

        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return "ships";
    }

    @GetMapping("/listarPersonajes")
    public String listarPersonajes( Model model) {
        return "personajes";
    }
}
