package com.codelious.saludows.servicio;

public class SaludarServiceImpl implements SaludarService {

	public String getSaludo(String nombre) {
		
		String mensaje = "Hola " + nombre + " !!";
		return mensaje;

	}

}
