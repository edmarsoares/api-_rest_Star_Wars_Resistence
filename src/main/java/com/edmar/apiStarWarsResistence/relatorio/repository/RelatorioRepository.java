package com.edmar.apiStarWarsResistence.relatorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmar.apiStarWarsResistence.relatorio.Relatorio;
import com.sun.xml.bind.v2.model.core.ID;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long>{

}
