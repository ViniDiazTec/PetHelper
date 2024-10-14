package com.helppet.PetHelper.service;

import com.helppet.PetHelper.model.EntityPet;
import com.helppet.PetHelper.repository.RepositoryPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePet {

    @Autowired
    private RepositoryPet repositoryPet;

    // Lista todos os pets
    public List<EntityPet> listarTodos() {
        return repositoryPet.findAll();
    }

    // Busca um pet por ID
    public EntityPet buscarPorId(Integer id) {
        Optional<EntityPet> pet = repositoryPet.findById(id);
        return pet.orElse(null); // Retorna null se o pet não for encontrado
    }

    // Cria um novo pet
    public EntityPet criarPet(EntityPet pet) {
        return repositoryPet.save(pet);
    }

    // Atualiza um pet existente
    public EntityPet atualizarPet(Integer id, EntityPet pet) {
        if (repositoryPet.existsById(id)) {
            pet.setId(id); // Garantir que o ID não seja alterado
            return repositoryPet.save(pet);
        }
        return null; // Retorna null se o pet não for encontrado
    }

    // Deleta um pet por ID
    public void deletarPet(Integer id) {
        if (repositoryPet.existsById(id)) {
            repositoryPet.deleteById(id);
        }
    }
}
