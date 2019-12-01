package com.edmar.apiStarWarsResistence.galaxia.dto;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;

import lombok.Data;

@Data
public class GalaxiaCreateDto {
	
	private Long id;
	
	private String nome;
	
	private double latitude;
	
	private double longitude;
	
	public Galaxia convertFromDto() {
		
		Galaxia galaxia = new Galaxia();
		
		galaxia.setId(this.id);
		galaxia.setNome(this.nome);
		galaxia.setLatitude(this.latitude);
		galaxia.setLongitude(this.longitude);
		
		return galaxia;
	}
}
