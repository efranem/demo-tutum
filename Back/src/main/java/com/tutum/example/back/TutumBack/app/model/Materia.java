package com.tutum.example.back.TutumBack.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_materias")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id_t_materias")
	private long id;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "activo")
	private Integer activo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}
