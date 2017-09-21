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
import com.tkinov.fidegar.domain.Usuario;

public class JdbcTemplateFidegarDAO implements LoginDAO, ResetDAO{	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TokenDAO tokenDAO;
	
	@Autowired
	private MatriculaDAO matriculaDAO;
	
	@Autowired
	private PreguntaDAO preguntaDAO;
	
	@Transactional(readOnly=true)
	public Usuario login(Credencial credenciales) {
		List<Usuario> usuario = jdbcTemplate.query("SELECT * FROM USUARIO WHERE USUARIO = '" + credenciales.getUsuario() + "' AND PASSWORD = '" + credenciales.getPassword() + "'", new UsuarioWrapper());
		logger.debug("Usuario autenticado: " + usuario.size());
		if(usuario.size() == 0)
			return null;
		else
			return usuario.get(0);		
	}
	
	@Transactional
	public int reset(String tipo, Dato datos) {
		int estado = 0;
		int matriculaid = matriculaDAO.getMatriculaIdFromMatricula(datos.getMatricula());
		if(tokenDAO.verificaToken(datos.getToken()) && preguntaDAO.verificaRespuesta(datos.getPreguntaId(), datos.getRespuesta(), matriculaid)) {
			switch (tipo) {
			case "nip": estado = jdbcTemplate.update("UPDATE ADMINMATRICULA SET NIP = ? WHERE MATRICULAID = ?", datos.getActualiza(), matriculaid); 
				break;
			case "no-celular": estado = jdbcTemplate.update("UPDATE ADMINMATRICULA SET NOCELULAR = ? WHERE MATRICULAID = ?", datos.getActualiza(), matriculaid);
				break;
			case "respuesta-secreta": estado = jdbcTemplate.update("UPDATE ADMINMATRICULA SET RESPUESTA = ? WHERE MATRICULAID = ?", datos.getActualiza(), matriculaid);
				break;
			default : estado = 99;
			}			
		}
		logger.info("Despues de ejecutar el query: " + estado);
		return estado;		
	}

	@Override
	public Usuario generaToken(Usuario usuario) {
		logger.debug("Generando Token");
		usuario.setToken(UUID.randomUUID());
		jdbcTemplate.update("UPDATE TOKEN SET TOKEN = ? WHERE USUARIOID = ?", usuario.getToken().toString(), usuario.getUsuarioId());
		return usuario;
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
