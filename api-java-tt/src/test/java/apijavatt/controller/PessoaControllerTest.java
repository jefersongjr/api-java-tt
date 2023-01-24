package apijavatt.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import apijavatt.model.entitys.Pessoa;
import apijavatt.service.PessoaService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@DisplayName("Testes da camada Pessoa controller")
@ActiveProfiles("test")
@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {
	@Autowired
	private PessoaController pessoaController;

	@MockBean
	private PessoaService pessoaService;

	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(pessoaController);
	}

	@Test
	@DisplayName("Testa se uma pessoa é criada corretamente")
	void CreateShouldPersitData() throws Exception {
		LocalDate data = LocalDate.of(1991, 02, 11);
		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
		when(pessoaController.novaPessoa(ArgumentMatchers.any(Pessoa.class))).thenReturn(pessoa);

		RestAssuredMockMvc.given().accept(ContentType.JSON).post("/pessoas");
		int code = RestAssuredMockMvc.given().accept(ContentType.JSON).when().post("/pessoas").andReturn().statusCode();

		assertEquals(201, code);
	}

	@Test
	@DisplayName("Testa se a Lista de pessoas é monstrada corretamente")
	void ListShouldAllPersitedData() {
		LocalDate data = LocalDate.of(1991, 02, 11);
		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);

		when(pessoaController.obterPessoas()).thenReturn(List.of(pessoa));

		RestAssuredMockMvc.given().accept(ContentType.JSON).post("/pessoas");

		Iterable<Pessoa> lista = pessoaService.listarTodasPessoas();

		assertNotNull(lista);

		int code = RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/pessoas").andReturn().statusCode();
		assertEquals(200, code);
	}

	@Test
	@DisplayName("Testa a busca de uma pessoa por id")
	void whenFindByIdThenReturnAnUserInstance() {
		LocalDate data = LocalDate.of(1991, 02, 11);
		Pessoa pessoa = new Pessoa("Gabriel Barbosa", data, null);
		pessoa.setId(1);

		when(pessoaController.alterarPessoa(pessoa.getId(), pessoa)).thenReturn(pessoa);
		RestAssuredMockMvc.given().accept(ContentType.JSON).post("/pessoas");

		Optional<Pessoa> lista = pessoaService.obterPorId(pessoa.getId());

		assertNotNull(lista);

		int code = RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/pessoas/1").andReturn()
				.statusCode();
		assertEquals(200, code);

	}

	@Test
	@DisplayName("Testa a busca de uma pessoa por Nome")
	void whenFindByNameThenReturnAnUserInstance() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João Carlos");

		when(pessoaController.obterPessoasPorNome(pessoa.getNome())).thenReturn(List.of(pessoa));
		RestAssuredMockMvc.given().accept(ContentType.JSON).post("/pessoas/Jo");

		Iterable<Pessoa> resposta = pessoaService.obterPorNome("João Carlos");
		assertNotNull(resposta);
		int code = RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/pessoas/1").andReturn()
				.statusCode();
		assertEquals(200, code);
	}
}