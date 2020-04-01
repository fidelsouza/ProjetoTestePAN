package com.example.model;

public class Estado implements Comparable{

	private int id;
	private String sigla;
	private String nome;
	Regiao regiao;

	// Getter Methods

	public int getId() {
		return id;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	// Setter Methods

	public void setId(int id) {
		this.id = id;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRegiao(Regiao regiaoObject) {
		this.regiao = regiaoObject;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
		return this.nome.compareTo(((Estado)o).getNome());
	}
	

}
