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
import com.felipe.cliente_crud.dto.ClienteUpdateNomeDto;
import com.felipe.cliente_crud.exception.ResourceAlreadyExistsException;
import com.felipe.cliente_crud.exception.ResourceNotFoundException;
import com.felipe.cliente_crud.model.Cliente;
import com.felipe.cliente_crud.repository.ClienteRepository;
import com.felipe.cliente_crud.util.StringUtils;

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
		if (!cliente.isPresent())
			throw new ResourceNotFoundException("Cliente não encontrado com o CPF: " + cpf + ".");
		return cliente.get();
    }
	
	public Cliente findById(int id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent())
        	throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id + ".");
        return cliente.get();
    }
	
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

	public Cliente cadastrar(ClienteDto clienteDto) {
		Cliente cliente = getClienteByCpf(StringUtils.retornarApenasNumeros(clienteDto.getCpf()));
		if(cliente != null && cliente.getId() > 0) 
            throw new ResourceAlreadyExistsException("Cliente já cadastrado com este CPF!");
		if (clienteDto.getId() > 0 && existsById(clienteDto.getId()))
            throw new ResourceAlreadyExistsException("Cliente já cadastrado com este ID!");
		return clienteRepository.save(ApiDtoBuilder.clienteDtoToObject(clienteDto));
    }

	public void deleteById(int id) {
        if (!existsById(id)) 
        	throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id + ".");
        else
        	clienteRepository.deleteById(id);
    }
	
	private boolean existsById(int id) {
        return clienteRepository.existsById(id);
    }
	
	public Cliente getClienteByCpf(String cpf) {
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		if (cliente.isPresent())
			return cliente.get();
		return null;
    }
	
	public void atualizarCliente(ClienteDto clienteDto) {
		if (!existsById(clienteDto.getId())) {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + clienteDto.getId());
        }
        clienteRepository.save(ApiDtoBuilder.clienteDtoToObject(clienteDto));
    }
	
	public void atualizarNome(int id, ClienteUpdateNomeDto cliente) {
		Optional<Cliente> clienteBanco = clienteRepository.findById(id);
		if (!clienteBanco.isPresent())
        	throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id + ".");
		Cliente clienteAtualizar = clienteBanco.get();
		if (cliente.getNome() != null)
			clienteAtualizar.setNome(cliente.getNome());
        clienteRepository.save(clienteAtualizar);
    }
}
