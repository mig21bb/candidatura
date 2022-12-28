package com.candidatura.espublico.controller;

import com.candidatura.espublico.entities.PedidoEntity;
import com.candidatura.espublico.repositories.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidosController {


    private static final Logger log = LoggerFactory.getLogger(PedidosController.class);

    public static final String delimiter = ",";
    @Autowired
    PedidoRepository pedidoRepository;



    public PedidosController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/")
	public String pedidos() {
        log.debug("Cargando página para cargar ficheros de pedidos");
        return "pedidos";
    }

	@PostMapping("/cargar")
    public String uploadFile(@RequestParam(name="file", required=false) MultipartFile file,@RequestParam(name="ruta", required=false) String ruta, Model model) {
        log.debug("Cargando fichero o ruta del fichero");
        // check if file is empty
            BufferedReader br;
            List<PedidoEntity> entityList= new ArrayList<>();
            if(file!=null){
                try{
                    if (file.isEmpty()) {
                        model.addAttribute("message", "Please select a file to upload.");
                        return "pedidos";
                    }
                    List<String> result = new ArrayList<>();
                    String line;
                    InputStream is = file.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        result.add(line);
                    }

                    model.addAttribute("ruta", file.getName());
                } catch(Exception ioe) {
                    ioe.printStackTrace();
                }
            }else if(ruta!=null && !ruta.equals("")){
                try {
                    File pedidos = new File(ruta);
                    FileReader fr = new FileReader(pedidos);
                    br = new BufferedReader(fr);
                    String line = "";
                    String[] tempArr;
                    br.readLine();
                    while((line = br.readLine()) != null) {
                        tempArr = line.split(delimiter);
                        entityList.add(new PedidoEntity(tempArr));

                        System.out.println();
                    }
                    br.close();
                    pedidoRepository.saveAll(entityList);
                } catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }


       return "pedidos";
	}
}
