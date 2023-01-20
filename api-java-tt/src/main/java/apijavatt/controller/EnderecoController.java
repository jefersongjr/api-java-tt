package apijavatt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import apijavatt.model.entitys.Endereco;
import apijavatt.model.repositorys.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.PUT})
	public @ResponseBody Endereco novaPessoa(Endereco endereco) {
		enderecoRepository.save(endereco);
		return endereco;
	}
	
}
