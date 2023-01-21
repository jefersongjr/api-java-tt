package apijavatt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import apijavatt.error.InvalidFieldsException;
import apijavatt.error.ResourceNotFoundException;
import apijavatt.model.entitys.Endereco;
import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.EnderecoRepository;
import apijavatt.model.repositorys.PessoaRepository;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@PostMapping
	public Endereco salvar(Endereco endereco) {
		validarRua(endereco);
		validarNumero(endereco);
		validarCidade(endereco);
		validarPessoaId(endereco);
		validandoCep(endereco);
		enderecoRepository.save(endereco);
		return endereco;
	}

	public void validarRua(Endereco endereco) {
		String rua = endereco.getLogradouro();
		if (rua == null)
			throw new InvalidFieldsException("O campo Logradouro é obrigatório.");
		if (rua.length() <= 4)
			throw new InvalidFieldsException("O campo Logradouro deve conter pelo menos 4 caracteres.");
	}

	public void validarNumero(Endereco endereco) {
		int num = endereco.getNumero();
		if (num < 0)
			throw new InvalidFieldsException("O campo número não pode ser um número negativo");
	}

	public void validarCidade(Endereco endereco) {
		String cidade = endereco.getCidade();
		if (cidade == null)
			throw new InvalidFieldsException("O campo Cidade é obrigatório.");
		if (cidade.length() <= 4)
			throw new InvalidFieldsException("O campo Cidade deve conter pelo menos 5 caracteres.");
	}

	public void validarPessoaId(Endereco endereco) {
		int pessoaId = endereco.getPessoaId();
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		System.out.println(pessoa);
		if (pessoaId == 0)
			throw new InvalidFieldsException("O campo pessoaId é obrigatório.");
		if (pessoaId < 0)
			throw new InvalidFieldsException("O campo pessoaId deve conter pelo menos 1 caracter.");
		if (pessoa.isEmpty())
			throw new ResourceNotFoundException("O campo pessoaId deve ser o Id de uma pessoa cadastrada.");
	}

	public void validandoCep(Endereco endereco) {
		String regex = "(\\d){5}-(\\d){3}";
		String CEP = endereco.getCEP();
		if(CEP == null)
			throw new InvalidFieldsException("O campo CEP é obrigatório.");
		if(CEP.length() == 0 )
			throw new InvalidFieldsException("O campo CEP é obrigatório.");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(CEP);
		boolean valid = matcher.find();
		if (valid == false)
			throw new InvalidFieldsException("Digite um CEP no formato XXXXX-XXX");
	}
}
