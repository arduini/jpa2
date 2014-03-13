package br.com.caelum.financas.modelo;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Gerente {

	@EmbeddedId
	private GerenteId gerenteId = new GerenteId();
	
	private String nome;
	private String telefone;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public GerenteId getGerenteId() {
		return gerenteId;
	}
	public void setGerenteId(GerenteId gerenteId) {
		this.gerenteId = gerenteId;
	}
}
