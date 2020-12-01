package com.felipe.cliente_crud.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	private int id;

	@NotBlank(message = "Nome: Campo obrigatório não informado.")
	private String nome;

	@NotBlank(message = "CPF: Campo obrigatório não informado.")
	@CPF(message="CPF: Campo com valor inválido.")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private String cpf;
	
	@NotNull(message = "Data de Nascimento: Campo obrigatório não informado.")
	private Date dataNascimento;

}
