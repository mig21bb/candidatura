package com.candidatura.espublico.repositories;

import com.candidatura.espublico.DTO.ResumenDTO;
import com.candidatura.espublico.entities.PedidoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoEntity,Integer> {


    List<PedidoEntity> findAllByOrderByOrderId();

    @Query(name = "recuentoPorRegion", nativeQuery=true)
    List<ResumenDTO> recuentoPorRegion() ;
    @Query(name = "recuentoPorPais", nativeQuery=true)
    List<ResumenDTO> recuentoPorPais() ;
    @Query(name = "recuentoPorItem", nativeQuery=true)
    List<ResumenDTO> recuentoPorItem() ;
    @Query(name = "recuentoPorChannel", nativeQuery=true)
    List<ResumenDTO> recuentoPorChannel() ;
    @Query(name = "recuentoPorPriority", nativeQuery=true)
    List<ResumenDTO> recuentoPorPriority() ;
}
