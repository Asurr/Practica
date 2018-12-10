package com.hector.practica.app.repository;

import org.springframework.stereotype.Repository;

import com.hector.practica.app.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByDni(String dni);

	Cliente findByTelefono(String telefono);

}
