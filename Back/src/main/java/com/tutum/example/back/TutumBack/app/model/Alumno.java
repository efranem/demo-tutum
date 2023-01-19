package com.tutum.example.back.TutumBack.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_alumnos")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id_t_usuarios")
	private long id;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "ap_paterno")
	private String ap_paterno;
	
	@Column(name = "ap_materno")
	private String ap_materno;
	
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

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}

	public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}
