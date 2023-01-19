package com.tutum.example.back.TutumBack.app.exception;

import java.net.ConnectException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tutum.example.back.TutumBack.app.utils.Constantes;
import com.tutum.example.back.TutumBack.app.utils.Response;

@ControllerAdvice
public class ExceptionHandler {

	private final static Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler({ NotFoundException.class })
	@ResponseBody
	public ResponseEntity<Object> notFound(final HttpServletRequest request, final Exception ex) {
		LOG.error("No encontrado: " + ex.getMessage());

		return ResponseEntity.status(Constantes.NOT_FOUND).body("No se encontro informacion");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler({ HttpMessageNotReadableException.class,
			HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class,
			MissingRequestHeaderException.class, MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class, HttpClientErrorException.class,
			MissingServletRequestPartException.class, HttpMediaTypeNotSupportedException.class })
	@ResponseBody
	public ResponseEntity<Object> badRequest(final HttpServletRequest request, final Exception ex) {
		LOG.error("Peticion mal formada: " + ex.getMessage());

		Response response = new Response();
		long inicio = System.currentTimeMillis();

		response.setBody("Petición malformada, revise su petición.");
		response.setCode(Constantes.BAD_REQUEST);

		long fin = System.currentTimeMillis();
		response.setLatency(fin - inicio);

		return ResponseEntity.status(response.getCode()).headers(null).body(response.getBody()	);

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler({ IllegalArgumentException.class,
			HttpServerErrorException.class, ConnectException.class, JsonProcessingException.class, })
	@ResponseBody
	public ResponseEntity<Object> internalServerError(final HttpServletRequest request, final Exception ex) {
		LOG.error("InternalServerError: " + ex.getMessage());

		String mensaje = "Error inesperado, favor de contactar a soporte";

		return ResponseEntity.status(Constantes.INTERNAL_SERVER_ERROR).body(mensaje);
	}


}
