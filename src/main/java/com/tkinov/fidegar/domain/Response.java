package com.tkinov.fidegar.domain;

public class Response {
	int respCodigo;
	String respDescripcion;
	
	public Response(int respCodigo, String respDescripcion) {
		this.respCodigo = respCodigo;
		this.respDescripcion = respDescripcion;
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
	
	
}
