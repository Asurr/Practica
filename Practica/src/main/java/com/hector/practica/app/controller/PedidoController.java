package com.hector.practica.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hector.practica.app.exception.PedidoNotFoundException;
import com.hector.practica.app.manager.PedidoManager;
import com.hector.practica.app.model.Pedido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Pedidos", description = "Consulta los pedidos asi como permite buscar pedidos por id y añadir")
public class PedidoController {

	
	@Autowired
	private PedidoManager pedidoManager;

	@ApiOperation(value = "Lista todos los Pedidos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito obteniendo lista"),
			@ApiResponse(code = 500, message = "Error generico"),
			@ApiResponse(code = 404, message = "No se encontró la ruta especificada") })
	@RequestMapping(value = "/api/pedido", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> getPedidos(Authentication authentication) {	
		final List<Pedido> pedidos = pedidoManager.getPedidos(authentication.getName());
		if (!pedidos.isEmpty()) {
			return ResponseEntity.ok(pedidos);
		}
		throw new PedidoNotFoundException("No existen pedidos");
	}

	@ApiOperation(value = "Obtiene un pedido concreto mediante la busqueda por id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito obteniendo el pedido"),
			@ApiResponse(code = 500, message = "Error generico"),
			@ApiResponse(code = 404, message = "No se encontró la ruta especificada") })
	@RequestMapping(value = "/api/pedido/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Pedido>> getPedido(@PathVariable Long id,Authentication authentication) {

		final Optional<Pedido> pedido = pedidoManager.getPedido(id,authentication.getName());
		if (pedido.isPresent()) {
			return ResponseEntity.ok(pedido);
		}
		throw new PedidoNotFoundException("No existe el pedido ".concat(id.toString()));
	}

	@ApiOperation(value = "Guarda un pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito guardando pedido"),
			@ApiResponse(code = 500, message = "Error generico"),
			@ApiResponse(code = 404, message = "No se encontró la ruta especificada") })
	@RequestMapping(value = "/api/pedido", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> setPedido(@Valid @RequestBody Pedido pedido) {
		Pedido miPedido=pedidoManager.setPedido(pedido);
		if (miPedido!=null) {
			return ResponseEntity.ok(miPedido);
		} else {
			throw new PedidoNotFoundException("No se ha podido guardar el pedido");
		}
	}

	@ApiOperation(value = "Modifica un pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito modificando pedido"),
			@ApiResponse(code = 500, message = "Error generico"),
			@ApiResponse(code = 404, message = "No se encontró la ruta especificada") })
	@RequestMapping(value = "/api/pedido", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> putPedido(@Valid @RequestBody Pedido pedido,Authentication authentication) {
		Pedido miPedido = pedidoManager.putPedido(pedido,authentication.getName());
		if (miPedido!=null) {
			return ResponseEntity.ok(miPedido);
		} else {
			throw new PedidoNotFoundException("No se ha podido actualizar el pedido");
		}
	}

	@ApiOperation(value = "Borra un pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito borrando pedido"),
			@ApiResponse(code = 500, message = "Error generico"),
			@ApiResponse(code = 404, message = "No se encontró la ruta especificada") })
	@RequestMapping(value = "/api/pedido/{id}", method = RequestMethod.DELETE)
	public void deletePedido(@PathVariable Long id,Authentication authentication) {
		if (!pedidoManager.deletePedido(id,authentication.getName())) {
			throw new PedidoNotFoundException("No se ha podido eliminar el pedido");
		}
	}

}
