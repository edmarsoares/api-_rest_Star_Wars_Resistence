package com.edmar.apiStarWarsResistence.inventario.item.dto;

import com.edmar.apiStarWarsResistence.inventario.item.Item;
import com.edmar.apiStarWarsResistence.inventario.item.TipoItem;

import lombok.Data;

@Data
public class ItemCreateDto {
	
	private Long id;
	
	private Integer quantidade;
	
	private TipoItem tipoItem;
	
	public Item convertFromDto() {
		Item item = new Item();
		
		item.setId(this.id);
		item.setQuantidade(this.quantidade);
		item.setTipoItem(this.tipoItem);
		item.setPontos(this.tipoItem.getPontosIniciais());
		
		return item;
	}
}
