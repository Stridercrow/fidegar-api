package com.tkinov.fidegar.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateMatriculaDAO implements MatriculaDAO{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int getMatriculaIdFromMatricula(String matricula) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT MatriculaId FROM MATRICULA WHERE MATRICULA = ?", matricula);
			logger.debug(map.toString());
			return Integer.parseInt(map.get("MATRICULAID").toString());
		}catch(EmptyResultDataAccessException de) {
			return 0;
		}
	}	
}
