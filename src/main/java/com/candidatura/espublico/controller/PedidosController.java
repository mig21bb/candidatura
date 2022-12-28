package com.candidatura.espublico.controller;

import com.candidatura.espublico.DTO.ResumenDTO;
import com.candidatura.espublico.bl.PedidosBL;
import com.candidatura.espublico.entities.PedidoEntity;
import com.candidatura.espublico.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidosController {


    private static final Logger log = LoggerFactory.getLogger(PedidosController.class);


    @Autowired
    PedidosBL pedidosBL;

    @GetMapping("/")
	public String pedidos() {
        log.debug("Cargando página para cargar ficheros de pedidos");
        return "pedidos";
    }

	@PostMapping(value="/cargar")
    public String uploadFile(@RequestParam(name="ruta", required=true) String ruta, Model model) {
        log.debug("Cargando ruta del fichero");
        // check if file is empty
        String mensaje="";
        File pedidosOrdenados = null;
        model.addAttribute("ruta",ruta);
        if(ruta!=null && !ruta.equals("")){
            try {

                File pedidos = new File(ruta);
                pedidosBL.cargarPedidos(pedidos);

            } catch(IOException e) {
                mensaje="Error cargando el fichero en la ruta especificada";
                model.addAttribute("mensaje",mensaje);
                log.debug(mensaje+e.getMessage());
                log.error(e.getMessage());
                e.printStackTrace();
                return "pedidos";
            } catch(Exception e){
                mensaje="Error cargando el los pedidos";
                model.addAttribute("mensaje",mensaje);
                log.debug(mensaje+e.getMessage());
                log.error(e.getMessage());
                e.printStackTrace();
                return "pedidos";
            }
            log.debug("Generación de archivo con los registros ordenados.");
            try{
                pedidosOrdenados = pedidosBL.ordenarPedidos(pedidosOrdenados);
            } catch(IOException e) {
                mensaje="Error generando nuevo archivo.";
                model.addAttribute("mensaje",mensaje);
                log.debug(mensaje+e.getMessage());
                log.error(e.getMessage());
                e.printStackTrace();
                return "pedidos";
            } catch(Exception e){
                mensaje="Error generando nuevo archivo.";
                model.addAttribute("mensaje",mensaje);
                log.debug(mensaje+e.getMessage());
                log.error(e.getMessage());
                e.printStackTrace();
                return "pedidos";
            }
            if(pedidosOrdenados!=null){
                model.addAttribute("rutaDestino",pedidosOrdenados.getPath());
            }
            try {
                log.debug("Consultas para el resumen");
                List<List<ResumenDTO>> resumen = pedidosBL.consultarResumenPedidos();
                model.addAttribute("resumen",resumen);
            }catch (Exception e){
                mensaje="Error generando el resumen.";
                model.addAttribute("mensaje",mensaje);
                log.debug(mensaje+e.getMessage());
                log.error(e.getMessage());
                e.printStackTrace();
                return "pedidos";
            }


        }
        model.addAttribute("mensaje","Éxito en la importación.");
        return "pedidos";
	}


}
