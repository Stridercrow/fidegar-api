package com.tkinov.fidegar.dao;

import com.tkinov.fidegar.domain.Credencial;
import com.tkinov.fidegar.domain.Usuario;

public interface LoginDAO {
	Usuario login(Credencial credenciales);
	Usuario generaToken(Usuario usuario);
}
