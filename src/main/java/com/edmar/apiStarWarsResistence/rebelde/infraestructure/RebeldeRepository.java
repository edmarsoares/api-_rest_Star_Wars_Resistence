package com.edmar.apiStarWarsResistence.rebelde.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edmar.apiStarWarsResistence.rebelde.Rebelde;

@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, Long>, RebeldeRepositoryCustom {
	
	@Query("SELECT (COUNT(*)) FROM Rebelde r WHERE r.traidor = true")
	Long countTotalTraidores();
	
	@Query("SELECT (COUNT(*)) FROM Rebelde r WHERE r.traidor = false")
	Long countTotalRebeldesFieis();
	
}
