package com.edmar.apiStarWarsResistence.rebelde.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import com.edmar.apiStarWarsResistence.inventario.item.Item;
import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.rebelde.dto.RebeldeCreateDTO;
import com.edmar.apiStarWarsResistence.rebelde.event.RebeldeTraidorEvent;
import com.edmar.apiStarWarsResistence.rebelde.service.RebeldeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/rebelde")
@Api(value = "rebeldeController")
public class RebeldeController {

	@Autowired
	private RebeldeService rebeldeService;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping()
	@ApiOperation(
			value="Listar todos os rebeldes", 
			response=Rebelde.class, 
			notes="essa operação lista todos os rebeldes na base de dados")
	public ResponseEntity<List<Rebelde>> ListAll() {
		final List<Rebelde> rebeldes = this.rebeldeService.ListAll();

		return ResponseEntity.ok(rebeldes);
	}
	
	@ApiOperation(
			value="deletar rebelde por id", 
			notes="remove um rebelde da base através de seu id")
	@DeleteMapping
	public ResponseEntity<?> delete(final Long id) {
		this.rebeldeService.delete(id);

		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(
			value="buscar pelo identificador", 
			response=Rebelde.class, 
			notes="buscar um rebelde na base dados através de seu identificador")
	@GetMapping("/{id}")
	public ResponseEntity<?> findByID(@PathVariable final Long id) {
		final Optional<Rebelde> rebelde = this.rebeldeService.findBYId(id);

		return ResponseEntity.ok(rebelde);
	}
	
	@ApiOperation(
			value="Salvar rebelde", 
			response=Rebelde.class, 
			notes="salva um rebelde na base dados")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody RebeldeCreateDTO rebeldeDto) {
		Rebelde rebelde = rebeldeDto.convertFromDTO();
		this.rebeldeService.save(rebelde);

		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody final Rebelde rebelde) {
		this.rebeldeService.save(rebelde);

		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(
			value="reportar última localização",
			response=Rebelde.class, 
			notes="reporta a última localização do rebelde. É necessário informar o id e a localização (Galaxia)")
	@PostMapping("/{idRebelde}")
	public ResponseEntity<?> reportarUltimaLocalizacao(@PathVariable long idRebelde,
			@RequestBody final Galaxia novaLocalizacao) {
		this.rebeldeService.reportarUltimaLocalizacao(idRebelde, novaLocalizacao);

		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(
			value="reportar traição", 
			response=Rebelde.class, 
			notes="este endpoint é responsável por informar que um rebelde virou um traidor."
					+ " è necessário informar quem o acusou (idAcusador) e quem foi acusado (idTraidor)")
	@GetMapping("/acusador/{idAcusador}/traidor/{idTraidor}")
	public void marcarTraidor(@PathVariable final long idAcusador, @PathVariable final long idTraidor) {
		Optional<Rebelde> rebeldeTraidor = this.rebeldeService.marcarTraidor(idAcusador, idTraidor);
		
		if (rebeldeTraidor.isPresent()) {
			this.publisher.publishEvent(new RebeldeTraidorEvent(rebeldeTraidor.get()));
		}
	}
	
	@ApiOperation(
			value="realizar negociação", 
			notes="endpoint responsável por realizar a negociação. É preciso informar o id do rebelde "
					+ " que quer negociar e o id do que pode aceitar ou não a negociação") 
	@PostMapping("/negociador/{idNegociador}/negociavel/{idNegociavel}")
	public void Negociar(@PathVariable final long idNegociador, @PathVariable final long idNegociavel, @RequestBody Item item) {
		this.rebeldeService.negociarItensInventario(idNegociador, idNegociavel, item);
	}
}
