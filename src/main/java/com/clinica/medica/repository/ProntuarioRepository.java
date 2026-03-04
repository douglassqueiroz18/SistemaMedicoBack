package com.clinica.medica.repository;

import com.clinica.medica.entity.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    List<Prontuario> findByPacienteIdOrderByDataHoraDesc(Long pacienteId);
}