package com.candidatura.espublico.repositories;

import com.candidatura.espublico.entities.PeopleEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends CrudRepository<PeopleEntity,Integer> {
}
