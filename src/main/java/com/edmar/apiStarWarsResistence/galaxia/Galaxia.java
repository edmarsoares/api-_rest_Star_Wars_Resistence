package com.edmar.apiStarWarsResistence.galaxia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name="seq_galaxia", sequenceName="seq_galaxia", allocationSize=1)
public class Galaxia implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_galaxia")
	private Long id;
	
	@Column
	private double latitude;
	
	@Column
	private double longitude;

}
