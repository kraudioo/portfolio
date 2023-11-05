package br.com.app.portfolio.controller;

import br.com.app.portfolio.data.dto.ProjetoDTO;
import br.com.app.portfolio.service.ProjetoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @GetMapping()
    public List<ProjetoDTO> listarTodos() {
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> listarPorId(@PathVariable @NotNull Long id) {
        ProjetoDTO dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<ProjetoDTO> criarProjeto(@RequestBody @Valid ProjetoDTO dto, UriComponentsBuilder uriBuilder) {
        ProjetoDTO pedidoRealizado = service.criarProjeto(dto);

        URI endereco = uriBuilder.path("/Projeto").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> atualiza(@PathVariable Long id, @RequestBody ProjetoDTO Projeto) {
        ProjetoDTO dto = service.alterarProjeto(id, Projeto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjetoDTO> deleta(@PathVariable Long id) {
        ProjetoDTO dto = service.delete(id);
        return ResponseEntity.ok(dto);
    }
}
