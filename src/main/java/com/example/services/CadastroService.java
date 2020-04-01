package com.example.services;

import java.util.List;


import com.example.model.Cliente;
import com.example.model.Endereco;
import com.example.model.Estado;

public interface CadastroService {

	Cliente findOneByCpf(String cpf);

	List<Estado> ordenaEstados(Estado[] array);

	Cliente atualizaEndereco(String cpf, Endereco endereco);
}
