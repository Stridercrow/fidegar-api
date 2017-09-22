package com.tkinov.fidegar;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tkinov.fidegar.dao.JdbcTemplateFidegarDAO;
import com.tkinov.fidegar.dao.JdbcTemplateMatriculaDAO;
import com.tkinov.fidegar.dao.JdbcTemplatePreguntaDAO;
import com.tkinov.fidegar.dao.JdbcTemplateTokenDAO;
import com.tkinov.fidegar.dao.LoginDAO;
import com.tkinov.fidegar.dao.MatriculaDAO;
import com.tkinov.fidegar.dao.PreguntaDAO;
import com.tkinov.fidegar.dao.TokenDAO;
import com.tkinov.fidegar.service.FidegarService;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class AppConfig {
	
	@Autowired
	AppProperties appProperies;
	
	@Bean
	public DataSource dataSource() throws SQLException {		
		OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser(appProperies.getUser());
        dataSource.setPassword(appProperies.getPwd());
        dataSource.setURL(appProperies.getCadena());
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
	}
	
	@Bean
	public FidegarService personService() {
		return new FidegarService();
	}
	
	@Bean
	public LoginDAO jdbcLoginDAO() {
		return new JdbcTemplateFidegarDAO();
	}
	
	@Bean
	public TokenDAO jdbcTokenDAO() {
		return new JdbcTemplateTokenDAO();
	}
	
	@Bean
	public MatriculaDAO jdbcMatriculaDAO() {
		return new JdbcTemplateMatriculaDAO();
	}
	
	@Bean
	public PreguntaDAO jdbcPreguntaDAO() {
		return new JdbcTemplatePreguntaDAO();
	}
}
