package com.tkinov.fidegar.dao;

public interface PreguntaDAO {
	boolean verificaRespuesta(int preguntaId, String respuesta, int matriculaId);
}
