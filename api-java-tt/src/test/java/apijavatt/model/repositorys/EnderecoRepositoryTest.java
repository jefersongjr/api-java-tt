package apijavatt.model.repositorys;

import java.time.LocalDate;
import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import apijavatt.model.entitys.Endereco;


@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("Testes para Pessoa Repository")
class EnderecoRepositoryTest {
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Test
	@DisplayName("Testa se uma endereço é cadastrada corretamente")
	void CreateShouldPersitData() {
		LocalDate data = LocalDate.of(1991, 02, 11);

		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);
		enderecoRepository.save(endereco);

		Assertions.assertThat(endereco.getId()).isNotNull();
		Assertions.assertThat(endereco.getLogradouro()).isEqualTo("Rua A");
		Assertions.assertThat(endereco.getNumero()).isEqualTo(1);
		Assertions.assertThat(endereco.getCidade()).isEqualTo("Rio de Janeiro");
		Assertions.assertThat(endereco.getCEP()).isEqualTo("28354-015");
		Assertions.assertThat(endereco.getPessoaId()).isEqualTo(1);

	}
	@Test
	@DisplayName("Testa se a pesquisa por pessoaId é listada corretamente")
	void FindByIdContainigShould() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);
		enderecoRepository.save(endereco);
		Endereco endereco1 = new Endereco(1, "Rua B", 1, "Rio de Janeiro", "28354-015", 1);
		enderecoRepository.save(endereco1);
		Endereco endereco2 = new Endereco(1, "Rua C", 1, "Rio de Janeiro", "28354-015", 1);
		enderecoRepository.save(endereco2);
		
		Iterable<Endereco> enderecoPessoaLista = enderecoRepository.findByPessoaId(1);
		int tamahoLista = IterableUtils.size(enderecoPessoaLista);

		Assertions.assertThat(tamahoLista).isEqualTo(3);
	}
	@Test
	@DisplayName("Testa se um endereço é atualizado corretamente")
	void CreateShouldChangeAndPersitData() {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);
		enderecoRepository.save(endereco);
		endereco.setLogradouro("Rua B");
		endereco.setNumero(2);

		endereco = enderecoRepository.save(endereco);

		Assertions.assertThat(endereco.getLogradouro()).isEqualTo("Rua B");
		Assertions.assertThat(endereco.getNumero()).isEqualTo(2);
	}
}
