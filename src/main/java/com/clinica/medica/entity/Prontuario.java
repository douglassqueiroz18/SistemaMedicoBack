package com.clinica.medica.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prontuarios")
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Vários atendimentos para um paciente
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonBackReference // <-- ADICIONE ISSO AQUI
    private Paciente paciente;

    @Column(columnDefinition = "TEXT") // Para textos longos
    private String anamnese;

    @Column(columnDefinition = "TEXT")
    private String prescricao;

    private LocalDateTime dataHora = LocalDateTime.now();
    
    // Getters e Setters
}