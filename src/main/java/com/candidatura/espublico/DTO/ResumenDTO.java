package com.candidatura.espublico.DTO;

import com.candidatura.espublico.entities.FilmEntity;
import com.candidatura.espublico.entities.PedidoEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ResumenDTO {

    private String campo;
    private Integer recuento;

    public ResumenDTO(String campo, Integer recuento){
        this.campo=campo;
        this.recuento=recuento;
    }


}
