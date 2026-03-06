package com.clinica.medica.repository;

import com.clinica.medica.entity.AgendamentoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AgendamentoPacienteRepository extends JpaRepository<AgendamentoPaciente, Long> {

    List<AgendamentoPaciente> findByPacienteIdOrderByDataHoraDesc(Long pacienteId);

    List<AgendamentoPaciente> findByStatus(String status);

    @Query("SELECT a FROM AgendamentoPaciente a WHERE a.status <> 'CANCELADO' OR a.status IS NULL")
    List<AgendamentoPaciente> findAllAtivos();
}