package com.ifanow.CollegeManagement.Models;
public class AttendenceUpdateModel {
	String studentName,department,loginTime,logoutTime;
	Integer studentId,srNo,attendencePercentage;

	public AttendenceUpdateModel(Integer sId, Integer srNo, String studentName, String department, String loginTime, String logoutTime,Integer attendencePercentage) {
		this.studentId = sId;
		this.srNo = srNo;
		this.studentName = studentName;
		this.department = department;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.attendencePercentage=attendencePercentage;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getAttendencePercentage() {
		return attendencePercentage;
	}

	public void setAttendencePercentage(Integer attendencePercentage) {
		this.attendencePercentage = attendencePercentage;
	}


	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
}

