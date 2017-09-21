package com.tkinov.fidegar.domain;

public class Pregunta {
	int preguntaId;
	String descripcion;
	
	public int getPreguntaId() {
		return preguntaId;
	}
	public void setPreguntaId(int preguntaId) {
		this.preguntaId = preguntaId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Pregunta [preguntaId=" + preguntaId + ", descripcion=" + descripcion + "]";
	}
}
