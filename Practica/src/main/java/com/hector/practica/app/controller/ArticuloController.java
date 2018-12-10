package com.hector.practica.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hector.practica.app.exception.ArticuloNotFoundException;
import com.hector.practica.app.manager.ArticuloManager;
import com.hector.practica.app.model.Articulo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Artículos", description = "Consulta los artículos asi como permite buscar artículos por id")
public class ArticuloController {

	@Autowired
	ArticuloManager articuloManager;

	@ApiOperation(value = "Lista todos los artículos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito obteniendo lista"),
			@ApiResponse(code = 500, message = "Error generico"),
			@ApiResponse(code = 404, message = "No se encontró la ruta especificada") })
	@RequestMapping(value = "/api/articulo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Articulo>> getArticulos() {
		final List<Articulo> articulos = articuloManager.getArticulos();
		if (!articulos.isEmpty()) {
			return ResponseEntity.ok(articulos);
		}
		throw new ArticuloNotFoundException("No existen articulos");
	}

	@ApiOperation(value = "Obtiene un artículo concreto mediante la busqueda por id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito obteniendo artículo"),
			@ApiResponse(code = 500, message = "Error generico"),
			@ApiResponse(code = 404, message = "No se encontró la ruta especificada") })
	@RequestMapping(value = "/api/articulo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Articulo>> getArticulo(@PathVariable Long id) {
		final Optional<Articulo> articulo = articuloManager.getArticulo(id);
		if (articulo.isPresent()) {
			return ResponseEntity.ok(articulo);
		}
		throw new ArticuloNotFoundException("No existe el artículo ".concat(id.toString()));
	}
}
