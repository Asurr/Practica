package com.hector.practica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hector.practica.app.model.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

}
