package br.com.app.portfolio.controller;

import br.com.app.portfolio.data.dto.PessoaDTO;
import br.com.app.portfolio.service.PessoaService;
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
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping()
    public List<PessoaDTO> listarTodos() {
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> listarPorId(@PathVariable @NotNull Long id) {
        PessoaDTO dto = service.obterPorId(id);

        return  ResponseEntity.ok(dto);
    }
    @PostMapping()
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody @Valid PessoaDTO dto, UriComponentsBuilder uriBuilder) {
        PessoaDTO pedidoRealizado = service.criarPessoa(dto);

        URI endereco = uriBuilder.path("/pessoa").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> alterarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoa){
        PessoaDTO dto = service.alterarPessoa(id, pessoa);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaDTO> delete(@PathVariable Long id){
        PessoaDTO dto = service.delete(id);
        return ResponseEntity.ok(dto);
    }


}
