package com.candidatura.espublico.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidosController {


    private static final Logger log = LoggerFactory.getLogger(PedidosController.class);
	
	@GetMapping("/")
	public String pedidos() {
        log.debug("Cargando página para cargar ficheros de pedidos");
        return "pedidos";
    }

	@PostMapping("/cargar")
    public String uploadFile(@RequestParam(name="file", required=false) MultipartFile file,@RequestParam(name="ruta", required=false, defaultValue = "C:\\Users\\34697\\Documents\\Mis Proyectos\\candidaturaespublico\\candidatura\\RegistroVentas1.csv") String ruta, Model model) {
        log.debug("Cargando fichero o ruta del fichero");
        // check if file is empty
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "resultadoFichero";
        }
        
     // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

       model.addAttribute("ruta", file.getName());
       return "resultadoFichero";
	}
}
