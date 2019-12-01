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
import com.edmar.apiStarWarsResistence.rebelde.event.RebeldeTraidorEvent;
import com.edmar.apiStarWarsResistence.rebelde.service.RebeldeService;

@RestController
@RequestMapping("api/rebelde")
public class RebeldeController {

	@Autowired
	private RebeldeService rebeldeService;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping()
	public ResponseEntity<List<Rebelde>> ListAll() {
		final List<Rebelde> rebeldes = this.rebeldeService.ListAll();

		return ResponseEntity.ok(rebeldes);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(final Long id) {
		this.rebeldeService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findByID(@PathVariable final Long id) {
		final Optional<Rebelde> rebelde = this.rebeldeService.findBYId(id);

		return ResponseEntity.ok(rebelde);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Rebelde rebelde) {
		this.rebeldeService.save(rebelde);

		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody final Rebelde rebelde) {
		this.rebeldeService.save(rebelde);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/{idRebelde}")
	public ResponseEntity<?> reportarUltimaLocalizacao(@PathVariable long idRebelde,
			@RequestBody final Galaxia novaLocalizacao) {
		this.rebeldeService.reportarUltimaLocalizacao(idRebelde, novaLocalizacao);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/acusador/{idAcusador}/traidor/{idTraidor}")
	public void marcarTraidor(@PathVariable final long idAcusador, @PathVariable final long idTraidor) {
		Optional<Rebelde> rebeldeTraidor = this.rebeldeService.marcarTraidor(idAcusador, idTraidor);
		
		if (rebeldeTraidor.isPresent()) {
			this.publisher.publishEvent(new RebeldeTraidorEvent(rebeldeTraidor.get()));
		}
	}
	
	@PostMapping("/negociador/{idNegociador}/negociavel/{idNegociavel}")
	public void Negociar(@PathVariable final long idNegociador, @PathVariable final long idNegociavel, @RequestBody Item item) {
		this.rebeldeService.negociarItensInventario(idNegociador, idNegociavel, item);
	}
}
