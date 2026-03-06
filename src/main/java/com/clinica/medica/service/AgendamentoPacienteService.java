package com.clinica.medica.service;

import com.clinica.medica.entity.AgendamentoPaciente;
import com.clinica.medica.repository.AgendamentoPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoPacienteService {

    @Autowired
    private AgendamentoPacienteRepository repository;

    public List<AgendamentoPaciente> listarTodos() {
        return repository.findAll();
    }

    public List<AgendamentoPaciente> listarPorPaciente(Long pacienteId) {
        return repository.findByPacienteIdOrderByDataHoraDesc(pacienteId);
    }

    public Optional<AgendamentoPaciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public AgendamentoPaciente salvar(AgendamentoPaciente agendamento) {
        // Regra de negócio: Não permitir agendamentos no passado
        if (agendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar consultas para datas passadas.");
        }
        
        // Define status inicial caso venha vazio
        if (agendamento.getStatus() == null) {
            agendamento.setStatus("AGENDADO");
        }

        return repository.save(agendamento);
    }

    @Transactional
    public AgendamentoPaciente atualizarStatus(Long id, String novoStatus) {
        return repository.findById(id).map(agendamento -> {
            agendamento.setStatus(novoStatus);
            return repository.save(agendamento);
        }).orElseThrow(() -> new RuntimeException("Agendamento não encontrado com o ID: " + id));
    }

    @Transactional
    public void cancelar(Long id) {
        AgendamentoPaciente agendamento = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        
        agendamento.setStatus("CANCELADO");
        
        repository.save(agendamento);
    }
}