package com.tkinov.fidegar.domain;

import java.util.UUID;

public class Usuario {
	
	private int usuarioId;
	private int matriculaId;
	private String usuario;
	private String password;
	private String estatus;
	private UUID token;
	
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public int getMatriculaId() {
		return matriculaId;
	}
	public void setMatriculaId(int matriculaId) {
		this.matriculaId = matriculaId;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public UUID getToken() {
		return token;
	}
	public void setToken(UUID token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", matriculaId=" + matriculaId + ", usuario=" + usuario + ", password="
				+ password + ", estatus=" + estatus + ", token=" + token + "]";
	}
	

}
