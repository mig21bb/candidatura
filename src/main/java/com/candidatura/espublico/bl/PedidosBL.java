package com.candidatura.espublico.bl;

import com.candidatura.espublico.DTO.ResumenDTO;
import com.candidatura.espublico.entities.PedidoEntity;
import com.candidatura.espublico.repositories.PedidoRepository;
import com.candidatura.espublico.utils.Utils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidosBL {

    private static final Logger log = LoggerFactory.getLogger(PedidosBL.class);

    private static final String delimiter = ",";

    private static final String tempFileName = "pedidos";
    private static final String fileExtension = ".csv";

    private static final String cabecera = "Order ID,Order Date,Ship Date,Region,Country,Item Type,Sales Channel,Order Priority,Units Sold,Unit Price,Unit Cost,Total Revenue,Total Cost,Total Profit";

    @Autowired
    PedidoRepository pedidosRepository;

    @Autowired
    Utils utils;

    /**
     * Carga los pedidos en la BBDD
     * @param pedidos
     * @return List<PedidoEntity>
     */
    @Transactional
    public List<PedidoEntity> cargarPedidos(File pedidos) throws IOException {
        log.debug("Cargando pedido de fichero");
        FileReader fr = new FileReader(pedidos);
        List<PedidoEntity> entityList= new ArrayList<>();
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] tempArr;
        br.readLine();
        while((line = br.readLine()) != null) {
            tempArr = line.split(delimiter);
            entityList.add(new PedidoEntity(tempArr));
            System.out.println();
        }
        br.close();
        log.debug("Fichero leído");
        pedidosRepository.saveAll(entityList);
        log.debug("Pedidos cargados en base de datos");
        return entityList;

    }

    @Transactional
    public File ordenarPedidos(File file) throws IOException {
        log.debug("Consultar pedidos ordenados");
        List<PedidoEntity> pedidos = pedidosRepository.findAllByOrderByOrderId();
        file= File.createTempFile(tempFileName,fileExtension);
        BufferedWriter  fileWriter = new BufferedWriter (new FileWriter(file, true));
        fileWriter.write(cabecera);
        fileWriter.newLine();
        for(PedidoEntity p: pedidos){
            fileWriter.write(p.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
        log.debug("Fichero "+file.getPath()+" generado con "+pedidos.size()+" líneas.");

        return file;

    }

    @Transactional
    public List<List<ResumenDTO>> consultarResumenPedidos() {
        List<List<ResumenDTO>> resumen = new ArrayList<>();
        Integer total = Math.toIntExact(pedidosRepository.count());
        List<ResumenDTO> consulta = new ArrayList<>();
        consulta.add(new ResumenDTO("Por Region",total));
        resumen.add(consulta);
        resumen.get(0).addAll(pedidosRepository.recuentoPorRegion());

        consulta = new ArrayList<>();
        consulta.add(new ResumenDTO("Por item_type",total));
        resumen.add(consulta);
        resumen.get(1).addAll(pedidosRepository.recuentoPorItem());

        consulta = new ArrayList<>();
        consulta.add(new ResumenDTO("Por canal de venta",total));
        resumen.add(consulta);
        resumen.get(2).addAll(pedidosRepository.recuentoPorChannel());

        consulta = new ArrayList<>();
        consulta.add(new ResumenDTO("Por prioridad",total));
        resumen.add(consulta);
        resumen.get(3).addAll(pedidosRepository.recuentoPorPriority());

        consulta = new ArrayList<>();
        consulta.add(new ResumenDTO("Por pais",total));
        resumen.add(consulta);
        resumen.get(4).addAll(pedidosRepository.recuentoPorPais());
        return resumen;
    }

    @Transactional
    public Long contarRegistros() {
        return pedidosRepository.count();
    }


    public long vaciarPedidos() {
        long total= pedidosRepository.count();
        pedidosRepository.deleteAll();
        return total;
    }
}
