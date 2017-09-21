package com.tkinov.fidegar.domain;

import java.util.UUID;

public class Token {	
	private int tokenId;	
	private UUID token;
	
	public int getTokenId() {
		return tokenId;
	}
	public void setTokenId(int idToken) {
		this.tokenId = idToken;
	}
	public UUID getToken() {
		return token;
	}
	public void setToken(UUID token) {
		this.token = token;
	}
}