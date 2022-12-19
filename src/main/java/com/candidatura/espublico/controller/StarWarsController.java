package com.candidatura.espublico.controller;

import com.candidatura.espublico.EspublicoApplication;
import com.candidatura.espublico.objects.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class StarWarsController {

    private static final Logger log = LoggerFactory.getLogger(StarWarsController.class);
        @GetMapping("/getEpisode")
        public String greeting(@RequestParam(name="episode", required=false, defaultValue="World") int episode, Model model) {


            String uri = "https://swapi.py4e.com/api/films/"+episode;
            RestTemplate restTemplate = new RestTemplate();

            Film film = restTemplate.getForObject(uri, Film.class);
            log.debug(film.toString());

            model.addAttribute("film", film);

            return "testPage";
        }


}
