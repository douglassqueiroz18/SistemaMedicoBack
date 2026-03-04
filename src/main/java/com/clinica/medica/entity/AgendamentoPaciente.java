package com.clinica.medica.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 200)
    private String observacao; // Ex: "Retorno", "Primeira consulta", "Urgência"

    @Column(nullable = false)
    private String status; // Ex: "AGENDADO", "CANCELADO", "REALIZADO"

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
}