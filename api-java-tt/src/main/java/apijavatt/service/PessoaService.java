package apijavatt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apijavatt.error.InvalidFieldsException;
import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		String nome = pessoa.getNome();
		validarNome(nome);
		pessoaRepository.save(pessoa);
		return pessoa;
	}
	
	public void validarNome(String nome) {
		if (nome == null)
			throw new InvalidFieldsException("O campo Nome é obrigatório");
		if (nome.length() <= 2)
			throw new InvalidFieldsException("O campo Nome tem que ter pelo menos 3 caracteres.");
	}

}
