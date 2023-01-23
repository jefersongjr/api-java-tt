package apijavatt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import apijavatt.error.InvalidFieldsException;
import apijavatt.model.entitys.Pessoa;
import apijavatt.service.PessoaService;
import apijavatt.controller.PessoaController;


@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("Testes para Pessoa Repository")
class PessoaControllerTest {
	@Autowired
	private PessoaController controller;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	@DisplayName("Testa a resposta ao não inserir o nome")
	void CreateShouldPersitData() {
		LocalDate data = LocalDate.of(1991, 02, 11);

		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);

		Assertions.assertThat(pessoa.getId()).isNotNull();
		Assertions.assertThat(pessoa.getNome()).isEqualTo("Gabriel Barbosa");
		Assertions.assertThat(pessoa.getdataDeNascimento()).isEqualTo(data);
	}
}
