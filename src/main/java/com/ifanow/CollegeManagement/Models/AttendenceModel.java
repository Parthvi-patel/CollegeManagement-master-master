package com.ifanow.CollegeManagement.Models;

import org.springframework.stereotype.Component;


public class AttendenceModel {
    int studentId,srNo;
    String studentName;
    String department;
    String loginTime;
    String logoutTime;
    int attendencePercentage;

    public AttendenceModel(int srNo, int sid, String sName, String department, String loginTime, String logoutTime, int attendencePercentage) {
        this.srNo=srNo;
        this.studentId = sid;
        this.studentName = sName;
        this.department = department;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.attendencePercentage = attendencePercentage;
    }


}

