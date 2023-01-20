package apijavatt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public @ResponseBody Endereco novoEndereco(Endereco endereco) {
		enderecoRepository.save(endereco);
		return endereco;
	}
	
	@GetMapping(path="/pessoa/{pessoaId}")
	public Iterable<Endereco> obterEnderecoPorId(@PathVariable int pessoaId){
		return enderecoRepository.findByPessoaId(pessoaId);
	}
	
}
