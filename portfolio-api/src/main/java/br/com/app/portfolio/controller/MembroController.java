package br.com.app.portfolio.controller;

import br.com.app.portfolio.data.dto.MembroDTO;
import br.com.app.portfolio.service.MembroService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/membro")
public class MembroController {
    @Autowired
    private MembroService servico;

    @GetMapping()
    public List<MembroDTO> listarTodos() {
        return servico.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroDTO> listarPorId(@PathVariable @NotNull Long id) {
        MembroDTO dto = servico.obterPorId(id);

        return  ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<MembroDTO> criarMembro(@RequestBody @Valid MembroDTO dto, UriComponentsBuilder uriBuilder) {
        MembroDTO membro = servico.criarMembro(dto);

        URI endereco = uriBuilder.path("/membro").buildAndExpand(membro.getIdPessoa()).toUri();

        return ResponseEntity.created(endereco).body(membro);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MembroDTO> atualizaStatus(@PathVariable Long id, @RequestBody MembroDTO membro){
        MembroDTO dto = servico.alterarMembro(membro);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MembroDTO> delete(@PathVariable Long id, @PathVariable Long idPessoa){
        MembroDTO dto = servico.delete(id, idPessoa);
        return ResponseEntity.ok(dto);
    }
}
