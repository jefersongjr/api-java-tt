package apijavatt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import apijavatt.model.entitys.Pessoa;
import apijavatt.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	public @ResponseBody Pessoa novaPessoa(Pessoa pessoa) {
		pessoaService.salvar(pessoa);
		return pessoa;
	}
	
	@GetMapping
	public Iterable<Pessoa> obterPessoas() {
		return pessoaService.listarTodasPessoas();
	}
	
	@GetMapping(path="/nome/{parteNome}")
	public Iterable<Pessoa> obterPessoasPorNome(@PathVariable String parteNome) {
		return pessoaService.obterPorNome(parteNome);
	}
	
	@GetMapping(path="/{id}")
	public Optional<Pessoa> obterPessoaPorId(@PathVariable int id){
		return pessoaService.obterPorId(id);
	}
	
	@PutMapping
	public @ResponseBody Pessoa alterarPessoa(int id ,Pessoa pessoa) {
		pessoaService.alterar(id, pessoa);
		return pessoa;
	}
}
