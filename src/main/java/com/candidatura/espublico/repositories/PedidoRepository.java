package com.candidatura.espublico.repositories;

import com.candidatura.espublico.entities.PedidoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoEntity,Integer> {


}
