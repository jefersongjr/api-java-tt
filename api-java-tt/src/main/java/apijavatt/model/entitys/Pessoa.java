package apijavatt.model.entitys;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pessoa {

	@Id
	@Column(name="pessoa_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeNascimento;	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	public Pessoa() {
		
	}

	public Pessoa(String nome, 
			LocalDate dataDeNascimento, Endereco endereco) {
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

	public LocalDate getdataDeNascimento() {
		return dataDeNascimento;
	}

	public void setdataDeNascimento(LocalDate DataDeNascimento) {
		this.dataDeNascimento = DataDeNascimento;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

}
