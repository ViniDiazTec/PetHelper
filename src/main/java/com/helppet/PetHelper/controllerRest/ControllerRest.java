package com.helppet.PetHelper.controllerRest;

import com.helppet.PetHelper.entity.PetData;
import com.helppet.PetHelper.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")  // Prefixo 'api' para rotas relacionadas a pets
public class ControllerRest {

    @Autowired
    private PetService servicePet;

    // Retorna a lista de todos os pets
    @GetMapping("/listar")
    public ResponseEntity<List<PetData>> getAllPets() {
        List<PetData> pets = servicePet.listarTodos();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    // Retorna um pet pelo ID
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<PetData> getPetById(@PathVariable Long id) {
        PetData pet = servicePet.buscarPorId(id);
        return pet != null ? new ResponseEntity<>(pet, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Adiciona um novo pet
    @PostMapping("/adicionar")
    public ResponseEntity<PetData> addPet(@Valid @RequestBody PetData pet) {
        PetData novoPet = servicePet.criarPet(pet);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    }

    // Atualiza um pet existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PetData> atualizarPet(@PathVariable Long id, @Valid @RequestBody PetData pet) {
        PetData petAtualizado = servicePet.atualizarPet(id, pet);
        return petAtualizado != null ? new ResponseEntity<>(petAtualizado, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Método para deletar um pet
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPet(@PathVariable Long id) {
        PetData pet = servicePet.buscarPorId(id);
        if (pet != null) {
            servicePet.deletarPet(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Status 204 após exclusão bem-sucedida
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
