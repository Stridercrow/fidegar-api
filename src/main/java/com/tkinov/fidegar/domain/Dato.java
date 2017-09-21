package com.tkinov.fidegar.domain;

public class Dato {
	private String matricula;
	private String actualiza;
	private int preguntaId;
	private String respuesta;
	private String token;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getActualiza() {
		return actualiza;
	}
	public void setActualiza(String actualiza) {
		this.actualiza = actualiza;
	}
	public int getPreguntaId() {
		return preguntaId;
	}
	public void setPreguntaId(int preguntaId) {
		this.preguntaId = preguntaId;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Dato [matricula=" + matricula + ", actualiza=" + actualiza + ", preguntaId=" + preguntaId
				+ ", respuesta=" + respuesta + ", token=" + token + "]";
	}
	
		
}
