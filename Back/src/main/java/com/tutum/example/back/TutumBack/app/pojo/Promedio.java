package com.tutum.example.back.TutumBack.app.pojo;

import java.util.List;

public class Promedio {
	private List<CalificacionesAlumnoPojo> calificaciones;
	
	private double promedio;

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public List<CalificacionesAlumnoPojo> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<CalificacionesAlumnoPojo> calificaciones) {
		this.calificaciones = calificaciones;
	}
}
