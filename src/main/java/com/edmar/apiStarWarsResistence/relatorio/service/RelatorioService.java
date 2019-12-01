package com.edmar.apiStarWarsResistence.relatorio.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.apiStarWarsResistence.inventario.item.Item;
import com.edmar.apiStarWarsResistence.inventario.item.TipoItem;
import com.edmar.apiStarWarsResistence.inventario.item.service.ItemService;
import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.rebelde.service.RebeldeService;
import com.edmar.apiStarWarsResistence.relatorio.DadosMediaInventario;
import com.edmar.apiStarWarsResistence.relatorio.Relatorio;

@Service
public class RelatorioService {

	@Autowired
	private RebeldeService rebeldeService;
	@Autowired
	private ItemService itemService;

	public Relatorio gerandoRelatorio() {

		Long totalTraidores = this.rebeldeService.countTraidores();
		Long totalRebeldesFieis = this.rebeldeService.countTotalRebeldesFieis();
		List<Rebelde> rebeldesTotais = this.rebeldeService.ListAll();
		Long pontosPerdidosPorTraidores = this.itemService.getSomaPontosPerdidosPorTraidores();

		List<Item> itens = rebeldesTotais.stream().map(rebelde -> rebelde.getInventario().getItens()).reduce(
				(itens1, itens2) -> Stream.concat(itens1.stream(), itens2.stream()).collect(Collectors.toList())).get();

		Relatorio relatorio = new Relatorio();
		relatorio.gerarPorcentagem(rebeldesTotais.size(), totalTraidores, totalRebeldesFieis);
		
		DadosMediaInventario dadosParaGerarMediaArma = this.gerandoCalculoPorMediaItensInventario(TipoItem.ARMA);
		DadosMediaInventario dadosParaGerarMediaComida = this.gerandoCalculoPorMediaItensInventario(TipoItem.COMIDA);
		DadosMediaInventario dadosParaGerarMediaMunicao = this.gerandoCalculoPorMediaItensInventario(TipoItem.MUNICAO);
		DadosMediaInventario dadosParaGerarMediaAgua = this.gerandoCalculoPorMediaItensInventario(TipoItem.AGUA);
		
		relatorio.gerarMedia(dadosParaGerarMediaArma);
		relatorio.gerarMedia(dadosParaGerarMediaComida);
		relatorio.gerarMedia(dadosParaGerarMediaMunicao);
		relatorio.gerarMedia(dadosParaGerarMediaAgua);
		
		relatorio.setPontosPerdidos(pontosPerdidosPorTraidores);
		return relatorio;
	}

	public DadosMediaInventario gerandoCalculoPorMediaItensInventario(TipoItem tipoItem) {
		List<Rebelde> rebeldesTotais = this.rebeldeService.ListAll();
		 
		List<Item> itens = rebeldesTotais.stream()
				.map( rebelde -> rebelde.getInventario().getItens())
				.reduce((itens1, itens2) -> Stream.concat(itens1.stream(), itens2.stream())
				.collect(Collectors.toList())).get();
		
		Integer totalArma = itens.stream()
			.filter(item -> item.getTipoItem().equals(tipoItem))
			.map(item -> item.getQuantidade())
			.reduce((item1, item2) -> item1 + item2).get();
		
		DadosMediaInventario dados = this.montarDadosParaGerarMedia(totalArma, rebeldesTotais.size(), tipoItem);
		return dados; 
	}
	

	public DadosMediaInventario montarDadosParaGerarMedia(Integer total,Integer totalRebeldes, TipoItem tipoItem) {
		DadosMediaInventario dadosInventario = new DadosMediaInventario();
		dadosInventario.setTotalItem(total);
		dadosInventario.setTotalRebeldes(totalRebeldes);
		dadosInventario.setTipoItem(tipoItem);
		
		return dadosInventario;
	}

}
