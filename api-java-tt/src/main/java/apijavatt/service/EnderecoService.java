package apijavatt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import apijavatt.error.InvalidFieldsException;
import apijavatt.model.entitys.Endereco;
import apijavatt.model.repositorys.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@PostMapping
	public Endereco salvar(Endereco endereco) {
		validarRua(endereco);
		enderecoRepository.save(endereco);
		return endereco;
	}
	
	public void validarRua(Endereco endereco) {
		String rua = endereco.getLogradouro();
		if(rua == null )
			throw new InvalidFieldsException("O campo Logradouro é obrigatório.");
		if(rua.length() <= 4 )
			throw new InvalidFieldsException("O campo Logradouro deve conter pelo menos 5 caracteres.");
	}
}
