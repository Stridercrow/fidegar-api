package com.tkinov.fidegar.dao;

import com.tkinov.fidegar.domain.Dato;
import com.tkinov.fidegar.domain.Response;

public interface ResetDAO {
	
	//int reset(String tipo, Usuario usuario, Dato dato);
	Response reset(Dato dato);
}
