package com.tutum.example.back.TutumBack.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutum.example.back.TutumBack.app.model.Alumno;
import com.tutum.example.back.TutumBack.app.model.Calificacion;
import com.tutum.example.back.TutumBack.app.model.Materia;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

	@Query("SELECT c FROM Calificacion c WHERE alumno = :alumno ")
	public List<Calificacion> getCalificaciones(Alumno alumno);
	
	@Query("SELECT c FROM Calificacion c WHERE alumno = :alumno and materia= :materia")
	public Calificacion getCalificacion(Alumno alumno, Materia materia);

}
