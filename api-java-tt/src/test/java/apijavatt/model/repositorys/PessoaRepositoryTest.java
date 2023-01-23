package apijavatt.model.repositorys;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import apijavatt.model.entitys.Pessoa;

@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("Testes para Pessoa Repository")
class PessoaRepositoryTest {
	@Autowired
	private PessoaRepository pessoaRepository;

	@Test
	@DisplayName("Testa se uma pessoa é cadastrada corretamente")
	void CreateShouldPersitData() {
		LocalDate data = LocalDate.of(1991, 02, 11);

		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
		pessoaRepository.save(pessoa);

		Assertions.assertThat(pessoa.getId()).isNotNull();
		Assertions.assertThat(pessoa.getNome()).isEqualTo("Gabriel Barbosa");
		Assertions.assertThat(pessoa.getdataDeNascimento()).isEqualTo(data);
	}

	@Test
	@DisplayName("Testa se uma pessoa é atualizada corretamente")
	void CreateShouldChangeAndPersitData() {
		LocalDate data = LocalDate.of(1991, 02, 11);
		LocalDate data2 = LocalDate.of(1993, 02, 10);

		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
		pessoaRepository.save(pessoa);
		pessoa.setNome("Gabriel Henrique");
		pessoa.setdataDeNascimento(data2);

		pessoa = pessoaRepository.save(pessoa);

		Assertions.assertThat(pessoa.getNome()).isEqualTo("Gabriel Henrique");
		Assertions.assertThat(pessoa.getdataDeNascimento()).isEqualTo(data2);
	}

	@Test
	@DisplayName("Testa se as pessoas são listadas corretamente")
	void FindAllContainigShould() {
		LocalDate data = LocalDate.of(1991, 02, 11);
		LocalDate data2 = LocalDate.of(1993, 02, 10);
		LocalDate data3 = LocalDate.of(1990, 12, 10);

		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
		Pessoa pessoa2 = new Pessoa("Bruno Henrique", data2, null);
		Pessoa pessoa3 = new Pessoa("Everto Ribeiro", data3, null);

		pessoaRepository.save(pessoa);
		pessoaRepository.save(pessoa2);
		pessoaRepository.save(pessoa3);
		
		Iterable<Pessoa> pessoaLista = pessoaRepository.findAll();

		Assertions.assertThat(IterableUtils.size(pessoaLista)).isEqualTo(3);
	}
	
	@Test
	@DisplayName("Testa se a pesquisa por nome é listada corretamente")
	void FindByNameIgnoreCaseContainigShould() {
		LocalDate data = LocalDate.of(1991, 02, 11);
		LocalDate data2 = LocalDate.of(1993, 02, 10);
		LocalDate data3 = LocalDate.of(1990, 12, 10);

		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
		Pessoa pessoa2 = new Pessoa("Bruno Henrique", data2, null);
		Pessoa pessoa3 = new Pessoa("Gabriel Ribeiro", data3, null);

		pessoaRepository.save(pessoa);
		pessoaRepository.save(pessoa2);
		pessoaRepository.save(pessoa3);
		
		Iterable<Pessoa> pessoaLista = pessoaRepository.findByNomeContainingIgnoreCase("Gabriel");

		Assertions.assertThat(IterableUtils.size(pessoaLista)).isEqualTo(2);
	}

}
