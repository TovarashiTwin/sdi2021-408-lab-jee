package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher {
	@Id
	@GeneratedValue
	private long id;
	private String dni;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	

	
	public Teacher(long id, String dni, String nombre, String primerApellido, String segundoApellido) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		System.out.println("params");
	}
	public Teacher() {
		
	}

	public Long getId() {
		return Long.valueOf(id);
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", primerApellido=" + primerApellido
				+ ", segundoApellido=" + segundoApellido + "]";
	}

	public void setId(long id) {
		this.id = id;		
	}
	
}
