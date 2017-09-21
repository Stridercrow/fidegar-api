package com.tkinov.fidegar.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTokenDAO implements TokenDAO{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean verificaToken(String token) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT UsuarioId FROM TOKEN WHERE TOKEN = ?", token);
			logger.debug(map.toString());
			return true;	
		}catch(EmptyResultDataAccessException er) {
			return false;
		}		
	}
}