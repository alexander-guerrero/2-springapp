package com.alex;

import org.springframework.beans.factory.annotation.Autowired;

public class Administrador {

	private int idAdm;
	private String nombre;

	// Anotación en la propiedad
	// @Autowired
	private Direccion direccion;

	public Administrador() {

	}

	public Administrador(int idAdm, String nombre) {
		this.idAdm = idAdm;
		this.nombre = nombre;
	}

	public void setIdAdm(int idAdm) {
		this.idAdm = idAdm;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Anotación en el método Setter
	@Autowired
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Administrador [idAdm=" + idAdm + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}

	public void imprimirDireccion() {
		System.out.println("Av. Santa Rosa 700");
	}

}
