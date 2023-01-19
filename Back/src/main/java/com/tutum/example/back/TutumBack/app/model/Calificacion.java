package com.tutum.example.back.TutumBack.app.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_calificaciones")
public class Calificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id_t_calificaciones")
	private long id;

	@JoinColumn(referencedColumnName = "id_t_materias", name = "id_t_materias")
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Materia materia;

	@JoinColumn(referencedColumnName = "id_t_usuarios", name = "id_t_usuarios")
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Alumno alumno;

	@Column(name = "calificacion")
	private Double calificacion;
	
	@Column(name = "fecha_registro")
	private Date fecha_registro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

}
