package apijavatt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import apijavatt.error.InvalidFieldsException;
import apijavatt.error.ResourceNotFoundException;
import apijavatt.model.entitys.Pessoa;
import apijavatt.model.repositorys.PessoaRepository;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@DisplayName("Testes para Pessoa Service")
class PessoaServiceTest {

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
		LocalDate data = LocalDate.of(1991, 02, 11);

		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
		when(pessoaRepository.save(ArgumentMatchers.any(Pessoa.class))).thenReturn(pessoa);

		Pessoa created = pessoaService.salvar(pessoa);

		assertThat(created.getNome()).isSameAs(pessoa.getNome());

		verify(pessoaRepository).save(pessoa);
	}

	@Test
	@DisplayName("Testa se a Lista de pessoas é monstrada corretamente")
	void ListShouldAllPersitedData() {
		LocalDate data = LocalDate.of(1991, 02, 11);
		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);

		when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));
		Iterable<Pessoa> lista = pessoaService.listarTodasPessoas();
		assertNotNull(lista);
		assertEquals(1, IterableUtils.size(lista));
	}

	@Test
	@DisplayName("Testa a busca de uma pessoa por id")
	void whenFindByIdThenReturnAnUserInstance() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);

		when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));
		Optional<Pessoa> resposta = pessoaService.obterPorId(pessoa.getId());
		assertNotNull(resposta);
		assertThat(resposta).isNotEmpty();
	}

	@Test
	@DisplayName("Testa a busca de uma pessoa por Nome")
	void whenFindByNameThenReturnAnUserInstance() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João Carlos");

		when(pessoaRepository.findByNomeContainingIgnoreCase(pessoa.getNome())).thenReturn(List.of(pessoa));
		Iterable<Pessoa> resposta = pessoaService.obterPorNome("João Carlos");
		assertNotNull(resposta);
		assertThat(resposta).isNotEmpty();
	}

	@Test
	@DisplayName("Testa a atualiazação de uma pessoa pelo id")
	void whenUpdateAPeopleAndReturnSucess() {
		LocalDate data = LocalDate.of(1991, 02, 11);
		Pessoa pessoa = new Pessoa("João das Neves", data, null);

		when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));
		Pessoa novaPessoa = new Pessoa("João das Glorias", data, null);

		pessoaService.alterar(pessoa.getId(), novaPessoa);
		verify(pessoaRepository).save(novaPessoa);
		verify(pessoaRepository).findById(pessoa.getId());
	}

	@Test
	@DisplayName("Testa quando o campo nome é null")
	void whenTheNameIsNullException() {
		LocalDate data = LocalDate.of(1991, 02, 11);

		Pessoa pessoa = new Pessoa(null, data, null);
		try {
			pessoaService.salvar(pessoa);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo Nome é obrigatório", ex.getMessage());
		}
	}

	@Test
	@DisplayName("Testa quando o campo nome é é inválido")
	void whenTheNameIsInvalidException() {
		LocalDate data = LocalDate.of(1991, 02, 11);

		Pessoa pessoa = new Pessoa("JP", data, null);
		try {
			pessoaService.salvar(pessoa);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo Nome tem que ter pelo menos 3 caracteres.", ex.getMessage());
		}
	}

	@Test
	@DisplayName("Testa quando o data é invalido")
	void whenTheDateIsNullException() {
		Pessoa pessoa = new Pessoa("João Paulo", null, null);
		try {
			pessoaService.salvar(pessoa);
		} catch (Exception ex) {
			assertEquals(InvalidFieldsException.class, ex.getClass());
			assertEquals("O campo Data de Nascimento é obrigatório", ex.getMessage());
		}
	}
	
	@Test
	@DisplayName("Testa quando o data é invalido")
	void whenTheIdIsNullException() {
		try {
			pessoaService.obterPorId(1);
		} catch (Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
			assertEquals("Id não cadastrado", ex.getMessage());
		}
	}
}
