package apijavatt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping
	public @ResponseBody Pessoa novaPessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return pessoa;
	}
	
	@GetMapping
	public Iterable<Pessoa> obterPessoas() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public Optional<Pessoa> obterPessoaPorId(@PathVariable int id){
		return pessoaRepository.findById(id);
	}
}
