package com.hector.practica.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hector.practica.app.controller.ArticuloController;
import com.hector.practica.app.manager.ArticuloManager;
import com.hector.practica.app.model.Articulo;
import com.hector.practica.app.model.Catalogo;
import com.hector.practica.app.repository.ArticuloRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticuloController.class)
public class ArticuloControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArticuloRepository articuloRepository;

	@MockBean
	private ArticuloManager articuloManager;

	@MockBean
	private UserDetailsService userDetailsService;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	//recupera un artículo
	@Test
	public void testArticuloRepository() throws Exception {
		Articulo articulo = new Articulo((long) 2, new Catalogo((long) 1, "PRIMAVERA 2017"), "CHOCOLATE", "NESTLE",
				new Date(), 2.5, 5);
		when(articuloManager.getArticulo(2L)).thenReturn(Optional.of(articulo));
		this.mockMvc.perform(get("/api/articulo/2")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.idArticulo", is(2)));

	}

	//recupera todos los artículos
	@Test
	public void testArticulosRepository() throws Exception {
		List<Articulo> articulos = new ArrayList<Articulo>();
		Articulo miArticulo = new Articulo((long) 1, new Catalogo((long) 1, "PRIMAVERA 2017"), "CHOCOLATE", "NESTLE",
				new Date(), 2.5, 5);
		Articulo miArticulo1 = new Articulo((long) 2, new Catalogo((long) 2, "VERANO 2017"), "NATA", "NESTLE",
				new Date(), 3.5, 5);
		articulos.add(miArticulo);
		articulos.add(miArticulo1);
		when(articuloManager.getArticulos()).thenReturn(new ArrayList<>(articulos));

		this.mockMvc.perform(get("/api/articulo")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.length()", is(articulos.size())));

	}

}
