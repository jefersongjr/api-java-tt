package apijavatt.model.repositorys;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;

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
	@DisplayName("Testes para Pessoa Repository")
	void CreateShouldPersitData() {
		LocalDate data = LocalDate.of(1991,02,11);

		Pessoa  pessoa = new Pessoa("Gabriel Barbosa", data, null);
		pessoaRepository.save(pessoa);
		
		Assertions.assertThat(pessoa.getId()).isNotNull();
		Assertions.assertThat(pessoa.getNome()).isEqualTo("Gabriel Barbosa");
		Assertions.assertThat(pessoa.getdataDeNascimento()).isEqualTo(data);
	}


}
