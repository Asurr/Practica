package com.hector.practica.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hector.practica.app.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Optional<Pedido> findByIdPedidoAndCliente_Dni(long id,String dni);

	List<Pedido> findByCliente_Dni(String dni);

}
