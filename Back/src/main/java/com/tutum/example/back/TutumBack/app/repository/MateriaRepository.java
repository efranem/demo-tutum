package com.tutum.example.back.TutumBack.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutum.example.back.TutumBack.app.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

	@Query("SELECT m FROM Materia m WHERE activo = 1")
	public List<Materia> getMateriasActivas();
	
}
