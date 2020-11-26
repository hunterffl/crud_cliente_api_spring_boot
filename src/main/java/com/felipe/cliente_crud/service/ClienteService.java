package com.felipe.cliente_crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.felipe.cliente_crud.dto.ApiDtoBuilder;
import com.felipe.cliente_crud.dto.ClienteDto;
import com.felipe.cliente_crud.dto.ClienteRespostaDto;
import com.felipe.cliente_crud.model.Cliente;
import com.felipe.cliente_crud.repository.ClienteRepository;

/**
 * 
 * @author Felipe Lemos
 */
@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente findByCpf(String cpf) {
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		if (cliente.isPresent()) {
			return cliente.get();
		}
       return null;
    }
	
	/*
	
	public Collection<ClienteRespostaDto> findAll(){
		Collection<Cliente> clientes = clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
    	return clientes.stream()
                .map(ClienteRespostaDto::toDto)
                .collect(Collectors.toList());
    }
	
	public Collection<ClienteRespostaDto> findByNome(String nome){
    	Collection<Cliente> clientes = clienteRepository.findByNome(nome);
    	return clientes.stream()
                .map(ClienteRespostaDto::toDto)
                .collect(Collectors.toList());
    }*/
	
	public Page<ClienteRespostaDto> findByNomePaginado(String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        Page<Cliente> clientes = clienteRepository.findByNomePaginado(nome.toLowerCase(), pageRequest);
        List<ClienteRespostaDto> clientesResposta = clientes.stream().map(ClienteRespostaDto::toDto).collect(Collectors.toList());
        return new PageImpl<>(clientesResposta);
    }
	
	public Page<ClienteRespostaDto> findAllPaginado(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        Page<Cliente> clientes = clienteRepository.findAllPaginado(pageRequest);
        List<ClienteRespostaDto> clientesResposta = clientes.stream().map(ClienteRespostaDto::toDto).collect(Collectors.toList());
        return new PageImpl<>(clientesResposta);
    }

	public Cliente cadastrar(ClienteDto clienteDto){
		Cliente cliente = new Cliente();

    	if (clienteDto.getCpf() != null && !clienteDto.getCpf().trim().isEmpty()) {
    		cliente = findByCpf(clienteDto.getCpf());
    	}
    	
    	cliente = ApiDtoBuilder.clienteDtoToObject(clienteDto);
    	
        return clienteRepository.save(cliente);
    }

}
