package apijavatt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return pessoa;
	}
}
