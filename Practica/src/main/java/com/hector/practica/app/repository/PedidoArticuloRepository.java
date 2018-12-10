package com.hector.practica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hector.practica.app.model.PedidoArticulo;
import com.hector.practica.app.model.PedidoArticulo.pedidoArticuloPK;

@Repository
public interface PedidoArticuloRepository extends JpaRepository<PedidoArticulo, pedidoArticuloPK> {

}
