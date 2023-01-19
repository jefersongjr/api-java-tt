package apijavatt.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
}
