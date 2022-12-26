package com.candidatura.espublico.controller;

import com.candidatura.espublico.bl.CargaBL;
import com.candidatura.espublico.bl.ConsultaBL;
import com.candidatura.espublico.RESTobjects.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StarWarsController {



    private static final Logger log = LoggerFactory.getLogger(StarWarsController.class);

    @Autowired
    CargaBL cargaBL;

    @Autowired
    ConsultaBL consultaBL;

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
        List<People> characters = new ArrayList<>();

        model.addAttribute("personajes", consultaBL.consultaPeople());
        model.addAttribute("peliculas", consultaBL.consultaPelis());

        return "personajes";
    }

    @GetMapping("/buscarPiloto")
    public String listarPersonajes(String[] pelis, Model model) {
        List<People> characters = new ArrayList<>();

        model.addAttribute("personajes", consultaBL.consultaPeople());
        model.addAttribute("peliculas", consultaBL.consultaPelis());

        return "personajes";
    }
}
