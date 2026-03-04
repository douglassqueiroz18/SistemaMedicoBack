package com.clinica.medica.repository;

import com.clinica.medica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCpf(String cpf);

    Optional<Paciente> findByCpf(String cpf);    
}