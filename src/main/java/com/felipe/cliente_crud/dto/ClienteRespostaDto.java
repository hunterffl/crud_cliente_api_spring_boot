package com.felipe.cliente_crud.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipe.cliente_crud.model.Cliente;
import com.felipe.cliente_crud.util.StringUtils;

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
public class ClienteRespostaDto {

	private long id;
	private String nome;
	private String cpf;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;
	private int idade;
	
	
	public static ClienteRespostaDto toDto(Cliente cliente) {
		return new ClienteRespostaDto(
				cliente.getId(), 
				cliente.getNome(), 
				StringUtils.retornarCpfFormatado(cliente.getCpf()), 
				cliente.getDataNascimento(), 
				cliente.getIdade());
	}
}
