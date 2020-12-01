package com.felipe.cliente_crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.cliente_crud.dto.ClienteDto;
import com.felipe.cliente_crud.dto.ClienteRespostaDto;
import com.felipe.cliente_crud.dto.ClienteUpdateNomeDto;
import com.felipe.cliente_crud.model.Cliente;
import com.felipe.cliente_crud.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Felipe Lemos
 *
 */
@Api(value="API REST Clientes")
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
    ClienteService clienteService;
	
	@ApiOperation(value="Cadastra um Cliente")
	@PostMapping(path = "clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteRespostaDto> cadastrarCliente(@Valid @RequestBody ClienteDto clienteDto){
		Cliente clienteCad = clienteService.cadastrar(clienteDto);
        return new ResponseEntity<>(ClienteRespostaDto.toDto(clienteCad), HttpStatus.CREATED);
    }
	
	@ApiOperation(value="Retorna uma lista de Clientes")
	@GetMapping(path = "clientes")
	public ResponseEntity<Page<ClienteRespostaDto>> buscarClientes(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		
		 List<ClienteRespostaDto> clientes = new ArrayList<>();
		
		 if (nome == null)
	    	  clienteService.findAllPaginado(page, size).forEach(clientes::add);
	      else
	    	  clienteService.findByNomePaginado(nome, page, size).forEach(clientes::add);
	
	      if (clientes.isEmpty()) {
	    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	
	      return new ResponseEntity<>(new PageImpl<>(clientes), HttpStatus.OK);
    }
	
	@ApiOperation(value="Busca um cliente por CPF")
	@GetMapping(path = "clientes/{cpf}")
    public ResponseEntity<ClienteRespostaDto> getClienteByCpf(@PathVariable String cpf){
		Cliente cliente = clienteService.findByCpf(cpf);
        return new ResponseEntity<>(ClienteRespostaDto.toDto(cliente), HttpStatus.OK);
    }
	
	@ApiOperation(value="Atualiza um Cliente")
	@PutMapping(path = "clientes/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> atualizarCliente(@Valid @RequestBody ClienteDto clienteDto, @PathVariable("id") int id) {
		clienteDto.setId(id);
		clienteService.atualizarCliente(clienteDto);
		return new ResponseEntity<>("Cliente atualizado com sucesso.", HttpStatus.OK);
	}
	
	@ApiOperation(value="Atualiza o nome de um Cliente")
	@PatchMapping(path = "clientes/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> atualizarNome(@RequestBody ClienteUpdateNomeDto clienteDto, @PathVariable int id) {
		clienteService.atualizarNome(id, clienteDto);
		return new ResponseEntity<>("Cliente atualizado com sucesso.", HttpStatus.OK);
    }
	
	@ApiOperation(value="Deleta um Cliente")
	@DeleteMapping(path = "clientes/{id}")
    public ResponseEntity<String> excluir(@PathVariable int id){
		clienteService.deleteById(id);
		return new ResponseEntity<>("Cliente excluído com sucesso.", HttpStatus.OK);
    }
}