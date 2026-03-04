package com.clinica.medica.service;

import com.clinica.medica.entity.Paciente;
import com.clinica.medica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<Paciente> listar(String nome) {
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome);
        }
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }
    public Optional<Paciente> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Transactional
    public Paciente salvar(Paciente paciente) {
        if (repository.existsByCpf(paciente.getCpf())) {
            throw new RuntimeException("Já existe um paciente cadastrado com este CPF.");
        }
        return repository.save(paciente);
    }

    @Transactional
    public Paciente atualizar(Long id, Paciente dadosAtualizados) {
        return repository.findById(id).map(paciente -> {
            paciente.setNome(dadosAtualizados.getNome());
            paciente.setTelefone(dadosAtualizados.getTelefone());
            paciente.setEmail(dadosAtualizados.getEmail());
            paciente.setConvenio(dadosAtualizados.getConvenio());
            paciente.setNumeroCarteira(dadosAtualizados.getNumeroCarteira());
            paciente.setEndereco(dadosAtualizados.getEndereco());
            return repository.save(paciente);
        }).orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Impossível deletar: Paciente não encontrado.");
        }
        repository.deleteById(id);
    }
}