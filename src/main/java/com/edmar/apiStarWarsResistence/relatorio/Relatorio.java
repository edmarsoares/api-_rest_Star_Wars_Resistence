package com.edmar.apiStarWarsResistence.relatorio;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.edmar.apiStarWarsResistence.inventario.item.TipoItem;

import lombok.Data;

@Data
public class Relatorio {

	private double porcentTraidores;
	private double porcentRebeldes;
	private Long pontosPerdidos;
	private Integer mediaArmaPorRecursos;
	private Integer mediaComidaPorRecursos;
	private Integer mediaMunicaoPorRecursos;
	private Integer mediaAguaPorRecursos;
	private Integer totalRebeldes;
	
	/**
	 * strategyMap para geraçaõ de media de recursos por rebelde
	 */
	Map<TipoItem, Function<DadosMediaInventario, Integer>> strategy;

	public Relatorio() {
		this.strategy = new HashMap<>();
		this.strategy.put(TipoItem.ARMA, this::calculoMediaArma);
		this.strategy.put(TipoItem.AGUA, this::calculoMediaAgua);
		this.strategy.put(TipoItem.COMIDA, this::calculoMediaComida);
		this.strategy.put(TipoItem.MUNICAO, this::calculoMediaMunicao);

	}


	public void gerarPorcentagem(int total, long percentTraidores, long percentRebeldes) {

		this.totalRebeldes = total;

		this.porcentTraidores = this.calculoPorcentagem(percentTraidores, total);
		this.porcentRebeldes = this.calculoPorcentagem(percentRebeldes, total);
	}

	public double calculoPorcentagem(long percentual, long total) {
		double porcentagem = (percentual * 100) / total;

		return porcentagem;
	}

	public Integer calculoMediaArma(DadosMediaInventario dadosMedia) {
		this.mediaArmaPorRecursos = this.calculoMedia(dadosMedia.getTotalItem(), dadosMedia.getTotalRebeldes());

		return this.mediaArmaPorRecursos;
	}
	
	public Integer calculoMediaAgua(DadosMediaInventario dadosMedia) {
		this.mediaAguaPorRecursos = this.calculoMedia(dadosMedia.getTotalItem(), dadosMedia.getTotalRebeldes());

		return this.mediaAguaPorRecursos;
	}
	
	public Integer calculoMediaMunicao(DadosMediaInventario dadosMedia) {

		this.mediaMunicaoPorRecursos = this.calculoMedia(dadosMedia.getTotalItem(), dadosMedia.getTotalRebeldes());

		return this.mediaMunicaoPorRecursos;
	}
	
	public Integer calculoMediaComida(DadosMediaInventario dadosMedia) {
		this.mediaComidaPorRecursos = this.calculoMedia(dadosMedia.getTotalItem(), dadosMedia.getTotalRebeldes());

		return this.mediaComidaPorRecursos;
	}
	
	public Integer calculoMedia(Integer parcial , Integer total) {
		return parcial / total;
	}
	
	/**
	 * Apartir desse método é que é gerado as médias de cada recurso
	 * Ex.: Arma: 2 por rebelde, comida: 5 por rebelde
	 * @param dadosMedia
	 */
	public void gerarMedia(DadosMediaInventario dadosMedia) {
		this.strategy.get(dadosMedia.getTipoItem()).apply(dadosMedia);
	}
}
