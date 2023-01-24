package apijavatt.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import apijavatt.model.entitys.Endereco;
import apijavatt.service.EnderecoService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@DisplayName("Testes da camada Endereco controller")
@ActiveProfiles("test")
@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {
	@Autowired
	private EnderecoController enderecoController;

	@MockBean
	private EnderecoService enderecoService;
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(enderecoController);
	}
	
	@Test
	@DisplayName("Testa se um endereço é criado corretamente")
	void CreateShouldPersitData() throws Exception {
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);
		when(enderecoController.novoEndereco(ArgumentMatchers.any(Endereco.class))).thenReturn(endereco);

		RestAssuredMockMvc.given().accept(ContentType.JSON).post("/endereco");
		int code = RestAssuredMockMvc.given().accept(ContentType.JSON).when().post("/endereco").andReturn().statusCode();

		assertEquals(201, code);
	}
	
	@Test
	@DisplayName("Testa se a Lista de pessoas é monstrada corretamente")
	void ListShouldAllPersitedData() {
		
		Endereco endereco = new Endereco(1, "Rua A", 1, "Rio de Janeiro", "28354-015", 1);
		
		when(enderecoController.obterEnderecoPorPessoaId(1)).thenReturn(List.of(endereco));

		RestAssuredMockMvc.given().accept(ContentType.JSON).post("/pessoas");

		Iterable<Endereco> lista = enderecoService.obterPorId(1);

		assertNotNull(lista);

		int code = RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/endereco/pessoa/1").andReturn().statusCode();
		assertEquals(200, code);
	}
}
