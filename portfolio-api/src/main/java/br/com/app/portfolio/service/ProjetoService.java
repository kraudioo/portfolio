package br.com.app.portfolio.service;

import br.com.app.portfolio.data.dto.ProjetoDTO;
import br.com.app.portfolio.data.model.Pessoa;
import br.com.app.portfolio.data.model.Projeto;
import br.com.app.portfolio.repository.PessoaRepository;
import br.com.app.portfolio.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<ProjetoDTO> obterTodos() {
        List<Projeto> list = repository.findAll();

        List<Projeto> projetosOrdenadosPorRisco = list.stream()
                .sorted(Comparator.comparing(Projeto::getRisco))
                .collect(Collectors.toList());

        List<ProjetoDTO> projetoDTOS = new ArrayList<>();
       for (Projeto projeto : projetosOrdenadosPorRisco){
           ProjetoDTO pro = new ProjetoDTO();
           pro.setNome(projeto.getNome());
           pro.setDataInicio(projeto.getDataInicio());
           pro.setDataPrevisaoFim(projeto.getDataPrevisaoFim());
           pro.setDataFim(projeto.getDataFim());
           pro.setDescricao(projeto.getDescricao());
           pro.setStatus(projeto.getStatus());
           pro.setOrcamento(projeto.getOrcamento());
           pro.setRisco(projeto.getRisco());
           pro.setIdgerente(projeto.getGerente().getId());
           pro.setId(projeto.getId());
           projetoDTOS.add(pro);
       }

       return projetoDTOS;
    }

    public ProjetoDTO obterPorId(Long id) {
        Projeto Projeto = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        ProjetoDTO pro = new ProjetoDTO();
        pro.setNome(Projeto.getNome());
        pro.setDataInicio(Projeto.getDataInicio());
        pro.setDataPrevisaoFim(Projeto.getDataPrevisaoFim());
        pro.setDataFim(Projeto.getDataFim());
        pro.setDescricao(Projeto.getDescricao());
        pro.setStatus(Projeto.getStatus());
        pro.setOrcamento(Projeto.getOrcamento());
        pro.setRisco(Projeto.getRisco());
        pro.setIdgerente(Projeto.getGerente().getId());
        pro.setId(Projeto.getId());
        return pro;
    }

    public ProjetoDTO criarProjeto(ProjetoDTO dto) {
        Projeto projeto = modelMapper.map(dto, Projeto.class);

        Long nextVal = repository.getNextValMySequence();
        if (nextVal==null)
            nextVal=1L;
        projeto.setId(nextVal);
        Pessoa pessoa = pessoaRepository.findById(dto.getIdgerente())
                .orElseThrow(EntityNotFoundException::new);
          projeto.setGerente(pessoa);
        Projeto salvo = repository.save(projeto);

        return modelMapper.map(projeto, ProjetoDTO.class);
    }

    public ProjetoDTO alterarProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projeto = repository.getById(id);

        projeto.setNome(projetoDTO.getNome());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataPrevisaoFim(projetoDTO.getDataPrevisaoFim());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setStatus(projetoDTO.getStatus());
        projeto.setOrcamento(projetoDTO.getOrcamento());
        projeto.setRisco(projetoDTO.getRisco());
        Pessoa pessoa = pessoaRepository.findById(projetoDTO.getIdgerente())
                .orElseThrow(EntityNotFoundException::new);
        projeto.setGerente(pessoa);
        Projeto salvo = repository.saveAndFlush(projeto);

        ProjetoDTO pro = new ProjetoDTO();
        pro.setNome(projetoDTO.getNome());
        pro.setDataInicio(projetoDTO.getDataInicio());
        pro.setDataPrevisaoFim(projetoDTO.getDataPrevisaoFim());
        pro.setDataFim(projetoDTO.getDataFim());
        pro.setDescricao(projetoDTO.getDescricao());
        pro.setStatus(projetoDTO.getStatus());
        pro.setOrcamento(projetoDTO.getOrcamento());
        pro.setRisco(projetoDTO.getRisco());
        pro.setIdgerente(projeto.getGerente().getId());
        return pro;
    }


    public ProjetoDTO delete(Long id) {
        Projeto Projeto = repository.getById(id);
        repository.delete(Projeto);
        return modelMapper.map(Projeto, ProjetoDTO.class);
    }
}