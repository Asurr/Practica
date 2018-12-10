package com.hector.practica.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "catalogo")
@Table(name = "catalogo")
@ApiModel("Model Catalogo")
@JsonIgnoreProperties({ "articulos" })
public class Catalogo implements Serializable {

	private static final long serialVersionUID = -4492059672842177272L;

	@Id
	@Column(name = "idcatalogo", columnDefinition = "int", nullable = false)
	@ApiModelProperty(value = "Id del catalogo", required = true)
	@NotNull
	private Long idCatalogo;

	@Column(name = "nombre", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Nombre del catalogo", required = true)
	@NotNull
	private String nombre;

	@OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Articulo> articulos;

	public Catalogo() {
	}

	public Catalogo(Long idCatalogo, String nombre) {
		super();
		this.idCatalogo = idCatalogo;
		this.nombre = nombre;
	}

	public Long getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(Long idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCatalogo == null) ? 0 : idCatalogo.hashCode());
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
		Catalogo other = (Catalogo) obj;
		if (idCatalogo == null) {
			if (other.idCatalogo != null)
				return false;
		} else if (!idCatalogo.equals(other.idCatalogo))
			return false;
		return true;
	}

}
