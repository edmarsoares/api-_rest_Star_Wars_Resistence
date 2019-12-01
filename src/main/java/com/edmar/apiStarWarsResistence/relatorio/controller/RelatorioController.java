package com.edmar.apiStarWarsResistence.relatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.apiStarWarsResistence.relatorio.Relatorio;
import com.edmar.apiStarWarsResistence.relatorio.service.RelatorioService;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping
	public ResponseEntity<Relatorio> gerandoRelatorio(){
		Relatorio relatorio = this.relatorioService.gerandoRelatorio();
		
		return ResponseEntity.ok(relatorio);
	}

}
