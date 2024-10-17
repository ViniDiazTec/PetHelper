package com.helppet.PetHelper.service;

import com.helppet.PetHelper.entity.PetData;
import com.helppet.PetHelper.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    private final String FOLDER_PATH = "PetHelper/src/main/resources/static/imagens/";

    // Upload image
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        PetData petData = petRepository.save(PetData.builder()
                .nome(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build());

        file.transferTo(new File(filePath)); // Saves the file to disk

        if (petData != null) {
            return "File uploaded successfully: " + filePath;
        }
        return "File upload failed.";
    }

    // Download image
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<PetData> petData = petRepository.findByNome(fileName);

        if (petData.isPresent()) {
            String filePath = petData.get().getFilePath();  // Fetch file path from DB
            return Files.readAllBytes(new File(filePath).toPath());  // Return file as byte array
        }

        throw new IOException("File not found with name: " + fileName);
    }

    // List all pets
    public List<PetData> listarTodos() {
        return petRepository.findAll();
    }

    // Get pet by ID
    public PetData buscarPorId(Long id) {
        Optional<PetData> pet = petRepository.findById(id);
        return pet.orElse(null); // Return null if the pet is not found
    }

    // Create a new pet
    public PetData criarPet(PetData pet) {
        return petRepository.save(pet); // Fixed typo: using petRepository
    }

    // Update existing pet
    public PetData atualizarPet(Long id, PetData pet) {
        if (petRepository.existsById(id)) {
            pet.setId(id); // Ensure the ID is not changed
            return petRepository.save(pet);
        }
        return null; // Return null if the pet is not found
    }

    // Delete pet by ID
    public void deletarPet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        }
    }
}
