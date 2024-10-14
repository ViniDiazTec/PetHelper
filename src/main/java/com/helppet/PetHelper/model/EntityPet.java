package com.helppet.PetHelper.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "pets") // Define o nome da tabela no banco de dados
public class EntityPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Integer id;

    @NotBlank(message = "O nome é obrigatório") // Validação do nome
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres") // Tamanho máximo do nome
    private String nome;

    @NotBlank(message = "A raça é obrigatória") // Validação da raça
    @Size(max = 50, message = "A raça deve ter no máximo 50 caracteres") // Tamanho máximo da raça
    private String raca;

    @Min(value = 0, message = "A idade deve ser maior ou igual a 0") // Validação para idade mínima
    private Integer idade;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres") // Tamanho máximo da descrição
    private String descricao;

    @NotBlank(message = "A imagem é obrigatória") // Validação para campo imagem
    private String imagem;  // URL ou caminho da imagem do pet

    /*// Relacionamento com uma entidade hipotética, como 'AdoptionEntity'
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdoptionEntity> adocoes = new ArrayList<>(); // Lista de adoções para o pet*/
}
