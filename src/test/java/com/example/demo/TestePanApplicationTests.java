package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Cliente;
import com.example.model.Endereco;
import com.example.model.Estado;
import com.example.model.Municipio;
import com.example.repository.ClienteRepository;

import antlr.collections.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestePanApplicationTests {

	@MockBean
	private ClienteRepository clienteRepo;

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;


	@Test
	void buscaClientePorCpf() {

		// MOCK
		Cliente cliente = new Cliente(1L, "Fidel", "04313064192", "156116", "Leila Maria");
		BDDMockito.when(clienteRepo.findOneByCpf("04313064192")).thenReturn(cliente);

		String url = "http://localhost:" + port + "/cadastros/cliente";

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("cpf", "04313064192");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<Cliente> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				Cliente.class);

		assertThat(response.getBody()).isNotNull();

	}
	
	@Test
	void buscaEnderecoByCEP() {
		
		String url = "http://localhost:" + port + "/cadastros/endereco";
		//
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("cep", "74115050");
	

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<Endereco> response1 = restTemplate.getRestTemplate().exchange(builder.toUriString(), HttpMethod.GET, entity, Endereco.class);
		
		assertThat(response1.getBody()).isNotNull();
	}
	
	@Test
	void buscaEstados() {
		
		ResponseEntity<Estado[]> response = restTemplate.getForEntity("/cadastros/estados", Estado[].class);
		
		assertThat(response.getBody()).isNotNull();
	}
	
	@Test
	void buscaMunicipios() {
		
		String url = "http://localhost:" + port + "/cadastros/municipios";
		//
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("id", "52");
	

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<Municipio[]> response1 = restTemplate.getRestTemplate().exchange(builder.toUriString(), HttpMethod.GET, entity, Municipio[].class);
		
		assertThat(response1.getBody()).isNotNull();
	}
	
	@Test
	void atualizaEndereco() {
		Cliente cliente = new Cliente(1L, "Fidel", "04313064192", "156116", "Leila Maria");
		BDDMockito.when(clienteRepo.findOneByCpf("04313064192")).thenReturn(cliente);
		
		BDDMockito.when(clienteRepo.save(cliente)).thenReturn(cliente);
		
		String cpf = "04313064192";
		
		String url = "http://localhost:" + port + "/cadastros/atualiza-cliente/"+cpf;

	    restTemplate.getRestTemplate().getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	    HttpHeaders headers = new HttpHeaders();
	    
	    Endereco end = new Endereco();
	    end.setBairro("Vila Mariana");
	    end.setCep("04119000");
	    end.setLogradouro("Rua Afonso Celso");
	    end.setUf("SP");
	    
	    HttpEntity<Endereco> requestEntity = new HttpEntity<Endereco>(end, headers);
	    HttpEntity<Cliente> response = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,Cliente.class);
	    
	    assertThat(response.getBody().getEndereco()).isNotNull();
	    
	}

}
