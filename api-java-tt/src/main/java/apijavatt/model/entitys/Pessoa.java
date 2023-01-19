package apijavatt.model.entitys;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Pessoa  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

	private String nome;
	private Date dataDeNascimento;

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

	public Date getdataDeNascimento() {
		return dataDeNascimento;
	}

	public void setdataDeNascimento(Date DataDeNascimento) {
		this.dataDeNascimento = DataDeNascimento;
	}

}
