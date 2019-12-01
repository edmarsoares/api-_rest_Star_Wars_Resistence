package com.edmar.apiStarWarsResistence.inventario.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.edmar.apiStarWarsResistence.inventario.Inventario;
import com.edmar.apiStarWarsResistence.inventario.item.Item;
import com.edmar.apiStarWarsResistence.inventario.item.dto.ItemCreateDto;

import lombok.Data;

@Data
public class InventarioCreateDto {
	
	private Long id;

	private List<ItemCreateDto> itens;
	
	public Inventario convertFromDto() {
		Inventario inventario = new Inventario();
		
		inventario.setId(this.id);
		
		List<Item> itens = this.itens.stream()
				.map(ItemCreateDto::convertFromDto)
				.collect(Collectors.toList());
		
		inventario.setItens(itens);
		
		return inventario;
	}
}
