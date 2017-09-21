package com.tkinov.fidegar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkinov.fidegar.dao.LoginDAO;
import com.tkinov.fidegar.domain.Pregunta;

public class FidegarService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	public List<Pregunta> findAll() {
		return loginDAO.findAll();
	}
}