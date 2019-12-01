package com.edmar.apiStarWarsResistence.inventario.item;

public enum TipoItem implements PontosPorItem {

	ARMA("arma") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 4;
			return pontosTotais;
		}

		@Override
		public int getPontosIniciais() {
			// TODO Auto-generated method stub
			return 4;
		}

	},
	MUNICAO("munição") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 3;
			return pontosTotais;
		}

		@Override
		public int getPontosIniciais() {
			// TODO Auto-generated method stub
			return 3;
		}

	},
	AGUA("água") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 2;
			return pontosTotais;
		}

		@Override
		public int getPontosIniciais() {
			// TODO Auto-generated method stub
			return 2;
		}

	},
	COMIDA("comida") {

		@Override
		public int gerarPontos(Item item) {
			int qtd = item.getQuantidade();

			int pontosTotais = qtd * 1;
			return pontosTotais;
		}

		@Override
		public int getPontosIniciais() {
			// TODO Auto-generated method stub
			return 1;
		}

	};

	private String descricao;

	private TipoItem(String descricao) {
		this.descricao = descricao;
	}

}
