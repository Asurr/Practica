package com.hector.practica.app.test.integration;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hector.practica.app.model.Articulo;
import com.hector.practica.app.model.Catalogo;
import com.hector.practica.app.model.Cliente;
import com.hector.practica.app.model.Pedido;
import com.hector.practica.app.model.PedidoArticulo;
import com.hector.practica.app.model.PedidoArticulo.pedidoArticuloPK;
import com.hector.practica.app.repository.PedidoRepository;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PedidoControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PedidoRepository pedidoRepository;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Value("${security.jwt.grant-type}")
	private String grantType;

	
	//realiza la peticion para autenticarse
	private String obtainAccessToken(String username, String password) throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grantType);
		params.add("username", username);
		params.add("password", password);

		ResultActions result = mockMvc
				.perform(post("/oauth/token").params(params).with(httpBasic(clientId, clientSecret))
						.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}

	//devuelve un pedido
	@Test
	public void testPedidoRepository() throws Exception {
		Optional<Pedido> p1 = pedidoRepository.findByIdPedidoAndCliente_Dni(1L, "71754769F");
		String accessToken = obtainAccessToken("71754769F", "PRUEBA");
		this.mockMvc.perform(get("/api/pedido/1").header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.idPedido", is(p1.get().getIdPedido().intValue())));

	}

	//devuelve todos los pedidos
	@Test
	public void testPedidosRepository() throws Exception {
		List<Pedido> pedidos = pedidoRepository.findByCliente_Dni("71754769F");
		String accessToken = obtainAccessToken("71754769F", "PRUEBA");
		this.mockMvc.perform(get("/api/pedido").header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.length()", is(pedidos.size())));

	}

	//elimina un pedido
	@Test
	public void testPedidoRemoveRepository() throws Exception {
		String accessToken = obtainAccessToken("71754769F", "PRUEBA");
		this.mockMvc.perform(delete("/api/pedido/1").header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk());

	}

	//elimina un pedido que no existe(error)
	@Test
	public void testPedidoRemove1Repository() throws Exception {
		String accessToken = obtainAccessToken("71754769F", "PRUEBA");
		this.mockMvc.perform(delete("/api/pedido/16").header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isNotFound());

	}

	//modifica un pedido
	@Test
	public void testPedidoPutRepository() throws Exception {
		Optional<Pedido> p1 = pedidoRepository.findById(3L);
		String accessToken = obtainAccessToken("71754769F", "PRUEBA");
		p1.get().getArticulos().get(0).setCantidad(44);
		this.mockMvc
				.perform(put("/api/pedido").header("Authorization", "Bearer " + accessToken)
						.contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(p1.get())))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8));
		assertTrue(pedidoRepository.findById(3L).get().getArticulos().get(0).getCantidad() == p1.get().getArticulos()
				.get(0).getCantidad());

	}

	//inserta un pedido
	@Test
	public void testPedidoPostRepository() throws Exception {
		Pedido p1 = new Pedido();
		String accessToken = obtainAccessToken("71754769F", "PRUEBA");

		p1.setCliente(new Cliente((long) 1, "ENRIQUE", "PASTOR", "GALDOS", "Calle ave del paraiso 7", "71754769F",
				"6130607950", "232323123213215545345", "$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C"));
		p1.setFechaPedido(new Date());
		p1.setIdPedido(16L);
		List<PedidoArticulo> miLista = new ArrayList<>();
		PedidoArticulo miPedidoArticulo = new PedidoArticulo(new pedidoArticuloPK(16L, 1L), 10,
				new Pedido(16L, new Date(),
						new Cliente((long) 1, "ENRIQUE", "PASTOR", "GALDOS", "Calle ave del paraiso 7", "71754769F",
								"6130607950", "232323123213215545345",
								"$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C")),
				new Articulo((long) 1, new Catalogo((long) 1, "PRIMAVERA 2017"), "CHOCOLATE", "NESTLE", new Date(), 2.5,
						5));
		miLista.add(miPedidoArticulo);
		p1.setArticulos(miLista);
		this.mockMvc
				.perform(post("/api/pedido").header("Authorization", "Bearer " + accessToken)
						.contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(p1)))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.idPedido", is(p1.getIdPedido().intValue())));

	}

	//utilidad para convertir un objeto a json
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
}
