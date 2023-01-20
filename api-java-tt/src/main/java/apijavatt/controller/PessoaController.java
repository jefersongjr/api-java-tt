package apijavatt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.PUT})
	public @ResponseBody Pessoa novaPessoa(@Valid Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return pessoa;
	}
	
	@GetMapping
	public Iterable<Pessoa> obterPessoas() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping(path="/nome/{parteNome}")
	public Iterable<Pessoa> obterPessoasPorNome(@PathVariable String parteNome) {
		return pessoaRepository.findByNomeContainingIgnoreCase(parteNome);
	}
	
	@GetMapping(path="/{id}")
	public Optional<Pessoa> obterPessoaPorId(@PathVariable int id){
		return pessoaRepository.findById(id);
	}
	
}
