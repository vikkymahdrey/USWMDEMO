package com.team.app.dto;

import java.io.Serializable;
import java.util.Date;

public class BaseResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String apiToken;
	private String baseToken;
	private Date accessTokenExpDate;
	private Date baseTokenExpDate;

	
	public Date getAccessTokenExpDate() {
		return accessTokenExpDate;
	}

	public void setAccessTokenExpDate(Date accessTokenExpDate) {
		this.accessTokenExpDate = accessTokenExpDate;
	}

	public Date getBaseTokenExpDate() {
		return baseTokenExpDate;
	}

	public void setBaseTokenExpDate(Date baseTokenExpDate) {
		this.baseTokenExpDate = baseTokenExpDate;
	}

	public void setBaseToken(String baseToken) {
		this.baseToken = baseToken;
	}
	public String getBaseToken() {
		return baseToken;
	}
	
	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
}
