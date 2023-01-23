package apijavatt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
	@DisplayName("Testa se uma pessoa é atualizada corretamente")
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
		Optional<Pessoa> response = pessoaService.obterPorId(pessoa.getId());
        assertNotNull(response);
        assertThat(response).isNotEmpty();
        }
}
//
//	@Test
//	@DisplayName("Testa se as pessoas são listadas corretamente")
//	void FindAllContainigShould() {
//		LocalDate data = LocalDate.of(1991, 02, 11);
//		LocalDate data2 = LocalDate.of(1993, 02, 10);
//		LocalDate data3 = LocalDate.of(1990, 12, 10);
//
//		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
//		Pessoa pessoa2 = new Pessoa("Bruno Henrique", data2, null);
//		Pessoa pessoa3 = new Pessoa("Everto Ribeiro", data3, null);
//
//		pessoaRepository.save(pessoa);
//		pessoaRepository.save(pessoa2);
//		pessoaRepository.save(pessoa3);
//
//		Iterable<Pessoa> pessoaLista = pessoaRepository.findAll();
//
//		Assertions.assertThat(IterableUtils.size(pessoaLista)).isEqualTo(3);
//	}
//
//	@Test
//	@DisplayName("Testa se a pesquisa por nome é listada corretamente")
//	void FindByNameIgnoreCaseContainigShould() {
//		LocalDate data = LocalDate.of(1991, 02, 11);
//		LocalDate data2 = LocalDate.of(1993, 02, 10);
//		LocalDate data3 = LocalDate.of(1990, 12, 10);
//
//		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
//		Pessoa pessoa2 = new Pessoa("Bruno Henrique", data2, null);
//		Pessoa pessoa3 = new Pessoa("Gabriel Ribeiro", data3, null);
//
//		pessoaRepository.save(pessoa);
//		pessoaRepository.save(pessoa2);
//		pessoaRepository.save(pessoa3);
//
//		Iterable<Pessoa> pessoaLista = pessoaRepository.findByNomeContainingIgnoreCase("Gabriel");
//
//		Assertions.assertThat(IterableUtils.size(pessoaLista)).isEqualTo(2);
//	}
//
//	@Test
//	@DisplayName("Testa se a pesquisa por id é listada corretamente")
//	void FindByIdContainigShould() {
//		LocalDate data = LocalDate.of(1991, 02, 11);
//		LocalDate data2 = LocalDate.of(1993, 02, 10);
//		LocalDate data3 = LocalDate.of(1990, 12, 10);
//
//		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
//		pessoaRepository.save(pessoa);
//		Pessoa pessoa2 = new Pessoa("Bruno Henrique", data2, null);
//		pessoaRepository.save(pessoa2);
//		Pessoa pessoa3 = new Pessoa("Gabriel Ribeiro", data3, null);
//		pessoaRepository.save(pessoa3);
//
//		Optional<Pessoa> pessoaLista = pessoaRepository.findById(2);
//		int idLista = pessoaLista.get().getId();
//
//		Assertions.assertThat(idLista).isEqualTo(2);
//	}
