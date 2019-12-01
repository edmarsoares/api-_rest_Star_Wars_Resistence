package com.edmar.apiStarWarsResistence.galaxia.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;
import com.edmar.apiStarWarsResistence.galaxia.service.GalaxiaService;
/**
 * Usado caso seja necessário a api fornecer recursos de uma galaxia 
 * @author edmar
 *
 */
@RestController
@RequestMapping("api/galaxia")
public class GalaxiaController {
	
	@Autowired
	private GalaxiaService galaxiaService;
	
	@GetMapping()
	public ResponseEntity<List<Galaxia>> ListAll(){
		final List<Galaxia> galaxias = this.galaxiaService.ListAll();
		
		return ResponseEntity.ok(galaxias);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(final Long id){
		this.galaxiaService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findByID(@PathVariable final Long id){
		final Optional<Galaxia> galaxia = this.galaxiaService.findBYId(id);
		
		return ResponseEntity.ok(galaxia);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody final Galaxia galaxia){
		this.galaxiaService.save(galaxia);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody final Galaxia galaxia){
		this.galaxiaService.save(galaxia);
		
		return ResponseEntity.ok().build();
	}

}
