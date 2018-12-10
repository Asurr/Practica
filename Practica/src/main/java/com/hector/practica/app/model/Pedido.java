package com.hector.practica.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "pedido")
@Table(name = "pedido")
@ApiModel("Model Pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -4800938660597549082L;

	@Id
	@Column(name = "idpedido", columnDefinition = "int", nullable = false)
	@ApiModelProperty(value = "Id del pedido", required = true)
	@NotNull
	private Long idPedido;

	@Column(name = "fechapedido", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(value = "fecha del pedido", required = true)
	private Date fechaPedido;

	@ManyToOne
	@JoinColumn(name = "idcliente", nullable = false)
	@ApiModelProperty(value = "Id del cliente", required = true)
	private Cliente cliente;

	@JsonManagedReference(value = "pedido_pedido_articulo")
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.EAGER)
	private List<PedidoArticulo> articulos;

	public Pedido() {
	}

	public Pedido(Long idPedido, Date fechaPedido, Cliente cliente) {
		super();
		this.idPedido = idPedido;
		this.fechaPedido = fechaPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PedidoArticulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<PedidoArticulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		return true;
	}

}
