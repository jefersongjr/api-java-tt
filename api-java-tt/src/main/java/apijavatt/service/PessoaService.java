package apijavatt.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import apijavatt.error.InvalidFieldsException;
import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		String nome = pessoa.getNome();
		LocalDate data = pessoa.getdataDeNascimento();
		validarNome(nome);
		validarData(data);
		pessoaRepository.save(pessoa);
		return pessoa;
	}
	
	public Iterable<Pessoa> listarTodasPessoas() {
		return pessoaRepository.findAll();
	}
	
	public Iterable<Pessoa> obterPorNome( String parteNome) {
		return pessoaRepository.findByNomeContainingIgnoreCase(parteNome);
	}
	
	public void validarNome(String nome) {
		if (nome == null)
			throw new InvalidFieldsException("O campo Nome é obrigatório");
		if (nome.length() <= 2)
			throw new InvalidFieldsException("O campo Nome tem que ter pelo menos 3 caracteres.");
	}
	
	public void validarData(LocalDate data) {
		if (data == null)
			throw new InvalidFieldsException("O campo Data de Nascimento é obrigatório");
	}
	
	public Optional<Pessoa> obterPorId(int id){
		return pessoaRepository.findById(id);
	}
	

}
