package com.edmar.apiStarWarsResistence.galaxia.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmar.apiStarWarsResistence.galaxia.Galaxia;

@Repository
public interface GalaxiaRepository extends JpaRepository<Galaxia, Long>{

}
