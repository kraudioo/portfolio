package br.com.app.portfolio.service;

import br.com.app.portfolio.data.dto.MembroDTO;
import br.com.app.portfolio.data.model.Membro;
import br.com.app.portfolio.data.model.Pessoa;
import br.com.app.portfolio.data.model.Projeto;
import br.com.app.portfolio.data.model.pk.MembroPK;
import br.com.app.portfolio.repository.MembroRepository;
import br.com.app.portfolio.repository.PessoaRepository;
import br.com.app.portfolio.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class MembroService {


    @Autowired
    private MembroRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MembroDTO> obterTodos() {
        return repository.findMembros().stream()
                .map(p -> modelMapper.map(p, MembroDTO.class))
                .collect(Collectors.toList());
    }

    public MembroDTO obterPorId(Long id) {
        Membro Membro = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(Membro, MembroDTO.class);
    }

    public MembroDTO criarMembro(MembroDTO dto) {
        Membro membro = modelMapper.map(dto, Membro.class);
        Optional<Pessoa> listPessoa = pessoaRepository.findById(dto.getIdPessoa()).stream().findFirst();
        Pessoa pessoa = listPessoa.get();
        Projeto projeto = projetoRepository.getById(dto.getIdProjeto());
        MembroPK membroPK = new MembroPK();
        membroPK.setIdPessoa(pessoa.getId());
        membroPK.setIdProjeto(projeto.getId());
        membro.setId(membroPK);
        Membro salvo = repository.save(membro);

        return modelMapper.map(membro, MembroDTO.class);
    }

    public MembroDTO alterarMembro(MembroDTO dto) {
        Membro membro = modelMapper.map(dto, Membro.class);
        Optional<Pessoa> listPessoa = pessoaRepository.findById(dto.getIdPessoa()).stream().findFirst();
        Pessoa pessoa = listPessoa.get();
        Projeto projeto = projetoRepository.getById(dto.getIdProjeto());
        MembroPK membroPK = new MembroPK();
        membroPK.setIdPessoa(pessoa.getId());
        membroPK.setIdProjeto(projeto.getId());
        membro.setId(membroPK);
        Membro salvo = repository.saveAndFlush(membro);

        return modelMapper.map(membro, MembroDTO.class);
    }



    public MembroDTO delete(Long id, Long idPessoa) {
        Membro Membro = repository.getById(id);
        repository.delete(Membro);
        return modelMapper.map(Membro, MembroDTO.class);
    }
}
