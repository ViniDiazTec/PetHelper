package com.helppet.PetHelper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PET_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key
    private String nome;
    private String raca;
    private Integer idade;
    private String descricao;
    
    private String type;  // For file content type (e.g., image/png)
    
    @Lob
    @Column(name = "file_path", length = 1000)
    private String filePath;  // Store file path
}
