package com.edmar.apiStarWarsResistence.inventario.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@SequenceGenerator(name = "seq_item", sequenceName = "seq_item", allocationSize = 1)
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
	private Long id;

	@Column
	private Integer quantidade;
	@Column
	private Integer pontos;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoItem tipoItem;

	public void removerQuantidade() {
		
		if (this.quantidade == 0) {
			return;
		}
		this.quantidade -= 1;
	}

}
