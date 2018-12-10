package com.hector.practica.app.test;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hector.practica.app.controller.PedidoController;
import com.hector.practica.app.manager.PedidoManager;
import com.hector.practica.app.model.Articulo;
import com.hector.practica.app.model.Catalogo;
import com.hector.practica.app.model.Cliente;
import com.hector.practica.app.model.Pedido;
import com.hector.practica.app.model.PedidoArticulo;
import com.hector.practica.app.repository.PedidoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PedidoController.class)
@WithMockUser("71754769F")
public class PedidoControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PedidoRepository pedidoRepository;

	@MockBean
	private PedidoManager pedidoManager;
	
	@MockBean
    private UserDetailsService userDetailsService;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	//obtiene un pedido
	@Test
	public void testPedidoRepository() throws Exception {
		 Pedido p1 = new Pedido();
		 p1.setCliente(new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C"));
		 p1.setFechaPedido(new Date());
		 p1.setIdPedido((long) 16);
		 List<PedidoArticulo> miLista= new ArrayList<>();
		 PedidoArticulo miPedidoArticulo = new PedidoArticulo(10, new Pedido((long)16,new Date(), new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C")),new Articulo((long)1, new Catalogo((long)1, "PRIMAVERA 2017"), "CHOCOLATE","NESTLE", new Date(), 2.5, 5));	 
		 miLista.add(miPedidoArticulo);
		 p1.setArticulos(miLista);
		when(pedidoManager.getPedido(16L,"71754769F")).thenReturn(Optional.of(p1));
		this.mockMvc.perform(get("/api/pedido/16")).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.idPedido", is(16)));
		
	}
	
	//obtiene la lista de pedidos
	@Test
	public void testPedidosRepository() throws Exception {
		 List<Pedido> pedidos = new ArrayList<Pedido>();
		 Pedido p1 = new Pedido();
		 p1.setCliente(new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C"));
		 p1.setFechaPedido(new Date());
		 p1.setIdPedido((long) 16);
		 List<PedidoArticulo> miLista= new ArrayList<>();
		 PedidoArticulo miPedidoArticulo = new PedidoArticulo(10, new Pedido((long)16,new Date(), new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C")),new Articulo((long)1, new Catalogo((long)1, "PRIMAVERA 2017"), "CHOCOLATE","NESTLE", new Date(), 2.5, 5));	 
		 miLista.add(miPedidoArticulo);
		 p1.setArticulos(miLista);
		 Pedido p2 = new Pedido();
		 p2.setCliente(new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C"));
		 p2.setFechaPedido(new Date());
		 p2.setIdPedido((long) 18);
		 List<PedidoArticulo> miLista2= new ArrayList<>();
		 PedidoArticulo miPedidoArticulo2 = new PedidoArticulo(10, new Pedido((long)18,new Date(), new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C")),new Articulo((long)1, new Catalogo((long)1, "PRIMAVERA 2017"), "CHOCOLATE","NESTLE", new Date(), 2.5, 5));	 
		 miLista2.add(miPedidoArticulo2);
		 p2.setArticulos(miLista2);
		 pedidos.add(p1);
		 pedidos.add(p2);
		 when(pedidoManager.getPedidos("71754769F")).thenReturn(new ArrayList<>(pedidos));
		 this.mockMvc.perform(get("/api/pedido")).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.length()", is(pedidos.size())));
 		
	}

	//elimina un pedido
	@Test
	public void testPedidoRemoveRepository() throws Exception {
		 when(pedidoManager.deletePedido(16L,"71754769F")).thenReturn(Boolean.TRUE);
		 this.mockMvc.perform(delete("/api/pedido/16")).andExpect(status().isOk());
		
	}
	
	//intenta eliminar un pedido que no existe
	@Test
	public void testPedidoRemove1Repository() throws Exception {
		 when(pedidoManager.deletePedido(16L,"71754769F")).thenReturn(Boolean.FALSE);
		 this.mockMvc.perform(delete("/api/pedido/16")).andExpect(status().isNotFound());
		
	}
	
	//modifica un pedido
	@Test
	public void testPedidoPutRepository() throws Exception {
		 List<Pedido> pedidos = new ArrayList<Pedido>();
		 Pedido p1 = new Pedido();
		 p1.setCliente(new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C"));
		 p1.setFechaPedido(new Date());
		 p1.setIdPedido((long) 16);
		 List<PedidoArticulo> miLista= new ArrayList<>();
		 PedidoArticulo miPedidoArticulo = new PedidoArticulo(10, new Pedido((long)16,new Date(), new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C")),new Articulo((long)1, new Catalogo((long)1, "PRIMAVERA 2017"), "CHOCOLATE","NESTLE", new Date(), 2.5, 5));	 
		 miLista.add(miPedidoArticulo);
		 p1.setArticulos(miLista);
		 Pedido p2 = new Pedido();
		 p2.setCliente(new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C"));
		 p2.setFechaPedido(new Date());
		 p2.setIdPedido((long) 18);
		 List<PedidoArticulo> miLista2= new ArrayList<>();
		 PedidoArticulo miPedidoArticulo2 = new PedidoArticulo(10, new Pedido((long)18,new Date(), new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C")),new Articulo((long)1, new Catalogo((long)1, "PRIMAVERA 2017"), "CHOCOLATE","NESTLE", new Date(), 2.5, 5));	 
		 miLista2.add(miPedidoArticulo2);
		 p2.setArticulos(miLista2);
		 pedidos.add(p1);
		 pedidos.add(p2);
		 p1.getArticulos().get(0).setCantidad(33);
		when(pedidoManager.putPedido(p1,"71754769F")).thenReturn(p1);
		this.mockMvc.perform(put("/api/pedido").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(p1))).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
	}
	
	//inserta un pedido
	@Test
	public void testPedidoPostRepository() throws Exception {
		 Pedido p1 = new Pedido();
		 p1.setCliente(new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C"));
		 p1.setFechaPedido(new Date());
		 p1.setIdPedido(16L);
		 List<PedidoArticulo> miLista= new ArrayList<>();
		 PedidoArticulo miPedidoArticulo = new PedidoArticulo(10, new Pedido(16L,new Date(), new Cliente((long)1,"ENRIQUE","PASTOR","GALDOS","Calle ave del paraiso 7","71754769F","6130607950","232323123213215545345","$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C")),new Articulo((long)1, new Catalogo((long)1, "PRIMAVERA 2017"), "CHOCOLATE","NESTLE", new Date(), 2.5, 5));	 
		 miLista.add(miPedidoArticulo);
		 p1.setArticulos(miLista);
		 when(pedidoManager.setPedido(p1)).thenReturn(p1);
		 this.mockMvc.perform(post("/api/pedido").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(p1))).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
         .andExpect(jsonPath("$.idPedido", is(p1.getIdPedido().intValue())));
		
	}
	
	//utilidad para convertir un objeto a json
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

}
