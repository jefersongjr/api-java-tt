package apijavatt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import apijavatt.error.InvalidFieldsException;
import apijavatt.error.ResourceNotFoundException;
import apijavatt.model.entitys.Endereco;
import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.EnderecoRepository;
import apijavatt.model.repositorys.PessoaRepository;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@DisplayName("Testes para Pessoa Service")
class EnderecoServiceTest {

	@Mock
	private EnderecoRepository enderecoRepository;

	@InjectMocks
	private EnderecoService enderecoService;

	@Mock
	private PessoaRepository pessoaRepository;

	@InjectMocks
	private PessoaService pessoaService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Testa se uma pessoa é criada corretamente")
	void CreateShouldPersitData() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);

		when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));

		when(enderecoRepository.save(ArgumentMatchers.any(Endereco.class))).thenReturn(endereco);

		Endereco created = enderecoService.salvar(endereco);

		assertThat(created.getLogradouro()).isSameAs(endereco.getLogradouro());

		verify(enderecoRepository).save(endereco);
	}

	@Test
	@DisplayName("Testa se a pesquisa por pessoaId é listada corretamente")
	void FindByIdContainigShould() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);

		when(enderecoService.obterPorId(endereco.getPessoaId())).thenReturn(List.of(endereco));

		Iterable<Endereco> enderecoPessoaLista = enderecoRepository.findByPessoaId(1);
		int tamanhoLista = IterableUtils.size(enderecoPessoaLista);

		assertNotNull(enderecoPessoaLista);

		Assertions.assertThat(tamanhoLista).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo nome é é inválido")
	void whenTheStreetIsInvalidException() {
		Endereco endereco = new Endereco(1, "Ru", 1, "Rio de Janeiro", "28354-015", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo Logradouro deve conter pelo menos 4 caracteres.", ex.getMessage());
		}
	}
	

	@Test
	@DisplayName("Testa se uma execeção é lançada quando  o campo nome é é nulo")
	void whenTheStreetIsNullException() {
		Endereco endereco = new Endereco(1, null, 1, "Rio de Janeiro", "28354-015", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo Logradouro é obrigatório.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo número é é inválido")
	void whenTheNumberIsInvalidException() {
		Endereco endereco = new Endereco(1, "Rua A", -1, "Rio de Janeiro", "28354-015", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo número não pode ser um número negativo", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo cidade é nulo")
	void whenTheCityIsNulldException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, null, "28354-015", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo Cidade é obrigatório.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo cidade é inválido")
	void whenTheCityIsInvaliddException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio", "28354-015", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo Cidade deve conter pelo menos 5 caracteres.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo pessoaId é inválido")
	void whenThePessoaIdIsInvaliddException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 0);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo pessoaId é obrigatório.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo pessoaId é inválido")
	void whenThePessoaIdIsNegativedException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", -1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo pessoaId deve ser maior que 0.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo pessoaId é não consta no banco de dados")
	void whenThePessoaIdIsNullException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
			assertEquals("O campo pessoaId deve ser o Id de uma pessoa cadastrada.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo CEP é inválido")
	void whenTheCEPIsInvalidException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo CEP é obrigatório.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo CEP é nulo")
	void whenTheCEPIsNullException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", null, 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo CEP é obrigatório.", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa se uma execeção é lançada quando o campo CEP é nulo")
	void whenTheCEPIsInvalidFormatException() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", " ", 1);
		try {
			enderecoService.salvar(endereco);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("Digite um CEP no formato XXXXX-XXX", ex.getMessage());
		}
	}
	

}
