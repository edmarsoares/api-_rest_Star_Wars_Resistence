package com.edmar.apiStarWarsResistence.relatorio;

import com.edmar.apiStarWarsResistence.inventario.item.TipoItem;

import lombok.Data;
/**
 * Classe que encapsula os dados para gerar uma media de inventario
 * @author edmar
 *
 */
@Data
public class DadosMediaInventario {
	
	private Integer totalItem;
	private Integer totalRebeldes;
	private TipoItem tipoItem;
}
