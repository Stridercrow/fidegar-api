package com.tkinov.fidegar.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkinov.fidegar.dao.LoginDAO;
import com.tkinov.fidegar.dao.ResetDAO;
import com.tkinov.fidegar.domain.Credencial;
import com.tkinov.fidegar.domain.Dato;
import com.tkinov.fidegar.domain.Usuario;

public class FidegarService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private ResetDAO resetDAO;
	
	public Usuario login(Credencial credenciales) {
		return loginDAO.login(credenciales);
	}	
	
	public Usuario generaToken(Usuario usuarioAutenticado) {
		return loginDAO.generaToken(usuarioAutenticado);
	}
	
	/*public int reset(String tipo, Usuario usuario, Dato dato) {
		return resetDAO.reset(tipo, usuario, dato);
	}*/
	public int reset(String tipo, Dato dato) {
		return resetDAO.reset(tipo, dato);
	}
}