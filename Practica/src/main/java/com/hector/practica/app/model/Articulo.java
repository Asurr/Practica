package com.hector.practica.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "articulo")
@Table(name = "articulo")
@ApiModel("Model Articulo")
@JsonIgnoreProperties({ "pedidos" })
public class Articulo implements Serializable {

	private static final long serialVersionUID = 4254096363406678051L;

	@Id
	@Column(name = "idarticulo", columnDefinition = "int", nullable = false)
	@ApiModelProperty(value = "Id del Articulo", required = true)
	@NotNull
	private Long idArticulo;

	@ManyToOne
	@MapsId("idcatalogo")
	@JoinColumn(name = "idcatalogo")
	private Catalogo catalogo;

	@Column(name = "nombrearticulo", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Nombre del articulo", required = true)
	private String nombreArticulo;

	@Column(name = "fabricante", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Nombre del fabricante", required = true)
	private String fabricante;

	@Column(name = "fechafabricacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(value = "Fecha de fabricación", required = true)
	private Date fechaFabricacion;

	@Column(name = "precio", columnDefinition = "decimal", nullable = true)
	@ApiModelProperty(value = "Precio del artículo", required = true)
	private double precio;

	@Column(name = "stock", columnDefinition = "int")
	@ApiModelProperty(value = "Cantidad en stock", required = true)
	private int stock;

	@OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoArticulo> pedidos;

	public Articulo() {
		super();
	}

	public Articulo(Long idArticulo, Catalogo catalogo, String nombreArticulo, String fabricante, Date fechaFabricacion,
			double precio, int stock) {
		super();
		this.idArticulo = idArticulo;
		this.catalogo = catalogo;
		this.nombreArticulo = nombreArticulo;
		this.fabricante = fabricante;
		this.fechaFabricacion = fechaFabricacion;
		this.precio = precio;
		this.stock = stock;
	}

	public List<PedidoArticulo> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoArticulo> pedidos) {
		this.pedidos = pedidos;
	}

	public Long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArticulo == null) ? 0 : idArticulo.hashCode());
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
		Articulo other = (Articulo) obj;
		if (idArticulo == null) {
			if (other.idArticulo != null)
				return false;
		} else if (!idArticulo.equals(other.idArticulo))
			return false;
		return true;
	}

}
