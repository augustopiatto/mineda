package com.augusto.SpringBoot.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.augusto.SpringBoot.entity.Correcao;

public interface CorrecaoRepository extends JpaRepository<Correcao, Long>{

    @Query("SELECT c FROM Correcao c WHERE c.nota > :nota AND c.anotacao.dataHora > :data")
    List<Correcao> findByNotaGreaterThanAndDataAfter(@Param("nota") Double nota, @Param("data") LocalDateTime data);

}
