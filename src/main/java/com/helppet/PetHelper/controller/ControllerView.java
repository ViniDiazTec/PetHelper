package com.helppet.PetHelper.controller;

import com.helppet.PetHelper.model.EntityPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Corrige a importação da classe Model correta
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class ControllerView {

    @Autowired
    private RestTemplate restTemplate;  // RestTemplate para fazer chamadas REST

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";  // mapeia para src/main/resources/templates/index.html
    }

    // Exibe a página de adoção e carrega a lista de pets da REST controller
    @GetMapping("/adopt")
    public String showAdoptPage(Model model) {
        // URL do endpoint da REST controller que retorna a lista de pets
        String url = "http://localhost:8080/api/pets/listar";

        // Faz a chamada para a REST controller e pega a lista de pets
        ResponseEntity<EntityPet[]> response = restTemplate.getForEntity(url, EntityPet[].class);

        // Converte a resposta para uma lista
        List<EntityPet> pets = Arrays.asList(response.getBody());

        // Adiciona a lista de pets ao modelo para ser exibida no Thymeleaf
        model.addAttribute("pets", pets);

        return "adopt";  // Renderiza o arquivo adopt.html
    }

    @GetMapping("/adoption")
    public String showAdoptionPage() {
        return "adoption_form";  // mapeia para src/main/resources/templates/adoption.html
    }

    @GetMapping("/details")
    public String showDetailsPage() {
        return "details";  // mapeia para src/main/resources/templates/details.html
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";  // mapeia para src/main/resources/templates/contact.html
    }
}
