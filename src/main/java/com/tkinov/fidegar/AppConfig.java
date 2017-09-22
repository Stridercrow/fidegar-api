package com.tkinov.fidegar;

import java.sql.SQLException;

import javax.sql.DataSource;

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
	@Bean
	public DataSource dataSource() throws SQLException {		
		OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser("FIDEGAR_DB");
        dataSource.setPassword("Fidegar12#");
        dataSource.setURL("jdbc:oracle:thin:@//192.168.0.127:1521/XE");
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
