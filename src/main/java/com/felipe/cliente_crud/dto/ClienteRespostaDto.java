package com.felipe.cliente_crud.dto;

import java.util.Date;

import com.felipe.cliente_crud.model.Cliente;

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

	private Long id;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private int idade;
	
	
	public static ClienteRespostaDto toDto(Cliente cliente) {
		return new ClienteRespostaDto(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getIdade());
	}
}
