package com.tutum.example.back.TutumBack.app.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutum.example.back.TutumBack.app.model.Alumno;
import com.tutum.example.back.TutumBack.app.model.Calificacion;
import com.tutum.example.back.TutumBack.app.model.Materia;
import com.tutum.example.back.TutumBack.app.pojo.CalificacionPojo;
import com.tutum.example.back.TutumBack.app.pojo.CalificacionesAlumnoPojo;
import com.tutum.example.back.TutumBack.app.pojo.Promedio;
import com.tutum.example.back.TutumBack.app.repository.AlumnoRepository;
import com.tutum.example.back.TutumBack.app.repository.CalificacionRepository;
import com.tutum.example.back.TutumBack.app.repository.MateriaRepository;
import com.tutum.example.back.TutumBack.app.utils.Response;

@Service
public class ApplicationService {

	@Autowired
	AlumnoRepository alumnoRepository;

	@Autowired
	MateriaRepository materiaRepository;

	@Autowired
	CalificacionRepository calificacionRepository;

	public Alumno findById(long id) {
		Optional<Alumno> optional = alumnoRepository.findById(id);

		return optional.get();
	}
	
	public List<Alumno> findAllAlumnos() {
		List<Alumno> alumnos = alumnoRepository.findAll();

		return alumnos;
	}
	
	public Alumno altaAlumno(Alumno alumno) {
		alumno = alumnoRepository.save(alumno);

		return alumno;
	}
	
	public Response eliminaAlumno(long idAlumno) {

		Response response = new Response();

		Optional<Alumno> optional = alumnoRepository.findById(idAlumno);
		
		if(optional.isPresent()) {
			alumnoRepository.deleteById(idAlumno);
			
			response.setBody("{\"success\":\"ok\", \"msg\":\"Alumno Eliminado \"} ");
			response.setCode(200);
			return response;
		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"Alumno NO Existe\"} ");
			response.setCode(400);
			return response;
		}

	}
	
	public List<Materia> findAllMaterias() {
		List<Materia> materias = materiaRepository.getMateriasActivas();

		return materias;
	}
	

	public Response altaCalificacion(CalificacionPojo calificacionPojo) {

		Response response = new Response();

		Calificacion calificacion = new Calificacion();

		if (calificacionPojo.getCalificacion() != null) {
			calificacion.setCalificacion(calificacionPojo.getCalificacion());
		} else {
			response.setBody("La calificacion es obligatoria");
			response.setCode(400);
			return response;
		}

		calificacion.setFecha_registro(new Date(System.currentTimeMillis()));

		Optional<Alumno> optionalAlumno = alumnoRepository.findById(calificacionPojo.getIdAlumno());
		if (optionalAlumno.isPresent()) {
			calificacion.setAlumno(optionalAlumno.get());
		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"No existe el Alumno\"}");
			response.setCode(400);
			return response;
		}

		Optional<Materia> optionalMateria = materiaRepository.findById(calificacionPojo.getIdMateria());
		if (optionalMateria.isPresent()) {
			calificacion.setMateria(optionalMateria.get());
		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"No existe la Materia\"}");
			response.setCode(400);
			return response;
		}
		
		Calificacion calificacion2 = calificacionRepository.getCalificacion(calificacion.getAlumno(), calificacion.getMateria());
		if(calificacion2 != null) {
			response.setBody("{\"success\":\"fail\", \"msg\":\"La materia ya se encuentra evaluada, modifique el registro correspondiente\"}");
			response.setCode(400);
			return response;
		}

		calificacion = calificacionRepository.save(calificacion);

		response.setBody("{\"success\":\"ok\", \"msg\":\"Calificacion Registrada\"} ");
		response.setCode(200);
		return response;
	}

	public Response consultaCalificaciones(String idAlumno) {
		Response response = new Response();

		Optional<Alumno> optionalAlumno = alumnoRepository.findById(Long.parseLong(idAlumno));
		if (optionalAlumno.isPresent()) {

			List<Calificacion> calificaciones = calificacionRepository.getCalificaciones(optionalAlumno.get());

			List<CalificacionesAlumnoPojo> lista = new ArrayList<CalificacionesAlumnoPojo>();
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			double promedio = 0;
			
			for (Calificacion cal : calificaciones) {
				
				CalificacionesAlumnoPojo pojo = new CalificacionesAlumnoPojo();
				pojo.setId(cal.getId());
				pojo.setId_t_usuario(String.valueOf(cal.getAlumno().getId()));
				pojo.setNombre(cal.getAlumno().getNombre());
				pojo.setApellido(cal.getAlumno().getAp_paterno());
				pojo.setMateria(cal.getMateria().getNombre());
				pojo.setCalificacion(String.valueOf(cal.getCalificacion()));
				pojo.setFecha_registro(simpleDateFormat.format(cal.getFecha_registro()));
				
				promedio += cal.getCalificacion();
				
				lista.add(pojo);
			}
			
			promedio = promedio/calificaciones.size();
			
			Promedio pr = new Promedio();
			pr.setPromedio(promedio);
			
			pr.setCalificaciones(lista);
			
			
			response.setObject(pr);
			response.setCode(200);

			return response;

		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"No existe el Alumno\"}");
			response.setCode(400);
			return response;
		}

	}
	
	public Response modificaCalificacion(CalificacionPojo calificacionPojo) {

		Response response = new Response();

		Calificacion calificacion = new Calificacion();

		if (calificacionPojo.getCalificacion() != null) {
			calificacion.setCalificacion(calificacionPojo.getCalificacion());
		} else {
			response.setBody("La calificacion es obligatoria");
			response.setCode(400);
			return response;
		}

		calificacion.setFecha_registro(new Date(System.currentTimeMillis()));

		Optional<Alumno> optionalAlumno = alumnoRepository.findById(calificacionPojo.getIdAlumno());
		if (optionalAlumno.isPresent()) {
			calificacion.setAlumno(optionalAlumno.get());
		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"No existe el Alumno\"}");
			response.setCode(400);
			return response;
		}

		Optional<Materia> optionalMateria = materiaRepository.findById(calificacionPojo.getIdMateria());
		if (optionalMateria.isPresent()) {
			calificacion.setMateria(optionalMateria.get());
		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"No existe la Materia\"}");
			response.setCode(400);
			return response;
		}
		
		Calificacion calificacion2 = calificacionRepository.getCalificacion(calificacion.getAlumno(), calificacion.getMateria());
		
		if(calificacion2 != null) {
			calificacion.setId(calificacion2.getId());
			calificacion = calificacionRepository.save(calificacion);
			
			response.setBody("{\"success\":\"ok\", \"msg\":\"Calificacion Actualizada\"} ");
			response.setCode(200);
			return response;
		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"Calificacion NO Actualizada, Resgistrela primero\"} ");
			response.setCode(400);
			return response;
		}

	}
	
	public Response eliminaCalificacion(long idCalificacion) {

		Response response = new Response();

		Optional<Calificacion> optional = calificacionRepository.findById(idCalificacion);
		
		if(optional.isPresent()) {
			calificacionRepository.deleteById(idCalificacion);
			
			response.setBody("{\"success\":\"ok\", \"msg\":\"Calificacion Eliminada \"} ");
			response.setCode(200);
			return response;
		} else {
			response.setBody("{\"success\":\"fail\", \"msg\":\"Calificacion NO Existe\"} ");
			response.setCode(400);
			return response;
		}

	}

}
