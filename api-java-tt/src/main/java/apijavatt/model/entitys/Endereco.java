package apijavatt.model.entitys;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String logradouro;
	private String CEP;
	private int numero;
	private String cidade;
	private int pessoaId;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "endereco")
	private Pessoa owner;

	public Endereco() {

	}

	public Endereco(int id, String logradouro, String CEP, int numero, String cidade, int pessoaId) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.CEP = CEP;
		this.numero = numero;
		this.cidade = cidade;
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
