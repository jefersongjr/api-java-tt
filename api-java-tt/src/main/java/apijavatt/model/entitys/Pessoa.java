package apijavatt.model.entitys;


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
	private String dataDeNascimento;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	public Pessoa() {
		
	}

	public Pessoa(String nome, 
			String dataDeNascimento, Endereco endereco) {
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

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

}
