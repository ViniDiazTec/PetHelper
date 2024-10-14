package com.helppet.PetHelper.repository;

import com.helppet.PetHelper.model.EntityPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPet extends JpaRepository<EntityPet, Integer> {
    // O JpaRepository já fornece os métodos CRUD básicos, como:
    // save(), findAll(), findById(), deleteById(), etc.
}
