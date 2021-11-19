package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.AttendenceInsertModel;
import com.ifanow.CollegeManagement.Models.AttendenceUpdateModel;
import com.ifanow.CollegeManagement.Models.DepartmentModel;
import com.ifanow.CollegeManagement.Services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {
    Gson gson=new Gson();
    @Autowired
    DepartmentServices departmentServices;
    @CrossOrigin("localhost:4200")
    @RequestMapping(path = "/getDepartmentCount", method = RequestMethod.GET)
    @ResponseBody
    public int getdepartmentcount()
    {
        int totaldepartment= departmentServices.totaldepartments();
        return totaldepartment;
    }

    @CrossOrigin("localhost:4200")
    @RequestMapping(path = "/getDepartment", method = RequestMethod.GET)
    @ResponseBody

    public String getdepartmentgson() {

        List<String> listdepartment;
        DepartmentServices deptservice = new DepartmentServices();
        listdepartment = departmentServices.getDepartment();
        String gsonlistDepartment=departmentServices.convertListtoJson(listdepartment);
        return gsonlistDepartment;

    }
    @CrossOrigin("localhost:4200")
    @RequestMapping(path = "/insertDepartment", method = RequestMethod.POST)
    @ResponseBody
    public String  DepartmentInsert(@RequestBody String departmentmodel) throws IOException {
        DepartmentModel deptmodel=gson.fromJson(departmentmodel, DepartmentModel.class);

        String deptname = deptmodel.getDepartmentName();
        String depthead=deptmodel.getDepartmentHead();
        String teachersall=deptmodel.getTeachersAll();
        departmentServices.Insert(deptname,depthead,teachersall);
        String count =gson.toJson("Data Successfully Inserted..No of Rows="+departmentServices.Insert(deptname,depthead,teachersall));
        return count;
    }
    @CrossOrigin("localhost:4200")
    @RequestMapping(path = "/updateDepartment", method = RequestMethod.PUT)
    @ResponseBody
    public String updateDepartment(@RequestBody String updatedepartment) throws IOException
    {
        int updatedRow=0;
        Gson gson=new Gson();
        DepartmentModel deptmodel=gson.fromJson(updatedepartment,DepartmentModel.class);
        int deptId=deptmodel.getDepartmentId();
        String deptname=deptmodel.getDepartmentName();
        String deptHead=deptmodel.getDepartmentHead();
        String teachersall=deptmodel.getTeachersAll();

        updatedRow=departmentServices.update(deptId,deptname,deptHead,teachersall);
        return gson.toJson("Successfully..Updated Rows="+String.valueOf(updatedRow));

    }
    @CrossOrigin("localhost:4200")
    @RequestMapping(path = "/deleteDepartment", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteDepartment(@RequestParam("deptId") int deptId )throws IOException
    {
        int deptIdstring = deptId;
        int count = departmentServices.delete(deptId);
        return count;
    }


}
