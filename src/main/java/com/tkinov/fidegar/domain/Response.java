package com.tkinov.fidegar.domain;

public class Response {
	int CODE;
	String DESC;
	String TOKN;
	
	public Response(int CODE, String DESC, String TOKN) {
		super();
		this.CODE = CODE;
		this.DESC = DESC;
		this.TOKN = TOKN;
	}
	
	public int getCODE() {
		return CODE;
	}
	public void setCODE(int CODE) {
		this.CODE = CODE;
	}
	public String getDESC() {
		return DESC;
	}
	public void setDESC(String DESC) {
		this.DESC = DESC;
	}
	public String getTOKN() {
		return TOKN;
	}
	public void setTOKN(String TOKN) {
		this.TOKN = TOKN;
	}
	
	
}
