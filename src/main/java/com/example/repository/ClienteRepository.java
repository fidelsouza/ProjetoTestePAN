package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {

	Cliente findOneByCpf(String cpf);
}
