package apijavatt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import apijavatt.model.entitys.Endereco;
import apijavatt.model.repositorys.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@PostMapping
	public @ResponseBody Endereco salvar(Endereco endereco) {
		enderecoRepository.save(endereco);
		return endereco;
	}
}
