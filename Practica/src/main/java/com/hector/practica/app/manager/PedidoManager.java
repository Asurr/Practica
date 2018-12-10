package com.hector.practica.app.manager;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hector.practica.app.model.Pedido;
import com.hector.practica.app.repository.PedidoRepository;

@Service
public class PedidoManager {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> getPedidos(String dni) {
		return pedidoRepository.findByCliente_Dni(dni);
	}

	public Optional<Pedido> getPedido(Long id,String dni) {
		return pedidoRepository.findByIdPedidoAndCliente_Dni(id,dni);
	}

	@Transactional(rollbackOn = Exception.class)
	public Pedido setPedido(Pedido pedido) {	
		return pedidoRepository.save(pedido);
	}

	@Transactional(rollbackOn = Exception.class)
	public Pedido putPedido(Pedido pedido,String dni) {
		if(pedidoRepository.findById(pedido.getIdPedido()).get().getCliente().getDni().equalsIgnoreCase(pedido.getCliente().getDni())) {
			return pedidoRepository.save(pedido);
		}else return null;

	}

	@Transactional(rollbackOn = Exception.class)
	public boolean deletePedido(Long id,String dni) {
		Optional<Pedido> mipedido = pedidoRepository.findById(id);		
		if (mipedido.isPresent()) {
			if(mipedido.get().getCliente().getDni().equals(dni)) {
			pedidoRepository.deleteById(id);
			return true;	
			}else return false;			
		} else {
			return false;

		}
	}

}
