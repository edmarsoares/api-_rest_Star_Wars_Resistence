package com.edmar.apiStarWarsResistence.rebelde;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;
import com.edmar.apiStarWarsResistence.inventario.Inventario;

@Entity
@SequenceGenerator(name="seq_rebelde", sequenceName="seq_rebelde", allocationSize=1)
public class Rebelde implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_rebelde")
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String idade;
	
	@Column
	private String genero;
	
	@ManyToOne
	@JoinColumn(name="id_galaxia")
	private Galaxia galaxia;
	
	@OneToOne
	@JoinColumn(name="id_inventario")
	private Inventario inventario;
	
}
