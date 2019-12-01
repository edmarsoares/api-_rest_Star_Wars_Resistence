package com.edmar.apiStarWarsResistence.rebelde.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;
import com.edmar.apiStarWarsResistence.galaxia.service.GalaxiaService;
import com.edmar.apiStarWarsResistence.genericService.GenericService;
import com.edmar.apiStarWarsResistence.inventario.item.Item;
import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.rebelde.exceptions.RebeldeException;
import com.edmar.apiStarWarsResistence.rebelde.infraestructure.RebeldeRepository;

@Service
public class RebeldeService extends GenericService<Rebelde, Long> {

	private RebeldeRepository rebeldeRepository;

	@Override
	@Transactional
	public void save(Rebelde rebelde) {

		rebelde.getInventario().gerarTotalPontos();
		this.rebeldeRepository.save(rebelde);
	}

	@Autowired
	public RebeldeService(RebeldeRepository repository, GalaxiaService galaxiaService) {
		super(repository);
		this.rebeldeRepository = repository;

	}

	/**
	 * Método responsável por reportar ultima localização do rebelde
	 * 
	 * @param idRebelde
	 * @param novaLocalizacao
	 */
	@Transactional
	public void reportarUltimaLocalizacao(final Long idRebelde, final Galaxia novaLocalizacao) {
		// Localizando o rebelde na base de dados
		Optional<Rebelde> rebeldeLocalizado = this.findBYId(idRebelde);

		rebeldeLocalizado.get().setGalaxia(novaLocalizacao);

		this.save(rebeldeLocalizado.get());

	}

	/**
	 * Método responsável por reportar um traidor por outro rebelde qualquer. A
	 * ideia é que sempre que um rebelde quizer marcar outro como traidor, seja
	 * passado o identificador do acusado e do acusador
	 * 
	 * @param idAcusador identificador do rebelde que acusou
	 * @param idTraidor  identificador do rebelde acusado
	 */
	@Transactional
	public Optional<Rebelde> marcarTraidor(final long idAcusador, final long idTraidor) {
		Optional<Rebelde> rebeldeAcusador = this.findBYId(idAcusador);
		Optional<Rebelde> rebeldeTraidor = this.findBYId(idTraidor);

		// Garantindo que um rebelde nao tente marcar outro como traidor duas vezes
		if (rebeldeAcusador.get().getRebeldesTraidores().contains(rebeldeTraidor.get().getId())) {
			return rebeldeTraidor;
		}
		rebeldeAcusador.get().adicionarTraidores(rebeldeTraidor.get());

		this.save(rebeldeAcusador.get());

		return rebeldeTraidor;

	}

//	public void update(Rebelde rebelde) {
//		Optional<Rebelde> rebeldeLocalizado = this.findBYId(rebelde.getId());
//	}
//	
	/**
	 * Método responsável por gerar uma negociáção que pode ocorrer ou não
	 * @param idNegociador identificador do rebelde negociador
	 * @param idRebeldeNegociavel identificador do rebelde negociavel
	 * @param itemANegociar O item ao qual quer negociar
	 */
	@Transactional
	public void negociarItensInventario(final Long idNegociador, final Long idRebeldeNegociavel, Item itemANegociar) {
		Optional<Rebelde> negociador = this.findBYId(idNegociador);
		Optional<Rebelde> negociavel = this.findBYId(idRebeldeNegociavel);
		
		if (negociador.get().isTraidor()) {
			throw new RebeldeException("Você é um traidor não pode negociar");
		}
		if (negociavel.get().isTraidor()) {
			throw new RebeldeException("Você é um traidor não pode negociar");
		}
		// Recuperando os itens do Rebelde negociador e do Rebelde que poderá aceitar a
		// negociação
		List<Item> itensNegociador = negociador.get().getInventario().getItens();
		List<Item> itensRebeldeNegociavel = negociavel.get().getInventario().getItens();

		// filtrando so os itens que interessa ao negociador
		List<Item> itensANegociar = itensNegociador.stream()
				.filter(item -> item.getTipoItem().equals(itemANegociar.getTipoItem()))
				.filter(item -> item.getQuantidade() >= itemANegociar.getQuantidade()).collect(Collectors.toList());

		Random randProbabilidade = new Random();

		// gerando probabilidade de um rebelde negociar ou não um de seus itens
		if (randProbabilidade.nextInt(3) == 2) {
			Item itensNegociavel = itensRebeldeNegociavel.stream()
					.filter(item -> !item.getTipoItem().equals(itemANegociar.getTipoItem()))
					.filter(item -> item.getQuantidade() >= itemANegociar.getQuantidade()).collect(Collectors.toList())
					.get(0);
			// adicionando os novos itens do  negociador e do negociavel
			negociador.get().getInventario().adicionarItens(itensNegociavel, negociador.get());
			negociavel.get().getInventario().adicionarItens(itensANegociar, negociavel.get());

			negociavel.get().getInventario().removerItens(itensNegociavel, negociavel.get());
			negociador.get().getInventario().removerItens(itensANegociar, negociador.get());

		} else {
			throw new RebeldeException("O rebelde não quer negociar hoje");
		}
		//Alterando a troca dos itens
		this.save(negociador.get());
		this.save(negociavel.get());
	}

	@Transactional(readOnly = true)
	public Long countAcusadores(final Long idTraidor) {
		return this.rebeldeRepository.countAcusadores(idTraidor);
	}
	
	@Transactional(readOnly = true)
	public Long countTraidores() {
		return this.rebeldeRepository.countTotalTraidores();
	}
	@Transactional(readOnly = true)
	public Long countTotalRebeldesFieis() {
		return this.rebeldeRepository.countTotalRebeldesFieis();
	}

}
