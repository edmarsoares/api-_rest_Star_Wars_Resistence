package com.edmar.apiStarWarsResistence.rebelde;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;
import com.edmar.apiStarWarsResistence.inventario.Inventario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@SequenceGenerator(name = "seq_rebelde", sequenceName = "seq_rebelde", allocationSize = 1)
public class Rebelde implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rebelde")
	private Long id;

	@Column
	private String nome;

	@Column
	private String idade;

	@Column
	private String genero;

	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "id_galaxia")
	private Galaxia galaxia;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_inventario")
	private Inventario inventario;

	@Column
	private boolean traidor;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "acusador_traidor", joinColumns = {
			@JoinColumn(name = "id_rebelde_acusador") }, inverseJoinColumns = {
					@JoinColumn(name = "id_rebelde_traidor") })
	private Set<Rebelde> rebeldesTraidores;
	
	/**
	 * adicionado um rebelde traidor na lista de traidores
	 * @param rebelde
	 * @return retorna uma lista de rebeldes traidores
	 */
	public void adicionarTraidores(final Rebelde rebelde){
		if (this.rebeldesTraidores == null) {
			this.rebeldesTraidores = new HashSet<Rebelde>() ;
		}
		
		this.rebeldesTraidores.add(rebelde);
		 
	}

}
