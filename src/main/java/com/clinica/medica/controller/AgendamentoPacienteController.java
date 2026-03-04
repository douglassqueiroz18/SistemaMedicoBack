package com.clinica.medica.controller;

import com.clinica.medica.entity.AgendamentoPaciente;
import com.clinica.medica.entity.Paciente;
import com.clinica.medica.service.AgendamentoPacienteService;
import com.clinica.medica.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AgendamentoPacienteController {

    private final AgendamentoPacienteService agendamentoService;
    private final PacienteService pacienteService;

    @PostMapping("/{id}/agendar")
    public ResponseEntity<AgendamentoPaciente> agendar(@PathVariable Long id, @RequestBody AgendamentoPaciente agendamento) {
        Paciente paciente = pacienteService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
        agendamento.setPaciente(paciente);
        
        AgendamentoPaciente salvo = agendamentoService.salvar(agendamento);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<AgendamentoPaciente>> listarPorPaciente(@PathVariable Long id) {
        List<AgendamentoPaciente> lista = agendamentoService.listarPorPaciente(id);
        return ResponseEntity.ok(lista);
    }
    @GetMapping // Este método responde ao GET em /api/agendamentos
    public ResponseEntity<List<AgendamentoPaciente>> listarTodos() {
        List<AgendamentoPaciente> todos = agendamentoService.listarTodos();
        return ResponseEntity.ok(todos);
    }
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        agendamentoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}