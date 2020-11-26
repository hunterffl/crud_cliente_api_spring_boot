package com.felipe.cliente_crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.cliente_crud.dto.ClienteDto;
import com.felipe.cliente_crud.dto.ClienteRespostaDto;
import com.felipe.cliente_crud.model.Cliente;
import com.felipe.cliente_crud.service.ClienteService;

/**
 * 
 * @author Felipe Lemos
 *
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
    ClienteService clienteService;
	
	@GetMapping
	public String getHello() {
		return "Hello World";
	}
	
	/*@GetMapping("/clientes")
	public ResponseEntity<List<ClienteRespostaDto>> getAll(@RequestParam(required = false) String nome) {
	    try {
	      List<ClienteRespostaDto> clientes = new ArrayList<>();
	
	      if (nome == null)
	    	  clienteService.findAll().forEach(clientes::add);
	      else
	    	  clienteService.findByNome(nome).forEach(clientes::add);
	
	      if (clientes.isEmpty()) {
	    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	
	      return new ResponseEntity<>(clientes, HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}*/
	
	@GetMapping("/clientes")
	public ResponseEntity<Page<ClienteRespostaDto>> findByNomePaginado(
            @RequestParam("nome") String nome,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		
		 List<ClienteRespostaDto> clientes = new ArrayList<>();
		
		 try {
		
		      if (nome == null)
		    	  clienteService.findAllPaginado(page, size).forEach(clientes::add);
		      else
		    	  clienteService.findByNomePaginado(nome, page, size).forEach(clientes::add);
		
		      if (clientes.isEmpty()) {
		    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		
		      return new ResponseEntity<>(new PageImpl<>(clientes), HttpStatus.OK);
		    } catch (Exception e) {
		    	return new ResponseEntity<>(new PageImpl<>(clientes), HttpStatus.INTERNAL_SERVER_ERROR);
		    }
    }
	
	@PostMapping(path = "cliente/novo", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ClienteDto clienteDto, BindingResult result){
    	
    	if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
    	
    	Cliente clienteCad = clienteService.cadastrar(clienteDto);
        return new ResponseEntity<>(ClienteRespostaDto.toDto(clienteCad), HttpStatus.CREATED);
    }

	
	/*@GetMapping(path = "api/cliente/{cpf}")
    public ResponseEntity<ClienteRespostaDto> getClienteByNome(@PathVariable String cpf){
    	Cliente cliente = clienteService.findByCpf(cpf);
        return new ResponseEntity<>(ClienteRespostaDto.toDto(cliente), HttpStatus.OK);
    }*/

}