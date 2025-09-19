package com.augusto.SpringBoot.service;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.augusto.SpringBoot.repository.CorrecaoRepository;
import com.augusto.SpringBoot.repository.AnotacaoRepository;
import com.augusto.SpringBoot.entity.Correcao;
import com.augusto.SpringBoot.entity.Anotacao;

@Service
public class CorrecaoService implements ICorrecaoService {

    @Autowired
    private CorrecaoRepository correcaoRepo;

    @Autowired
    private AnotacaoRepository anotacaoRepo;

    public List<Correcao> findByNotaGreaterThanAndDataAfter(Double nota, LocalDateTime data) {
        return correcaoRepo.findByNotaGreaterThanAndDataAfter(nota, data);
    };

    public Correcao novaCorrecao(Correcao correcao) {
        if (correcao.getAnotacao() == null || correcao.getAnotacao().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anotação não informada");
        }
        
        Optional<Anotacao> anotacaoOptional = anotacaoRepo.findById(correcao.getAnotacao().getId());
        if (anotacaoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anotação não encontrada");
        }
        
        if (correcao.getNota() == null || correcao.getNota() < 0 || correcao.getNota() > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nota deve estar entre 0 e 10");
        }
        
        if (correcao.getFeedback() == null || correcao.getFeedback().trim().length() <= 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Feedback deve ter mais de 5 caracteres");
        }
        
        String feedback = correcao.getFeedback().trim();
        if (!Character.isUpperCase(feedback.charAt(0))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Feedback deve começar com letra maiúscula");
        }

        if (correcao.getData() == null) {
            correcao.setData(LocalDate.now());
        }
        
        correcao.setAnotacao(anotacaoOptional.get());
        return correcaoRepo.save(correcao);
    };

    public List<Correcao> buscarTodos() {
        return correcaoRepo.findAll();
    };
}
