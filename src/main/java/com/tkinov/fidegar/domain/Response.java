package com.tkinov.fidegar.domain;

public class Response {
	int respCodigo;
	String respDescripcion;
	String token;
	
	public Response(int respCodigo, String respDescripcion, String token) {
		this.respCodigo = respCodigo;
		this.respDescripcion = respDescripcion;
		this.token = token;
	}
	
	public int getRespCodigo() {
		return respCodigo;
	}
	public void setRespCodigo(int respCodigo) {
		this.respCodigo = respCodigo;
	}
	public String getRespDescripcion() {
		return respDescripcion;
	}
	public void setRespDescripcion(String respDescripcion) {
		this.respDescripcion = respDescripcion;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
