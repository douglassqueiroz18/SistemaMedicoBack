package com.clinica.medica.controller;

import com.clinica.medica.entity.Prontuario;
import com.clinica.medica.service.ProntuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    // Busca o histórico de atendimentos de um paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Prontuario>> listarPorPaciente(@PathVariable Long pacienteId) {
        List<Prontuario> historico = prontuarioService.listarPorPaciente(pacienteId);
        return ResponseEntity.ok(historico);
    }

    // Salva um novo atendimento (Anamnese e Prescrição)
    @PostMapping
    public ResponseEntity<Prontuario> criar(@RequestBody Prontuario prontuario) {
        Prontuario novoProntuario = prontuarioService.salvar(prontuario);
        return new ResponseEntity<>(novoProntuario, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    prontuarioService.deletar(id);
    return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Prontuario>> listarTodos() {
        List<Prontuario> lista = prontuarioService.listarTodos(); // Crie este método no Service também
        return ResponseEntity.ok(lista);
    }
}