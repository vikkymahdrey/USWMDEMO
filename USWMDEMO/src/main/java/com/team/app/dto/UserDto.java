package com.team.app.dto;

import java.io.Serializable;

public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	private String userInfo;
}
