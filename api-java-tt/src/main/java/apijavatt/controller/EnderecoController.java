package apijavatt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import apijavatt.model.entitys.Endereco;
import apijavatt.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Endereco novoEndereco(Endereco endereco) {
		enderecoService.salvar(endereco);
		return endereco;
	}

	@GetMapping(path = "/pessoa/{pessoaId}")
	public Iterable<Endereco> obterEnderecoPorPessoaId(@PathVariable int pessoaId) {
		return enderecoService.obterPorId(pessoaId);
	}

}
