package com.tkinov.fidegar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.tkinov.fidegar.domain.Pregunta;

public class JdbcTemplateFidegarDAO implements LoginDAO{	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
	public List<Pregunta> findAll() {
		return jdbcTemplate.query("select * from PREGUNTA", new PreguntaRowMapper());
	}
	
	@Transactional
	public int actualizaPregunta() {
		return jdbcTemplate.update("UPDATE ADMINMATRICULA SET RESPUESTA = 'PERRO' WHERE MATRICULAID = 1");		
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
