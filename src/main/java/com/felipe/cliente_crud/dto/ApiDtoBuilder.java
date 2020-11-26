package com.felipe.cliente_crud.dto;

import com.felipe.cliente_crud.model.Cliente;

/**
 * 
 * @author Felipe Lemos
 */
public class ApiDtoBuilder {

	public static Cliente clienteDtoToObject(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		cliente.setId(clienteDto.getId());
		cliente.setNome(clienteDto.getNome());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setDataNascimento(clienteDto.getDataNascimento());
    	return cliente;
	}
	
	private ApiDtoBuilder() {
	    throw new IllegalStateException("Builder class");
	}

}
