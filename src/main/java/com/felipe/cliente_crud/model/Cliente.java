package com.felipe.cliente_crud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.felipe.cliente_crud.util.CalendarUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Felipe Lemos
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cpf;
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	public int getIdade(){
		return CalendarUtils.calculaIdade(dataNascimento);
	}

}
