package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.repository.MotoristaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {
    private final MotoristaRepository repository;

    public MotoristaService(MotoristaRepository repository) {
        this.repository = repository;
    }

    public List<Motorista> listarTodos() {
        return repository.findAll();
    }

    public Optional<Motorista> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Motorista salvar(Motorista motorista) {
        return repository.save(motorista);
    }

    public Motorista atualizar(Integer id, Motorista dados) {
        return repository.findById(id).map(m -> {
            m.setCpf(dados.getCpf());
            m.setUnidade(dados.getUnidade());
            m.setCnh(dados.getCnh());
            m.setNomeCompleto(dados.getNomeCompleto());
            m.setTelefone(dados.getTelefone());
            m.setEmailEmpresa(dados.getEmailEmpresa());
            m.setTipoRisco(dados.getTipoRisco());
            m.setUrlFoto(dados.getUrlFoto());
            m.setStatus(dados.getStatus());
            m.setIsinactive(dados.getIsinactive());
            return repository.save(m);
        }).orElseThrow(() -> new RuntimeException("Motorista não encontrado!"));
    }

    public void deletar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Motorista não encontrado!");
        }
    }
}
