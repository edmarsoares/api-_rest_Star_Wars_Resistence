package com.edmar.apiStarWarsResistence.relatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.relatorio.Relatorio;
import com.edmar.apiStarWarsResistence.relatorio.service.RelatorioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/relatorio")
@Api("RelatorioController")
public class RelatorioController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@ApiOperation(
			value="gerar relatórios", 
			response=Relatorio.class, 
			notes="endpoint responsável por gerá relatórios informando porcentagens e médias dos recursos dos rebeldes")
	@GetMapping
	public ResponseEntity<Relatorio> gerandoRelatorio(){
		Relatorio relatorio = this.relatorioService.gerandoRelatorio();
		
		return ResponseEntity.ok(relatorio);
	}

}
