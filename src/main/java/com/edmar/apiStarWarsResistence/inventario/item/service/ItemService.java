package com.edmar.apiStarWarsResistence.inventario.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.apiStarWarsResistence.inventario.item.infraestructure.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional(readOnly = true)
	public Long getSomaPontosPerdidosPorTraidores() {
		return this.itemRepository.getSomaPontosPerdidosPorTraidores();
		
	}


}
