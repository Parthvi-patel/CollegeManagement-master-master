package com.ifanow.CollegeManagement.Models;

import org.springframework.stereotype.Component;

@Component
public class studentModel {
    int studentId;
    
    String studentName,departmentName,studentMobileNo,studentAddmissionDate;

    public studentModel(int studentId, String studentName, String departmentName, String studentMobileNo, String studentAddmissionDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.departmentName = departmentName;
        this.studentMobileNo = studentMobileNo;
        this.studentAddmissionDate = studentAddmissionDate;
    }
    public studentModel(){

    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStudentMobileNo() {
        return studentMobileNo;
    }

    public void setStudentMobileNo(String studentMobileNo) {
        this.studentMobileNo = studentMobileNo;
    }

    public String getStudentAddmissionDate() {
        return studentAddmissionDate;
    }

    public void setStudentAddmissionDate(String studentAddmissionDate) {
        this.studentAddmissionDate = studentAddmissionDate;
    }
}
