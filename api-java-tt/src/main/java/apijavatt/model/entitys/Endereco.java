package apijavatt.model.entitys;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @JsonProperty("Logradouro")
	private String logradouro;
	private String CEP;
    @JsonProperty("Número")
	private int numero = 0;
    @JsonProperty("Cidade")
	private String cidade;
    @JsonProperty("Pessoa")
	private int pessoaId;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "endereco")
	private Pessoa owner;

	public Endereco() {

	}

	public Endereco(int id, String logradouro, int numero, String cidade, String CEP, int pessoaId) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.CEP = CEP;
		this.pessoaId = pessoaId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

}
