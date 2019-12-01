package com.edmar.apiStarWarsResistence.genericService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.apiStarWarsResistence.exception.BasicException;
/**
 * Classe responsável por fornecer metódos comuns a todos que precisem realizar ações gerais 
 * @author edmar
 *
 * @param <T>
 */
public abstract class GenericService<T extends Serializable, ID> {
	
	private JpaRepository<T, ID> repository;
	
	public GenericService(JpaRepository<T, ID> repository) {
		this.repository = repository;
	}
	
	/**
	 * Caso a entidade seja passadda com id, um update será realizado na entidade em questão
	 * @param entity
	 */
	@Transactional
	public void save(T entity){
		this.repository.save(entity);
	}
	
	@Transactional()
	public void delete(ID id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<T> findBYId(ID id) {
		Optional<T> entity = this.repository.findById(id);
		entity.orElseThrow(()-> new BasicException("A entidade de identificador "+ id + " Não foi encontrada") );
		
		return entity;
	}
	
	@Transactional(readOnly=true)
	public List<T> ListAll(){
		return this.repository.findAll();
	}
}
