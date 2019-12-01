package com.edmar.apiStarWarsResistence.inventario.item.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmar.apiStarWarsResistence.inventario.item.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom{
	

}
