/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helppet.PetHelper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerView {

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";  // mapeia para src/main/resources/templates/index.html
    }

    @GetMapping("/adopt")
    public String showAdoptPage() {
        return "adopt";  // mapeia para src/main/resources/templates/adopt.html
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
