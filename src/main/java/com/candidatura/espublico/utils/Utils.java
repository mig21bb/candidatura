package com.candidatura.espublico.utils;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.objects.Film;
import com.candidatura.espublico.objects.People;

import org.springframework.stereotype.Component;

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

	public FilmEntity fillPeopleEntity(People people) {
		// TODO Auto-generated method stub
		return null;
	}

	public FilmEntity fillStarshipEntity(Film ship) {
		// TODO Auto-generated method stub
		return null;
	}

}
