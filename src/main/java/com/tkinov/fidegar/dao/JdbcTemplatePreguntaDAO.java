package com.tkinov.fidegar.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplatePreguntaDAO implements PreguntaDAO{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public boolean verificaRespuesta(int preguntaId, String respuesta, int matriculaId) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT RESPUESTA FROM ADMINMATRICULA WHERE PREGUNTAID = ? AND MATRICULAID = ?", preguntaId, matriculaId);
			logger.debug(map.toString());
			if(map.get("RESPUESTA").equals(respuesta))
				return true;
			else
				return false;
		}catch(EmptyResultDataAccessException er){
			return false;
		}
	}
}