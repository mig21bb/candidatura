package com.candidatura.espublico.controller;

import com.candidatura.espublico.bl.CargaBL;
import com.candidatura.espublico.bl.ConsultaBL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/sw/")
public class StarWarsController {

    private static final Logger log = LoggerFactory.getLogger(StarWarsController.class);

    @Autowired
    CargaBL cargaBL;

    @Autowired
    ConsultaBL consultaBL;

    @GetMapping("/")
    public String swindex( Model model) {

        model.addAttribute("peliculas", consultaBL.consultaPelis());

        return "swindex";
    }

    @GetMapping("/vaciarBBDD")
    public String vaciarBBDD( Model model) {
        log.debug("Primero se vacía la base de datos de películas, naves y personajes");
        try{
            cargaBL.deleteDataBase();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        log.debug("La base de datos está vacía");
        model.addAttribute("message","La base de datos se ha vaciado");

        return "swindex";
    }

    @GetMapping("/cargarBBDD")
    public String cargarBBDD( Model model) {
        log.debug("Primero se vacía la base de datos de películas, naves y personajes, por si acaso");
        cargaBL.deleteDataBase();
        log.debug("Una vez vacía la BBDD se van cargando los distintos elementos");
        String uri="https://swapi.py4e.com/api/films/";
        StringBuilder mensaje = new StringBuilder("Se han cargado los siguientes datos en la BBDD: ");
        try{
            mensaje.append(cargaBL.cargarFilms(uri).size()+" registros de películas,");
            uri="https://swapi.py4e.com/api/starships/";
            mensaje.append(cargaBL.cargarShips(uri).size()+"registros de naves y ");
            uri="https://swapi.py4e.com/api/people/";
            mensaje.append(cargaBL.cargarPeople(uri).size()+" registros de personajes");
            log.debug("Se han cargado todos los elementos");
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("message",mensaje);

        return "swindex";
    }

    @GetMapping("/listarPersonajes")
    public String listarPersonajes( Model model) {

        model.addAttribute("personajes", consultaBL.consultaPeople());


        return "personajes";
    }

    @GetMapping("/buscarPiloto")
    public String listarPersonajes(@RequestParam(name="episode", required=false)String[] episodes, Model model) {

        model.addAttribute("personajes", consultaBL.consultaPeople());
        model.addAttribute("peliculas", consultaBL.consultaPelis());
        model.addAttribute("pilotoNave", consultaBL.consultaPilotoNave(episodes));

        return "personajes";
    }
}
