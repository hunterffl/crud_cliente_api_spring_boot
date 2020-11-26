package com.felipe.cliente_crud.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

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
public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "Nome: Campo obrigatório não informado.")
	private String nome;

	@NotNull(message = "CPF: Campo obrigatório não informado.")
	private String cpf;
	
	@NotNull(message = "Data de Nascimento: Campo obrigatório não informado.")
	private Date dataNascimento;

}
