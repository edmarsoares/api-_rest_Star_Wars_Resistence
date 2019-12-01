package com.edmar.apiStarWarsResistence.rebelde.infraestructure;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class RebeldeRepositoryImpl implements RebeldeRepositoryCustom{
	
	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * Conta quantos rebeldes marcaram um especifico rebelde como traidor
	 */
	public Long countAcusadores(final Long idTraidor) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("SELECT distinct  COUNT(*) FROM rebelde r  " + 
				"INNER JOIN acusador_traidor act  " + 
				"ON act.id_rebelde_acusador = r.id " + 
				"where  act.id_rebelde_traidor = "+idTraidor);
		
		String queryString = builder.toString();
		
		Query result = this.manager.createNativeQuery(queryString);
		return ((BigInteger) result.getSingleResult()).longValue();
	}
}
