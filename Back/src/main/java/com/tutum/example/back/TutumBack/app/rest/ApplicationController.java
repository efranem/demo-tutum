package com.tutum.example.back.TutumBack.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutum.example.back.TutumBack.app.model.Alumno;
import com.tutum.example.back.TutumBack.app.model.Materia;
import com.tutum.example.back.TutumBack.app.pojo.CalificacionPojo;
import com.tutum.example.back.TutumBack.app.service.ApplicationService;
import com.tutum.example.back.TutumBack.app.utils.Response;

@RestController
@RequestMapping(path = "/") // FIXME poner contextos de las operaciones aqui
public class ApplicationController {

	@Autowired
	private ApplicationService service;

	@GetMapping(path = "/keepAlive", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> keepAlive() {
		return ResponseEntity.status(200).body("{\"status\" : \"ALIVE\"}");
	}
	
	@GetMapping(path = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> version() {
		return ResponseEntity.status(200).body("1.0");
	}
	
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> alumnos() {

		List<Alumno> alumnos = service.findAllAlumnos();

		return ResponseEntity.status(200).body(alumnos);
	}
	
	@PostMapping(path = "/alumno", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> altaAlumno(@RequestBody Alumno alumno) {

		alumno = service.altaAlumno(alumno) ;

		return ResponseEntity.status(200).body(alumno);
	}
	
	@DeleteMapping(path = "/alumno/{idAlumno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> eliminaAlumno(@PathVariable String idAlumno) {

		Response response = service.eliminaAlumno(Long.parseLong(idAlumno));

		return ResponseEntity.status(response.getCode()).body(response.getBody());
	}
	
	@GetMapping(path = "/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> materias() {

		List<Materia> materias = service.findAllMaterias();

		return ResponseEntity.status(200).body(materias);
	}

	@PostMapping(path = "/calificacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> inseryaCalificacion(@RequestBody CalificacionPojo califiacion) {

		Response response = service.altaCalificacion(califiacion);

		return ResponseEntity.status(response.getCode()).body(response.getBody());
	}
	
	@PutMapping(path = "/calificacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> modificaCalificacion(@RequestBody CalificacionPojo califiacion) {

		Response response = service.modificaCalificacion(califiacion);

		return ResponseEntity.status(response.getCode()).body(response.getBody());
	}

	@GetMapping(path = "/calificacion/{idAlumno}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> calificacion(@PathVariable String idAlumno) {

		Response response = service.consultaCalificaciones(idAlumno);

		return ResponseEntity.status(response.getCode()).body(response.getObject());
	}
	
	@DeleteMapping(path = "/calificacion/{idCalificacion}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> eliminaCalificacion(@PathVariable String idCalificacion) {

		Response response = service.eliminaCalificacion(Long.parseLong(idCalificacion));

		return ResponseEntity.status(response.getCode()).body(response.getBody());
	}
	
}
