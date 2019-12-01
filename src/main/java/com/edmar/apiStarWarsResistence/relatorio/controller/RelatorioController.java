package com.edmar.apiStarWarsResistence.relatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.apiStarWarsResistence.relatorio.Relatorio;
import com.edmar.apiStarWarsResistence.relatorio.dto.RelatorioListingDto;
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
			response=RelatorioListingDto.class, 
			notes="endpoint responsável por gerá relatórios informando porcentagens e médias dos recursos dos rebeldes")
	@GetMapping
	public ResponseEntity<RelatorioListingDto> gerandoRelatorio(){
		
		Relatorio relatorio = this.relatorioService.gerandoRelatorio();
		
		RelatorioListingDto relatorioDto = new RelatorioListingDto();
		relatorioDto.convertToDto(relatorio);
		
		return ResponseEntity.ok(relatorioDto);
	}

}
