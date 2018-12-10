package com.hector.practica.app.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hector.practica.app.model.Articulo;
import com.hector.practica.app.repository.ArticuloRepository;

@Service
public class ArticuloManager {

	@Autowired
	private ArticuloRepository articuloRepository;

	public List<Articulo> getArticulos() {
		return articuloRepository.findAll();
	}

	public Optional<Articulo> getArticulo(Long id) {
		return articuloRepository.findById(id);
	}

}
