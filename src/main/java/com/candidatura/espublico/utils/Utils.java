package com.candidatura.espublico.utils;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.entities.PeopleEntity;
import com.candidatura.espublico.entities.StarshipEntity;
import com.candidatura.espublico.RESTobjects.Film;
import com.candidatura.espublico.RESTobjects.People;
import com.candidatura.espublico.RESTobjects.Starship;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Utils {

    /**
     * Rellena una entidad FilmEntity con el objeto Film
     * @param film
     * @return
     */
    public FilmEntity fillFilmEntity(Film film){
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setTitle(film.getTitle());
        filmEntity.setEpisodeId(film.getEpisode_id());
        filmEntity.setProducer(film.getProducer());
        filmEntity.setFilmId(this.getIdFromUrl(film.getUrl()));
        filmEntity.setDirector(film.getDirector());
        filmEntity.setCreated(film.getCreated());
        filmEntity.setEdited(film.getEdited());
        filmEntity.setOpeningCrawl(film.getOpening_crawl());
        filmEntity.setReleaseDate(film.getRelease_date());
        filmEntity.setUrl(film.getUrl());
        
        return filmEntity;
    }

    /**
     * Obtiene el id de una url
     * @param url
     * @return
     */
    public int getIdFromUrl(String url){
        return Integer.parseInt(url.split("/")[url.split("/").length-1]);
    }

	public PeopleEntity fillPeopleEntity(People people) {
		PeopleEntity peopleEntity = new PeopleEntity();
		peopleEntity.setBirthYear(people.getBirth_year());
		peopleEntity.setCreated(people.getCreated());
		peopleEntity.setEdited(people.getEdited());
		peopleEntity.setEyeColor(people.getEye_color());
		peopleEntity.setHairColor(people.getHair_color());
		peopleEntity.setHomeworld(getIdFromUrl(people.getHomeworld()));
		peopleEntity.setMass(people.getMass());
		peopleEntity.setHeight(people.getHeight());
		peopleEntity.setName(people.getName());
		peopleEntity.setGender(people.getGender());
		peopleEntity.setPeopleId(this.getIdFromUrl(people.getUrl()));
		peopleEntity.setSkinColor(people.getSkin_color());
		peopleEntity.setUrl(people.getUrl());
		
		return peopleEntity;
	}

	public StarshipEntity fillStarshipEntity(Starship ship) {
		StarshipEntity starshipEntity = new StarshipEntity();
		starshipEntity.setCargoCapacity(ship.getCargo_capacity());
		starshipEntity.setConsumables(ship.getConsumables());
		starshipEntity.setCostInCredits(ship.getCost_in_credits());
		starshipEntity.setCreated(ship.getCreated());
		starshipEntity.setEdited(ship.getEdited());
		starshipEntity.setHiperdriverRating(ship.getHyperdrive_rating());
		starshipEntity.setLength(ship.getLength());
		starshipEntity.setManufacturer(ship.getManufacturer());
		starshipEntity.setMaxAtmospheringSpeed(ship.getMax_atmosphering_speed());
		starshipEntity.setCrew(ship.getCrew());
		starshipEntity.setMglt(ship.getMGLT());
		starshipEntity.setModel(ship.getModel());
		starshipEntity.setName(ship.getName());
		starshipEntity.setPassengers(ship.getPassengers());
		starshipEntity.setStarshipClass(ship.getStarship_class());
		starshipEntity.setUrl(ship.getUrl());
		starshipEntity.setStarshipId(this.getIdFromUrl(ship.getUrl()));
		
		
		return starshipEntity;
	}


}
