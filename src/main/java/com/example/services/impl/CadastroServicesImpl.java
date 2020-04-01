package com.example.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Cliente;
import com.example.model.Endereco;
import com.example.model.Estado;
import com.example.repository.ClienteRepository;
import com.example.services.CadastroService;

@Service
public class CadastroServicesImpl implements CadastroService {

	@Autowired
	ClienteRepository clienteRepo;
	
	private static final Logger log = LogManager.getLogger(CadastroServicesImpl.class.getName());

	public Cliente findOneByCpf(String cpf) {
		
		log.info("Iniciando a busca de cpf");
		
		return clienteRepo.findOneByCpf(cpf);
	}

	public List<Estado> ordenaEstados(Estado[] array) {

		List<Estado> lista = new ArrayList<>(Arrays.asList(array));

		Collections.sort(lista);

		List<Estado> result = lista.stream()
				.filter(estado -> estado.getSigla().equalsIgnoreCase("SP") || estado.getSigla().equalsIgnoreCase("RJ"))
				.collect(Collectors.toList());

		lista.removeIf(estado -> estado.getSigla().equalsIgnoreCase("SP") || estado.getSigla().equalsIgnoreCase("RJ"));
		
		Estado rio = result.get(0);
		result.set(0, result.get(1));
		result.set(1, rio);

		result.addAll(lista);
		
		log.info("Finalizando a ordenação de estados");

		return result;
	}
	
	public Cliente atualizaEndereco(String cpf, Endereco endereco) {
		
		Cliente cliente = this.clienteRepo.findOneByCpf(cpf);
		if(cliente==null) {
			throw new RuntimeException("Cliente não existe com esse cpf");
		}
		
		cliente.setEndereco(endereco);
		
		cliente = this.clienteRepo.save(cliente);
		
		return cliente;
	}
}
