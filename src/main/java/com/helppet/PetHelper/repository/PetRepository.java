package com.helppet.PetHelper.repository;

import com.helppet.PetHelper.entity.PetData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetData, Long> {
    Optional<PetData> findByNome(String nome);  // Custom method to find by file name

    public boolean existsById(Integer id);
}
