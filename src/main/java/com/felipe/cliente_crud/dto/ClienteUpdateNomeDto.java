package com.felipe.cliente_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Felipe Lemos
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteUpdateNomeDto {

	private int id;
	private String nome;
}
