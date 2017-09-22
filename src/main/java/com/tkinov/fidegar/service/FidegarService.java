package com.tkinov.fidegar.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkinov.fidegar.dao.LoginDAO;
import com.tkinov.fidegar.dao.ResetDAO;
import com.tkinov.fidegar.domain.Credencial;
import com.tkinov.fidegar.domain.Dato;
import com.tkinov.fidegar.domain.Response;
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
	
	/*
	 * Valida que sea mayor que cero el tipoOperacion y la matricula no sea cadena vacia
	 */
	public boolean validaNoNulo(Dato datos) {
		if((datos.getTYPE() > 0) && (datos.getMAT().length() > 0)) {
			return true;
		}
		else {
			return false;
		}		
	}
	
	/*public int reset(String tipo, Usuario usuario, Dato dato) {
		return resetDAO.reset(tipo, usuario, dato);
	}*/
	public Response reset(Dato dato) {
		return resetDAO.reset(dato);
	}
}