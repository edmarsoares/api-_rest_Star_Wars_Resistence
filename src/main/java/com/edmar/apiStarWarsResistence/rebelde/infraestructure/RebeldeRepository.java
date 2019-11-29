package com.edmar.apiStarWarsResistence.rebelde.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmar.apiStarWarsResistence.rebelde.Rebelde;

@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {

}
