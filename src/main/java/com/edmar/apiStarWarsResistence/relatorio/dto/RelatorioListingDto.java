package com.edmar.apiStarWarsResistence.relatorio.dto;

import com.edmar.apiStarWarsResistence.relatorio.Relatorio;

import lombok.Data;

@Data
public class RelatorioListingDto {
	
	private double porcentTraidores;
	private double porcentRebeldes;
	private Long pontosPerdidos;
	private Integer mediaArmaPorRecursos;
	private Integer mediaComidaPorRecursos;
	private Integer mediaMunicaoPorRecursos;
	private Integer mediaAguaPorRecursos;
	private Integer totalRebeldes;
	
	public RelatorioListingDto convertToDto(Relatorio relatorio) {
		
		this.setPorcentTraidores(relatorio.getPorcentTraidores());
		this.setPorcentRebeldes(relatorio.getPorcentRebeldes());
		this.setPontosPerdidos(relatorio.getPontosPerdidos());
		this.setMediaArmaPorRecursos(relatorio.getMediaArmaPorRecursos());
		this.setMediaComidaPorRecursos(relatorio.getMediaComidaPorRecursos());
		this.setMediaMunicaoPorRecursos(relatorio.getMediaMunicaoPorRecursos());
		this.setMediaAguaPorRecursos(relatorio.getMediaAguaPorRecursos());
		this.setTotalRebeldes(relatorio.getTotalRebeldes());
		
		return this;
	}
}
