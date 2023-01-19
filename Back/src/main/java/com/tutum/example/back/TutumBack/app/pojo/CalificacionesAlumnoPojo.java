package com.tutum.example.back.TutumBack.app.pojo;

public class CalificacionesAlumnoPojo {

	private long id;
	private String id_t_usuario;
	private String nombre;
	private String apellido;
	private String materia;
	private String calificacion;
	private String fecha_registro;

	public String getId_t_usuario() {
		return id_t_usuario;
	}

	public void setId_t_usuario(String id_t_usuario) {
		this.id_t_usuario = id_t_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
