package com.edmar.apiStarWarsResistence.inventario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.edmar.apiStarWarsResistence.inventario.item.Item;
import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.rebelde.exceptions.RebeldeException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@SequenceGenerator(name = "seq_inventario", sequenceName = "seq_inventario", allocationSize = 1)
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_inventario")
	private Long id;

	@Column
	private Integer totalPontos;

	@OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_inventario")
	private List<Item> itens;

	public void gerarTotalPontos() {

		if (this.itens == null) {
			return;
		}

		Integer pontos = this.itens.stream()
				.map(item -> this.totalPontos = item.getTipoItem().gerarPontos(item))
				.reduce((total, soma) -> total + soma).get();
		
		this.totalPontos = pontos;
	}

	/**
	 * Só é permitido alterar os itens caso o rebelde não seja um traidor
	 * 
	 * @param item
	 * @param rebelde
	 */
	public void adicionarItens(Item item, Rebelde rebelde) {
		if (rebelde.isTraidor()) {
			throw new RebeldeException("Você é um rebelde traidor, portanto não pode adicionar itens na lista");
		}


		if (this.itens == null) {
			this.itens = new ArrayList<>();
		}

		this.itens.add(item);
	}
	
	public void adicionarItens(List<Item> item, Rebelde rebelde) {
		if (rebelde.isTraidor()) {
			throw new RebeldeException("Você é um rebelde traidor, portanto não pode adicionar itens na lista");
		}


		if (this.itens == null) {
			this.itens = new ArrayList<>();
		}

		this.itens.addAll(item);
	}
	
	
	public void removerItens(Item item, Rebelde rebelde) {
		if (rebelde.isTraidor()) {
			throw new RebeldeException("Você é um rebelde traidor, portanto não pode adicionar itens na lista");
		}


		if (this.itens == null) {
			this.itens = new ArrayList<>();
		}

		this.itens.remove(item);
	}
	//sobrecarga de método, ganho em polimorfismo
	public void removerItens(List<Item> item, Rebelde rebelde) {
		if (rebelde.isTraidor()) {
			throw new RebeldeException("Você é um rebelde traidor, portanto não pode adicionar itens na lista");
		}


		if (this.itens == null) {
			this.itens = new ArrayList<>();
		}

		this.itens.removeAll(item);
	}

	

}
