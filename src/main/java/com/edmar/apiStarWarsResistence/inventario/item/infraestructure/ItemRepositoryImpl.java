package com.edmar.apiStarWarsResistence.inventario.item.infraestructure;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ItemRepositoryImpl implements ItemRepositoryCustom {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Long getSomaPontosPerdidosPorTraidores() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("SELECT sum(item.quantidade * item.pontos) as  total "
				+ " from rebelde r inner join " + 
				"	inventario i on r.id_inventario = i.id " + 
				"	inner join Item item on item.id_inventario = i.id where r.traidor = true");
		
		String queryString = builder.toString();
		
		Query result = this.manager.createNativeQuery(queryString);
		return ((BigInteger) result.getSingleResult()).longValue();
	}
}
