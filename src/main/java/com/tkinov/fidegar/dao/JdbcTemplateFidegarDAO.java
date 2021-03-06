package com.tkinov.fidegar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.tkinov.fidegar.domain.Credencial;
import com.tkinov.fidegar.domain.Dato;
import com.tkinov.fidegar.domain.Pregunta;
import com.tkinov.fidegar.domain.Response;
import com.tkinov.fidegar.domain.Usuario;
import com.tkinov.fidegar.utils.TipoOperacion;

public class JdbcTemplateFidegarDAO implements LoginDAO, ResetDAO{	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TokenDAO tokenDAO;
	
	@Autowired
	private MatriculaDAO matriculaDAO;	
	
	@Autowired
	private Response respuestaOperacion;
	
	@Transactional(readOnly=true)
	public Usuario login(Credencial credenciales) {
		List<Usuario> usuario = jdbcTemplate.query("SELECT * FROM USUARIO WHERE USUARIO = '" + credenciales.getUSR() + "' AND PASSWORD = '" + credenciales.getPWD() + "'", new UsuarioWrapper());
		logger.debug("Usuario autenticado: " + usuario.size());
		if(usuario.size() == 0)
			return null;
		else
			return usuario.get(0);		
	}
	
	@Transactional
	public Response reset(Dato datos) {
		int estado = 0;
		int matriculaId = matriculaDAO.getMatriculaIdFromMatricula(datos.getMAT());
		if(matriculaId > 0) {
			if(tokenDAO.verificaToken(datos.getTOKN()) && matriculaId > 0) {
				switch (datos.getTYPE()) {
					case 1: estado = jdbcTemplate.update("UPDATE ADMINMATRICULA SET NIP = ? WHERE MATRICULAID = ?", "1111", matriculaId);
							generaRespuesta(estado, datos.getTYPE());
								//TODO Llamar a servicio para enviar a celular						
						break;
					case 2: estado = jdbcTemplate.update("UPDATE ADMINMATRICULA SET NOCELULAR = ? WHERE MATRICULAID = ?", datos.getCEL(), matriculaId);
							generaRespuesta(estado, datos.getTYPE());
						break;
					case 3: estado = jdbcTemplate.update("UPDATE ADMINMATRICULA SET PREGUNTAID = ? AND RESPUESTA = ? WHERE MATRICULAID = ?", datos.getQTN(), datos.getANS().toUpperCase(), matriculaId);
							generaRespuesta(estado, datos.getTYPE());
						break;
					default : respuestaOperacion.setCODE(99); respuestaOperacion.setDESC("Operación no existe");
				}
			}else {
				respuestaOperacion.setCODE(0);
				respuestaOperacion.setDESC("Token inválido");
			}
		}else {
			respuestaOperacion.setCODE(0);
			respuestaOperacion.setDESC("No se encuentra la matrícula");
		}
		logger.info("Despues de ejecutar el query: " + estado);
		return respuestaOperacion;		
	}

	@Transactional
	public Usuario generaToken(Usuario usuario) {
		logger.debug("Generando Token");
		usuario.setToken(UUID.randomUUID());
		jdbcTemplate.update("UPDATE TOKEN SET TOKEN = ? WHERE USUARIOID = ?", usuario.getToken().toString(), usuario.getUsuarioId());
		return usuario;
	}
	
	private void generaRespuesta(int estado, int tipoOperacion) {
		if(estado != 0) {
			respuestaOperacion.setCODE(0);
			respuestaOperacion.setDESC("Ocurrió un problema en el reset - " + TipoOperacion.valueOf(tipoOperacion).desc());							
		}else {
			respuestaOperacion.setCODE(1);
			respuestaOperacion.setDESC("Reset exitoso - " + TipoOperacion.valueOf(tipoOperacion).desc());
		}
	}
}

class PreguntaRowMapper implements RowMapper<Pregunta> {

	@Override
	public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pregunta pregunta = new Pregunta();
		pregunta.setPreguntaId(rs.getInt("preguntaId"));
		pregunta.setDescripcion(rs.getString("descripcion"));
		return pregunta;
	}
	
}

class UsuarioWrapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario usuario = new Usuario();			
		usuario = new Usuario();		
		usuario.setUsuarioId(rs.getInt("usuarioId"));
		usuario.setMatriculaId(rs.getInt("matriculaId"));
		usuario.setUsuario(rs.getString("usuario"));
		usuario.setPassword(rs.getString("password"));
		usuario.setEstatus(rs.getString("estatus"));
		return usuario;		
	}	
}
