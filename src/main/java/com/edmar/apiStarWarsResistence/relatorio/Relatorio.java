package com.edmar.apiStarWarsResistence.relatorio;

public class Relatorio {
	
	private double porcentTraidores;
	private double porcentRebeldes;
	private Integer pontosPerdidos;
	private Integer mediaPorRecursos;
	
	private Integer totalRebeldes;
	
	public void gerarPorcentagem(int total, long percentTraidores, long percentRebeldes) {

		this.totalRebeldes = total;

		this.porcentTraidores = this.calculoPorcentagem(percentTraidores, total);
		this.porcentRebeldes = this.calculoPorcentagem(percentRebeldes, total);
	}

	public double calculoPorcentagem(long percentual, long total) {
		double porcentagem = (percentual * 100) / total;

		return porcentagem;
	}
}
