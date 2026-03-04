package com.clinica.medica.entity;

import jakarta.persistence.*;
import lombok.*; // Importa Getter, Setter, etc.
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "paciente")
@Getter // Adicione explicitamente
@Setter // Adicione explicitamente
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate dataNascimento;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String convenio;

    @Column(name = "numero_carteira")
    private String numeroCarteira;

    private String endereco;
    private String cidade;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Prontuario> prontuarios;
}