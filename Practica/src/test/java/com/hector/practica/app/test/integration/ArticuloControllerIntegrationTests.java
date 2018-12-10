package com.hector.practica.app.test.integration;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
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

import com.hector.practica.app.model.Articulo;
import com.hector.practica.app.repository.ArticuloRepository;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ArticuloControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	 
	@Autowired
	private ArticuloRepository articuloRepository;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

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

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(clientId,clientSecret))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
	
    //recibe el detalle de un artículo
	@Test
	public void testArticuloRepository() throws Exception {
		
		Optional<Articulo> articulo = articuloRepository.findById(1L);
		String accessToken =obtainAccessToken("64280842M","PRUEBA");
		this.mockMvc.perform(get("/api/articulo/1").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect( jsonPath("$.idArticulo", is(articulo.get().getIdArticulo().intValue())));

	}

	//recibe todos los artículos
	@Test
	public void testArticulosRepository() throws Exception {
		List<Articulo> articulos = articuloRepository.findAll();
		String accessToken =obtainAccessToken("64280842M","PRUEBA");
		this.mockMvc.perform(get("/api/articulo").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.length()", is(articulos.size())));

	}
	

}
