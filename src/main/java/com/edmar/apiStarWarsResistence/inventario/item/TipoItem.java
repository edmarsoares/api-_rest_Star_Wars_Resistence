package com.edmar.apiStarWarsResistence.inventario.item;

public enum TipoItem implements PontosPorItem {

	ARMA("arma") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 4;
			return pontosTotais;
		}

	},
	MUNICAO("munição") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 3;
			return pontosTotais;
		}

	},
	AGUA("água") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 2;
			return pontosTotais;
		}

	},
	COMIDA("comida") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 1;
			return pontosTotais;
		}

	};

	private String descricao;

	private TipoItem(String descricao) {
		this.descricao = descricao;
	}

}
