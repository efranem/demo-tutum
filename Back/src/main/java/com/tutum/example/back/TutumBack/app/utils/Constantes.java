package com.tutum.example.back.TutumBack.app.utils;

public class Constantes
{
    public static String hostName;
    public static String hostAddress;
    public static String hostPort;
    
    public static String PATH_ARCHIVO_PROPIEDADES;
    
	//EXITOSO
	public static final int OK = 200;          //Peticion exitosa.*
	public static final int CREATED = 201;     //El objeto ha sido creado.*
	public static final int ACEPTED = 202;     //Peticion aceptada, pero no concluida.*
	public static final int NO_CONTENT = 204;  //Solo regresa encabezados.*
	//FALLIDO
	public static final int NOT_MODIFIED = 304;
	public static final int BAD_REQUEST = 400;          //Peticion Erronea.*
	public static final int UNAUTHORIZED = 401;         //Sin autorizacion.*
	public static final int FORBIDDEN = 403;            //Accion/Peticion denegada.
	public static final int NOT_FOUND = 404;            //El recurso solicitado no existe.
	public static final int METHOND_NOT_ALLOWED = 405;  //Metodo no permitido.
	public static final int NOT_ACCEPTABLE = 406;       //El servidor no es capaz de devolver 
//	                                                            los datos en ningun formato aceptado por el cliente.	
	public static final int CONFLICT = 409;             //Bad Headers
	public static final int GONE = 410;                 //Enlace expirado 
	public static final int LENGTH_REQUIRED = 411;      //elementos nulos o vacios
	//REINTENTABLES
	public static final int INTERNAL_SERVER_ERROR = 500;   //Error interno del servidor - NOPE.*
	public static final int SERVICE_UNAVAILABLE = 503;     //Servicio no disponible - Si Retry-After es especificada.
	public static final int GATEWAY_TAIMEOUT = 504;        //Gateway time-out - Si la peticion es idempotente.
	
	
    
}
