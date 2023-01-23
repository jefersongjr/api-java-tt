package apijavatt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import apijavatt.model.entitys.Pessoa;
import apijavatt.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Pessoa novaPessoa(Pessoa pessoa) {
		pessoaService.salvar(pessoa);
		return pessoa;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Pessoa> obterPessoas() {
		return pessoaService.listarTodasPessoas();
	}
	
	@GetMapping(path="/nome/{parteNome}")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Pessoa> obterPessoasPorNome(@PathVariable String parteNome) {
		return pessoaService.obterPorNome(parteNome);
	}
	
	@GetMapping(path="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Pessoa> obterPessoaPorId(@PathVariable int id){
		return pessoaService.obterPorId(id);
	}
	
	@PutMapping
	public @ResponseBody Pessoa alterarPessoa(int id ,Pessoa pessoa) {
		pessoaService.alterar(id, pessoa);
		return pessoa;
	}
}
