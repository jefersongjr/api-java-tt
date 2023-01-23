package apijavatt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
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
		int code = RestAssuredMockMvc.given().accept(ContentType.JSON).when().post("/pessoas").andReturn()
				.statusCode();
		
		 assertEquals(201, code);
	}
}