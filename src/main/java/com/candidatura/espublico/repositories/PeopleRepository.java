package com.candidatura.espublico.repositories;

import com.candidatura.espublico.RESTobjects.FilmPilotShip;
import com.candidatura.espublico.entities.PeopleEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PeopleRepository extends CrudRepository<PeopleEntity,Integer> {

    @Query(name="findFilmPilotShip", nativeQuery = true)
    List<FilmPilotShip> findPilotShip(@Param("films") Collection<String> films);
}
