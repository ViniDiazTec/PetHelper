package com.helppet.PetHelper.service;

import com.helppet.PetHelper.entity.PetData;
import com.helppet.PetHelper.repository.PetRepository;
import com.helppet.PetHelper.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    // List all pets
    public List<PetData> listarTodos() {
        return petRepository.findAll();
    }

    // Get pet by ID
    public PetData buscarPorId(Long id) {
        Optional<PetData> pet = petRepository.findById(id);
        return pet.orElse(null);
    }

    // Create a new pet with image upload
    public PetData criarPetComImagem(PetData pet, MultipartFile imagem) throws Exception {
        boolean uploadSucesso = Upload.fazerUploadImagem(imagem);
        
        if (uploadSucesso) {
            String nomeArquivo = imagem.getOriginalFilename();
            pet.setFilePath(nomeArquivo);  // Store the file path in the PetData entity
            return petRepository.save(pet);
        } else {
            throw new Exception("Erro ao fazer upload da imagem");
        }
    }

    // Update existing pet
    public PetData atualizarPet(Long id, PetData pet, MultipartFile imagem) throws Exception {
        if (petRepository.existsById(id)) {
            pet.setId(id);

            // If a new image is provided, upload it and set the file path
            if (imagem != null && !imagem.isEmpty()) {
                boolean uploadSucesso = Upload.fazerUploadImagem(imagem);
                if (uploadSucesso) {
                    pet.setFilePath(imagem.getOriginalFilename());
                } else {
                    throw new Exception("Erro ao fazer upload da imagem");
                }
            }

            return petRepository.save(pet);
        }
        return null;
    }

    // Delete pet by ID
    public void deletarPet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        }
    }
}
