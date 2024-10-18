package com.helppet.PetHelper.controller;

import com.helppet.PetHelper.entity.PetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ControllerView {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/adopt")
    public String showAdoptPage(Model model) {
        String url = "http://localhost:8080/api/pets/listar";

        try {
            ResponseEntity<PetData[]> response = restTemplate.getForEntity(url, PetData[].class);
            List<PetData> pets = (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) 
                ? Arrays.asList(response.getBody()) 
                : List.of();
            model.addAttribute("pets", pets);
        } catch (RestClientException e) {
            model.addAttribute("error", "Unable to load pets. Please try again later.");
        }

        return "adopt";
    }
    
@GetMapping("/details/{id}")
public String detalhesPet(@PathVariable Long id, Model model) {
    String url = "http://localhost:8080/api/pets/pesquisar/" + id;
    
    try {
        ResponseEntity<PetData> response = restTemplate.getForEntity(url, PetData.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            PetData pet = response.getBody();
            model.addAttribute("pet", pet);
            return "details"; // Nome da página HTML
        } else {
            return "redirect:/error";
        }
    } catch (RestClientException e) {
        return "redirect:/error"; // Trata o erro de conexão ou de API
    }
}

    @GetMapping("/adoption")
    public String showAdoptionPage() {
        return "adoption_form";
    }

    @GetMapping("/details")
    public String showDetailsPage() {
        return "details";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/cadastro")
    public String showCadastroPage() {
        return "cadastro_pet";
    }
}
