package com.nebula.api.model;

public enum ReviewStatus {
	APPROVED("A"), DECLINED("D"), PENDING("P");

	private String status;

	ReviewStatus(String status) {
		this.status = status ;
	}

	String getStatus() {
		return this.status;
	}

	void setStatus(String status) {
		this.status = status;
	}

	public static ReviewStatus fromShortStatus(String shortStatus) {

		switch (shortStatus) {
		case "A":
			return APPROVED;
		case "P":
			return PENDING;
		case "D":
			return DECLINED;

		default:
			throw new IllegalArgumentException();
		}

	}
}
