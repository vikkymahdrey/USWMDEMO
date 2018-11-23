package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_user_info database table.
 * 
 */
@Entity
@Table(name="tbl_user_info")
@NamedQuery(name="TblUserInfo.findAll", query="SELECT t FROM TblUserInfo t")
public class TblUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String contactnumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddt;

	private String emailId;

	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pwdChangeDt;

	private String status;

	private String uname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	//bi-directional many-to-one association to TblDeviceFeedback
	@OneToMany(mappedBy="tblUserInfo")
	private List<TblDeviceFeedback> tblDeviceFeedbacks;

	//bi-directional many-to-one association to TblPaymentInfo
	@OneToMany(mappedBy="tblUserInfo")
	private List<TblPaymentInfo> tblPaymentInfos;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landmarkId")
	private Landmark landmark;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role")
	private Role roleBean;

	//bi-directional many-to-one association to UserDeviceMapping
	@OneToMany(mappedBy="tblUserInfo")
	private List<UserDeviceMapping> userDeviceMappings;

	public TblUserInfo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Date getCreateddt() {
		return this.createddt;
	}

	public void setCreateddt(Date createddt) {
		this.createddt = createddt;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPwdChangeDt() {
		return this.pwdChangeDt;
	}

	public void setPwdChangeDt(Date pwdChangeDt) {
		this.pwdChangeDt = pwdChangeDt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Date getUpdateddt() {
		return this.updateddt;
	}

	public void setUpdateddt(Date updateddt) {
		this.updateddt = updateddt;
	}

	public List<TblDeviceFeedback> getTblDeviceFeedbacks() {
		return this.tblDeviceFeedbacks;
	}

	public void setTblDeviceFeedbacks(List<TblDeviceFeedback> tblDeviceFeedbacks) {
		this.tblDeviceFeedbacks = tblDeviceFeedbacks;
	}

	public TblDeviceFeedback addTblDeviceFeedback(TblDeviceFeedback tblDeviceFeedback) {
		getTblDeviceFeedbacks().add(tblDeviceFeedback);
		tblDeviceFeedback.setTblUserInfo(this);

		return tblDeviceFeedback;
	}

	public TblDeviceFeedback removeTblDeviceFeedback(TblDeviceFeedback tblDeviceFeedback) {
		getTblDeviceFeedbacks().remove(tblDeviceFeedback);
		tblDeviceFeedback.setTblUserInfo(null);

		return tblDeviceFeedback;
	}

	public List<TblPaymentInfo> getTblPaymentInfos() {
		return this.tblPaymentInfos;
	}

	public void setTblPaymentInfos(List<TblPaymentInfo> tblPaymentInfos) {
		this.tblPaymentInfos = tblPaymentInfos;
	}

	public TblPaymentInfo addTblPaymentInfo(TblPaymentInfo tblPaymentInfo) {
		getTblPaymentInfos().add(tblPaymentInfo);
		tblPaymentInfo.setTblUserInfo(this);

		return tblPaymentInfo;
	}

	public TblPaymentInfo removeTblPaymentInfo(TblPaymentInfo tblPaymentInfo) {
		getTblPaymentInfos().remove(tblPaymentInfo);
		tblPaymentInfo.setTblUserInfo(null);

		return tblPaymentInfo;
	}

	public Landmark getLandmark() {
		return this.landmark;
	}

	public void setLandmark(Landmark landmark) {
		this.landmark = landmark;
	}

	public Role getRoleBean() {
		return this.roleBean;
	}

	public void setRoleBean(Role roleBean) {
		this.roleBean = roleBean;
	}

	public List<UserDeviceMapping> getUserDeviceMappings() {
		return this.userDeviceMappings;
	}

	public void setUserDeviceMappings(List<UserDeviceMapping> userDeviceMappings) {
		this.userDeviceMappings = userDeviceMappings;
	}

	public UserDeviceMapping addUserDeviceMapping(UserDeviceMapping userDeviceMapping) {
		getUserDeviceMappings().add(userDeviceMapping);
		userDeviceMapping.setTblUserInfo(this);

		return userDeviceMapping;
	}

	public UserDeviceMapping removeUserDeviceMapping(UserDeviceMapping userDeviceMapping) {
		getUserDeviceMappings().remove(userDeviceMapping);
		userDeviceMapping.setTblUserInfo(null);

		return userDeviceMapping;
	}

}