package com.ifanow.CollegeManagement.Models;

import org.springframework.stereotype.Component;

//@Component
public class DepartmentModel {
    int departmentId;
    String departmentName;
    String departmentHead;
    String teachersAll;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }

    public String getTeachersAll() {
        return teachersAll;
    }

    public void setTeachersAll(String teachersAll) {
        this.teachersAll = teachersAll;
    }

    public DepartmentModel(int departmentId, String departmentName, String departmentHead, String teachersAll) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
        this.teachersAll = teachersAll;
    }
}

