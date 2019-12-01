package com.edmar.apiStarWarsResistence.relatorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.rebelde.service.RebeldeService;
import com.edmar.apiStarWarsResistence.relatorio.Relatorio;
import com.edmar.apiStarWarsResistence.relatorio.repository.RelatorioRepository;

@Service
public class RelatorioService {
	
	@Autowired
	private RelatorioRepository relatorioRepository;
	@Autowired
	private RebeldeService rebeldeService;
	
	
	public Relatorio gerandoRelatorio() {
		
		Long totalTraidores = this.rebeldeService.countTraidores();
		Long totalRebeldesFieis = this.rebeldeService.countTotalRebeldesFieis();
		List<Rebelde> rebeldesTotais = this.rebeldeService.ListAll();
		
		Relatorio relatorio = new Relatorio();
		relatorio.gerarPorcentagem(rebeldesTotais.size(), totalTraidores, totalRebeldesFieis);
		
		return relatorio;
	}
	
}
