package com.candidatura.espublico.controller;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.repositories.FilmRepository;
import com.candidatura.espublico.objects.Film;
import com.candidatura.espublico.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class StarWarsController {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    Utils utils;

    private static final Logger log = LoggerFactory.getLogger(StarWarsController.class);
        @GetMapping("/getFilm/{film_id}")
        public String greeting(@PathVariable(name="film_id", required=false) int film_id, Model model) {


            String uri = "https://swapi.py4e.com/api/films/"+film_id;
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

            return "testPage";
        }

    @GetMapping("/dropDataBase")
    public String dropDataBase(Model model) {

        try{
            filmRepository.deleteAll();
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return "testPage";
    }


}
