package com.edmar.apiStarWarsResistence.rebelde.event;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.edmar.apiStarWarsResistence.rebelde.Rebelde;
import com.edmar.apiStarWarsResistence.rebelde.service.RebeldeService;

/**
 * Listener que esculta eventos quando um rebelde é marcado como traidor por outro rebelde
 * Caso o pelo mesnos 3 rebeldes apontem um como traidor o atributo booleano {@link traidor} 
 * é setado para true indicando que aquele cara é um traidor
 * @author edmar
 *
 */
@Component
public class RebeldeTraidorListener implements ApplicationListener<RebeldeTraidorEvent>{
	
	@Autowired
	private RebeldeService rebeldeService;
	
	@Override
	public void onApplicationEvent(RebeldeTraidorEvent event) {
		
		Rebelde rebeldeTraidor = event.getRebeldeTraidor();
		
		if (rebeldeTraidor == null) {
			return ;
		}
		
		final Long countAcusadores = this.rebeldeService.countAcusadores(rebeldeTraidor.getId());
		
		if (countAcusadores >= 3) {
			rebeldeTraidor.setTraidor(true);
			
			this.rebeldeService.save(rebeldeTraidor);
		}
	}

}
