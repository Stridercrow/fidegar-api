package com.tkinov.fidegar.domain;

public class Dato {
	private String TOKN;
	private int TYPE;
	private String MAT;
	private int QTN;
	private String ANS;
	private String CEL;
	
	public String getTOKN() {
		return TOKN;
	}
	public void setTOKN(String tOKN) {
		TOKN = tOKN;
	}
	public int getTYPE() {
		return TYPE;
	}
	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}
	public String getMAT() {
		return MAT;
	}
	public void setMAT(String mAT) {
		MAT = mAT;
	}
	public int getQTN() {
		return QTN;
	}
	public void setQTN(int qTN) {
		QTN = qTN;
	}
	public String getANS() {
		return ANS;
	}
	public void setANS(String aNS) {
		ANS = aNS;
	}
	public String getCEL() {
		return CEL;
	}
	public void setCEL(String cEL) {
		CEL = cEL;
	}
	
	@Override
	public String toString() {
		return "Dato [TOKN=" + TOKN + ", TYPE=" + TYPE + ", MAT=" + MAT + ", QTN=" + QTN + ", ANS=" + ANS + ", CEL="
				+ CEL + "]";
	}
	
}
