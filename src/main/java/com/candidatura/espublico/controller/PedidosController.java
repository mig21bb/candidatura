package com.candidatura.espublico.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PedidosController {
	
	
	  @GetMapping("/")
	    public String homepage() {
	        return "index";
	    }
	
	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {

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
