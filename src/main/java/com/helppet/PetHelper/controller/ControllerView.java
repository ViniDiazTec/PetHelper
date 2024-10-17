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

@Controller
public class ControllerView {

    @Autowired
    private RestTemplate restTemplate;  // Bean for RestTemplate

    // Endpoint to show the index page
    @GetMapping("/index")
    public String showIndexPage() {
        return "index";  // Maps to src/main/resources/templates/index.html
    }

    // Displays the adoption page and loads the list of pets from the REST controller
    @GetMapping("/adopt")
    public String showAdoptPage(Model model) {
        String url = "http://localhost:8080/api/pets/listar";  // REST API endpoint

        try {
            // Fetch the list of pets from the REST API
            ResponseEntity<PetData[]> response = restTemplate.getForEntity(url, PetData[].class);

            // Check if the response has a valid status and body
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<PetData> pets = Arrays.asList(response.getBody());  // Convert array to list
                model.addAttribute("pets", pets);  // Add pets to the model for Thymeleaf rendering
            } else {
                model.addAttribute("pets", List.of());  // Add an empty list if no pets are returned
            }
        } catch (RestClientException e) {
            // Handle any exceptions during the REST call (e.g., service unavailable)
            model.addAttribute("error", "Unable to load pets. Please try again later.");
        }

        return "adopt";  // Renders the adopt.html template
    }

    // Endpoint to show the adoption form page
    @GetMapping("/adoption")
    public String showAdoptionPage() {
        return "adoption_form";  // Maps to src/main/resources/templates/adoption_form.html
    }

    // Endpoint to show the pet details page
    @GetMapping("/details")
    public String showDetailsPage() {
        return "details";  // Maps to src/main/resources/templates/details.html
    }

    // Endpoint to show the contact page
    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";  // Maps to src/main/resources/templates/contact.html
    }

    // Endpoint to show the pet registration (cadastro) page
    @GetMapping("/cadastro")
    public String showCadastroPage() {
        return "cadastro_pet";  // Maps to src/main/resources/templates/cadastro_pet.html
    }
}
