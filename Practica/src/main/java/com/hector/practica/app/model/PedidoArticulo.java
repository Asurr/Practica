package com.hector.practica.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;

@Entity(name = "pedidoarticulo")
@Table(name = "pedidoarticulo")
@ApiModel("Model Pedido Articulo")
public class PedidoArticulo implements Serializable {

	private static final long serialVersionUID = -5388229857484630882L;

	@EmbeddedId
	private pedidoArticuloPK pk;

	@Column(name = "cantidad", columnDefinition = "int", nullable = false)
	private int cantidad;

	@JsonBackReference(value = "pedido_pedido_articulo")
	@ManyToOne
	@MapsId("pedidoid")
	@JoinColumn(name = "pedidoid")
	private Pedido pedido;

	@ManyToOne
	@MapsId("articuloid")
	@JoinColumn(name = "articuloid")
	private Articulo articulo;

	// claves primaria compuesta por dos claves ajenas
	@Embeddable
	public static class pedidoArticuloPK implements Serializable {

		private static final long serialVersionUID = 3242593211606033330L;

		@Column(name = "pedidoid", columnDefinition = "int", nullable = false)
		@NotNull
		private Long pedidoId;

		@Column(name = "articuloid", columnDefinition = "int", nullable = false)
		@NotNull
		private Long articuloId;

		public pedidoArticuloPK() {
			super();
		}

		public Long getPedidoId() {
			return pedidoId;
		}

		public void setPedidoId(Long pedidoId) {
			this.pedidoId = pedidoId;
		}

		public Long getArticuloId() {
			return articuloId;
		}

		public void setArticuloId(Long articuloId) {
			this.articuloId = articuloId;
		}

		public pedidoArticuloPK(Long pedidoId, Long articuloId) {
			super();
			this.pedidoId = pedidoId;
			this.articuloId = articuloId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((articuloId == null) ? 0 : articuloId.hashCode());
			result = prime * result + ((pedidoId == null) ? 0 : pedidoId.hashCode());
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
			pedidoArticuloPK other = (pedidoArticuloPK) obj;
			if (articuloId == null) {
				if (other.articuloId != null)
					return false;
			} else if (!articuloId.equals(other.articuloId))
				return false;
			if (pedidoId == null) {
				if (other.pedidoId != null)
					return false;
			} else if (!pedidoId.equals(other.pedidoId))
				return false;
			return true;
		}

	}

	public PedidoArticulo() {
		super();
	}

	public PedidoArticulo(pedidoArticuloPK pk, int cantidad, Pedido pedido, Articulo articulo) {
		super();
		this.pk = pk;
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.articulo = articulo;
	}

	public PedidoArticulo(int cantidad, Pedido pedido, Articulo articulo) {
		super();
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.articulo = articulo;
	}

	public pedidoArticuloPK getPk() {
		return pk;
	}

	public void setPk(pedidoArticuloPK pk) {
		this.pk = pk;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		PedidoArticulo other = (PedidoArticulo) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

}
