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
import com.edmar.apiStarWarsResistence.rebelde.Rebelde;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * Usado caso seja necessário a api fornecer recursos de uma galaxia 
 * @author edmar
 *
 */
@Api("galaxiaController")
@RestController
@RequestMapping("api/galaxia")
public class GalaxiaController {
	
	@Autowired
	private GalaxiaService galaxiaService;
	
	@ApiOperation(
			value="Listar todos", 
			response=Galaxia.class, 
			notes="lista todas as galáxias da base de dados")
	@GetMapping()
	public ResponseEntity<List<Galaxia>> ListAll(){
		final List<Galaxia> galaxias = this.galaxiaService.ListAll();
		
		return ResponseEntity.ok(galaxias);
	}
	
	@ApiOperation(
			value="remover por id", 
			response=Galaxia.class,
			notes="remover uma galaxia pelo identificador")
	@DeleteMapping
	public ResponseEntity<?> delete(final Long id){
		this.galaxiaService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(
			value="buscar por id", 
			response=Galaxia.class, 
			notes="busca uma galaxia na base de dados pelo seu identificador")
	@GetMapping("/{id}")
	public ResponseEntity<?> findByID(@PathVariable final Long id){
		final Optional<Galaxia> galaxia = this.galaxiaService.findBYId(id);
		
		return ResponseEntity.ok(galaxia);
	}
	
	@ApiOperation(
			value="salvar uma galaxia", 
			response=Galaxia.class, 
			notes="salva uma galaxia na base dados")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody final Galaxia galaxia){
		this.galaxiaService.save(galaxia);
		
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(
			value="atualizar uma galaxia",
			response=Galaxia.class, 
			notes="atualiza uma galaxia na base dados")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody final Galaxia galaxia){
		this.galaxiaService.save(galaxia);
		
		return ResponseEntity.ok().build();
	}

}
