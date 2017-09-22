package com.tkinov.fidegar.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tkinov.fidegar.domain.Credencial;
import com.tkinov.fidegar.domain.Dato;
import com.tkinov.fidegar.domain.Response;
import com.tkinov.fidegar.domain.Usuario;
import com.tkinov.fidegar.service.FidegarService;



@RestController
@RequestMapping("/fidegar-api")
public class FidegarController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	FidegarService fidegarService;
	
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Response> login(@RequestBody Credencial credenciales) {
    	Usuario usuarioAutenticado;
    	ResponseEntity<Response> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(0, "Bad Request", null));
    	logger.debug("Realizando login");
    	if(credenciales != null) {
    		usuarioAutenticado = fidegarService.login(credenciales);
    		if(usuarioAutenticado != null) {
    			usuarioAutenticado = fidegarService.generaToken(usuarioAutenticado);
    			logger.debug("Usuario con token: " + usuarioAutenticado.toString());
    			response = ResponseEntity.status(HttpStatus.OK).body(new Response(1, "Login exitoso", usuarioAutenticado.getToken().toString()));
    		}else {
    			response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(0, "Credenciales inválidas", null));
    		}
    	}
    	return response;    	
    }
    
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ResponseEntity<Response> reset(@RequestBody Dato datos) {
    	Response respuesta;
    	logger.info("Operacion reset: " + datos.getTYPE());
    	logger.info("Datos reset: " + datos.toString());
    	
    	if(fidegarService.validaNoNulo(datos)) {
	    	respuesta = fidegarService.reset(datos);
	    	if(respuesta.getCODE() > 0) {
	    		if(respuesta.getCODE() == 99)
	    			return ResponseEntity.status(HttpStatus.NOT_FOUND).
	    								  body(respuesta);
	    		else
	    			return ResponseEntity.status(HttpStatus.OK)
	    								 .body(respuesta);
	    	}else{
	    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
	    							  body(respuesta);
	    	}
    	}else {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					  body(new Response(0, "Se requiere el tipo de operacion y la matricula ", null));
    	}
    }
}