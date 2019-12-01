package com.edmar.apiStarWarsResistence.rebelde.dto;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;
import com.edmar.apiStarWarsResistence.galaxia.dto.GalaxiaCreateDto;
import com.edmar.apiStarWarsResistence.inventario.Inventario;
import com.edmar.apiStarWarsResistence.inventario.dto.InventarioCreateDto;
import com.edmar.apiStarWarsResistence.rebelde.Rebelde;

import lombok.Data;

@Data	
public class RebeldeCreateDTO {
	
	private String nome;
	
	private String idade;
	
	private String genero;

	// Considerando que ja exista uma galaxia cadastrada é so setar o id dela aqui
	private Long id_galaxia;

	private InventarioCreateDto inventario;


	public Rebelde convertFromDTO() {
		
		Rebelde rebelde = new Rebelde();
		
		rebelde.setNome(this.nome);
		rebelde.setIdade(this.idade);
		rebelde.setGenero(this.genero);
		
		Galaxia galaxia = new Galaxia();
		galaxia.setId(id_galaxia);
		rebelde.setGalaxia(galaxia);
		rebelde.setInventario(inventario.convertFromDto());
		
		return rebelde;
	}
}
