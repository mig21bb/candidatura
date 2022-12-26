package com.candidatura.espublico.repositories;


import com.candidatura.espublico.entities.PeopleEntity;
import com.candidatura.espublico.entities.StarshipEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipRepository extends CrudRepository<StarshipEntity,Integer> {


}
