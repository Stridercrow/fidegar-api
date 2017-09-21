package com.tkinov.fidegar.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Response login(@RequestBody Credencial credenciales) {
    	Usuario usuarioAutenticado;    	
    	Response response = new Response(400, "Bad Request", null);
    	logger.debug("Realizando login");
    	if(credenciales != null) {
    		usuarioAutenticado = fidegarService.login(credenciales);
    		if(usuarioAutenticado != null) {
    			usuarioAutenticado = fidegarService.generaToken(usuarioAutenticado);
    			logger.debug("Usuario con token: " + usuarioAutenticado.toString());
    			response = new Response(200, "Login exitoso", usuarioAutenticado.getToken().toString());
    		}else {
    			response = new Response(403, "Credenciales inválidas", null);
    		}
    	}
    	return response;    	
    }
    
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public Response reset(@RequestParam(value="tipo") String tipo, @RequestBody Dato datos) {
    	int estado;
    	logger.info("Operacion reset: " + tipo);
    	logger.info("Datos reset: " + datos.toString());
    	
    	estado = fidegarService.reset(tipo, datos);
    	if(estado > 0)
    		if(estado == 99)
    			return new Response(404, "No se encontro la operacion", null);
    		else
    			return new Response(200, "Reset " + tipo + " realizado exitosamente", null);
    	else
    	return new Response(403, "No autorizado, verificar token, respuesta - reset " + tipo, null);
    }
}