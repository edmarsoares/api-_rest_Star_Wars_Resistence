package com.edmar.apiStarWarsResistence.rebelde.event;

import org.springframework.context.ApplicationEvent;

import com.edmar.apiStarWarsResistence.rebelde.Rebelde;

import lombok.Data;

@Data
public class RebeldeTraidorEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 1L;
	
	private Rebelde rebeldeTraidor;

	public RebeldeTraidorEvent(Rebelde source) {
		super(source);	
		this.rebeldeTraidor = source;
	}

	

}
