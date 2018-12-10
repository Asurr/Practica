package com.hector.practica.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "cliente")
@Table(name = "cliente")
@ApiModel("Model Cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 506685522964423582L;

	@Id
	@Column(name = "idcliente", columnDefinition = "int", nullable = false)
	@ApiModelProperty(value = "Id del cliente", required = true)
	@NotNull
	private Long idCliente;

	@Column(name = "nombre", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Nombre del cliente", required = true)
	@NotNull
	private String nombre;

	@Column(name = "primerapellido", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Primer apellido", required = true)
	@NotNull
	private String primerApellido;

	@Column(name = "segundoapellido", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Segundo apellido", required = true)
	private String segundoApellido;

	@Column(name = "direccion", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Dirección", required = true)
	private String direccion;

	@Column(name = "dni", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "DNI", required = true)
	@NotNull
	private String dni;

	@Column(name = "telefono", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Telefono", required = true)
	private String telefono;

	@JsonIgnore
	@Column(name = "cuenta", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Cuenta bancaria", required = true)
	@NotNull
	private String cuenta;

	@JsonIgnore
	@Column(name = "password", columnDefinition = "varchar", length = 255, nullable = false)
	@ApiModelProperty(value = "Contraseña", required = true)
	@NotNull
	private String password;

	public Cliente() {
		super();
	}

	public Cliente(Long idCliente, String nombre, String primerApellido, String segundoApellido, String direccion,
			String dni, String telefono, String cuenta, String password) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.direccion = direccion;
		this.dni = dni;
		this.telefono = telefono;
		this.cuenta = cuenta;
		this.password = password;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

}
