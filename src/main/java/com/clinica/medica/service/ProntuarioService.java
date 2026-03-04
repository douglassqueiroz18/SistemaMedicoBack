package com.clinica.medica.service;

import com.clinica.medica.entity.Prontuario;
import com.clinica.medica.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;

    public List<Prontuario> listarPorPaciente(Long pacienteId) {
        return prontuarioRepository.findByPacienteIdOrderByDataHoraDesc(pacienteId);
    }

    public Prontuario salvar(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }
    public void deletar(Long id) {
        prontuarioRepository.deleteById(id);
    }
    public List<Prontuario> listarTodos() {
    return prontuarioRepository.findAll();
    }
}