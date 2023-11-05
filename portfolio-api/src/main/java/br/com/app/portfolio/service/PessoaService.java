package br.com.app.portfolio.service;

import br.com.app.portfolio.data.dto.PessoaDTO;
import br.com.app.portfolio.data.model.Pessoa;
import br.com.app.portfolio.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<PessoaDTO> obterTodos() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public PessoaDTO obterPorId(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public PessoaDTO criarPessoa(PessoaDTO dto) {
        Long nextVal = repository.getNextValMySequence();
        if (nextVal==null)
            nextVal=1L;
        Pessoa pessoa = modelMapper.map(dto, Pessoa.class);
        pessoa.setId(nextVal);
        pessoa.setCpf(dto.getCpf());
      pessoa.setNome(dto.getNome());
      pessoa.setFuncionario(dto.getFuncionario());
      pessoa.setDataNascimento(dto.getDataNascimento());
        Pessoa salvo = repository.save(pessoa);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public PessoaDTO alterarPessoa(Long id, PessoaDTO dto) {
        Optional<Pessoa> listPessoa = repository.findById(id).stream().findFirst();
        Pessoa pessoa = listPessoa.get();
        pessoa.setCpf(dto.getCpf());
        pessoa.setNome(dto.getNome());
        pessoa.setFuncionario(dto.getFuncionario());
        pessoa.setDataNascimento(dto.getDataNascimento());
        Pessoa salvo = repository.saveAndFlush(pessoa);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }


    public PessoaDTO delete(Long id) {
        Optional<Pessoa> listPessoa = repository.findById(id).stream().findFirst();
        Pessoa pessoa = listPessoa.get();
         repository.delete(pessoa);
        return modelMapper.map(pessoa, PessoaDTO.class);
    }



}
