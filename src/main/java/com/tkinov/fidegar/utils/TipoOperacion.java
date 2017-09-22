package com.tkinov.fidegar.utils;

public enum TipoOperacion {
	NIP(1, "nip"),
	CELULAR(2, "numero-celular"),
	PREGUNTA(3, "pregunta");
	
	private int id;
	private String desc;
	
	TipoOperacion(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public int id() {
		return this.id;
	}
	
	public String desc() {
		return this.desc;
	}
	
	public static TipoOperacion valueOf(int statusCode) {
		for (TipoOperacion status : values()) {
			if (status.id == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
}
