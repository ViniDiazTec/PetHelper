package com.helppet.PetHelper.controllerRest;

import com.helppet.PetHelper.entity.PetData;
import com.helppet.PetHelper.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class ControllerRest {

    @Autowired
    private PetService servicePet;

    // Returns the list of all pets
    @GetMapping("/listar")
    public ResponseEntity<List<PetData>> getAllPets() {
        List<PetData> pets = servicePet.listarTodos();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    // Returns a pet by ID
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<PetData> getPetById(@PathVariable Long id) {
        PetData pet = servicePet.buscarPorId(id);
        return pet != null ? new ResponseEntity<>(pet, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add a new pet with image upload
@PostMapping("/adicionar")
public ResponseEntity<PetData> addPet(@ModelAttribute PetData pet, 
                                      @RequestPart("file") MultipartFile file) {
    try {
        PetData novoPet = servicePet.criarPetComImagem(pet, file);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    // Update an existing pet with optional image upload
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PetData> atualizarPet(@PathVariable Long id, 
                                                @Valid @RequestPart("pet") PetData pet,
                                                @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            PetData petAtualizado = servicePet.atualizarPet(id, pet, file);
            return petAtualizado != null ? new ResponseEntity<>(petAtualizado, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a pet by ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPet(@PathVariable Long id) {
        PetData pet = servicePet.buscarPorId(id);
        if (pet != null) {
            servicePet.deletarPet(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
