package com.candidatura.espublico.repositories;

import com.candidatura.espublico.entities.FilmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<FilmEntity,Integer> {

    public List<FilmEntity> findByOrderByEpisodeIdAsc();
}
