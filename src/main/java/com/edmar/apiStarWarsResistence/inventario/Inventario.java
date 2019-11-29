package com.edmar.apiStarWarsResistence.inventario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seq_galaxia", sequenceName="seq_galaxia", allocationSize=1)
public class Inventario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_galaxia")
	private Long id;
	
}
