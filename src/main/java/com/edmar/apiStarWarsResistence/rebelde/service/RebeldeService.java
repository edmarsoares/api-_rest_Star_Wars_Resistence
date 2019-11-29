package com.edmar.apiStarWarsResistence.rebelde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.apiStarWarsResistence.genericService.GenericService;
import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.rebelde.infraestructure.RebeldeRepository;

@Service
public class RebeldeService extends GenericService<Rebelde, Long>{
	
	private RebeldeRepository rebeldeRepository;
	
	@Autowired
	public RebeldeService(RebeldeRepository repository) {
		super(repository);
		this.rebeldeRepository = repository;
	}
	
	public void marcarComoTraidor(final Long idRebeldeTraidor) {
		
	}
}
