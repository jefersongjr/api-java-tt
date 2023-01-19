package apijavatt.model.entitys;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nome;
	@Temporal(TemporalType.DATE)
	private String dataDeNascimento;
	private int endereco;

	public Pessoa() {
		
	}

	public Pessoa(String nome, 
			String dataDeNascimento, int endereco) {
		super();
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getdataDeNascimento() {
		return dataDeNascimento;
	}

	public void setdataDeNascimento(String DataDeNascimento) {
		this.dataDeNascimento = DataDeNascimento;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public int getEndereco() {
		return endereco;
	}

}
