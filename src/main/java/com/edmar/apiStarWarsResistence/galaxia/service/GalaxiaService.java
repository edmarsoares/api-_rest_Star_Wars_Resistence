package com.edmar.apiStarWarsResistence.galaxia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;
import com.edmar.apiStarWarsResistence.galaxia.infraestructure.GalaxiaRepository;
import com.edmar.apiStarWarsResistence.genericService.GenericService;

@Service
public class GalaxiaService extends GenericService<Galaxia, Long>{
	
	private GalaxiaRepository galaxiaRepsoitory;
	
	@Autowired
	public GalaxiaService(GalaxiaRepository repository) {
		super(repository);
		this.galaxiaRepsoitory = repository;
		// TODO Auto-generated constructor stub
	}
	
}
