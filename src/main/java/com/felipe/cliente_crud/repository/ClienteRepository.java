package com.felipe.cliente_crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.felipe.cliente_crud.model.Cliente;

/**
 * 
 * @author Felipe Lemos
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("from Cliente WHERE nome LIKE CONCAT('%',:nome,'%')")
	List<Cliente> findByNome(@Param("nome") String nome);
	
	@Query("from Cliente WHERE cpf = :cpf")
	Optional<Cliente> findByCpf(String cpf);
	
	@Query("from Cliente c WHERE LOWER(c.nome) like %:nome%")
    Page<Cliente> findByNomePaginado(@Param("nome") String nome, Pageable pageable);
	
	@Query("from Cliente c")
	Page<Cliente> findAllPaginado(Pageable pageable);

	@Query("from Cliente where id = ?1")
	Optional<Cliente> findById(int id);

}

