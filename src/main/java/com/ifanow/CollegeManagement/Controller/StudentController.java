package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Services.studentServices;
import com.ifanow.CollegeManagement.Models.studentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/api")


public class StudentController {
    @Autowired
    studentServices studentservices;
    @Autowired
    studentServices sqloperation;
    @Autowired
            studentModel[] student;
    List<studentModel> studentmodelsList=new ArrayList<>();




    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/showStudentDetails")

    public String getstudentDetails() {

        Gson studentmodeljson=new Gson();
        String studentjsonObj=studentmodeljson.toJson(sqloperation.SelectStudent());
        System.out.println(studentjsonObj);

        studentjsonObj= Base64.getEncoder().encodeToString(studentjsonObj.getBytes());


        return studentjsonObj;

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/inserstudentDetails",method = RequestMethod.POST)
    public  String insertStudentDetails(@RequestBody String StudentDetailsInsert ) throws IOException {
        System.out.println(StudentDetailsInsert);
        Gson gson = new Gson();
        studentModel studentmodel=gson.fromJson(StudentDetailsInsert,studentModel.class);
        int studentId=studentmodel.getStudentId();
        String studentName=studentmodel.getStudentName();
        String departmentName=studentmodel.getDepartmentName();
        String studentMobileNo=studentmodel.getStudentMobileNo();
        String studentAddmissionDate=studentmodel.getStudentAddmissionDate();

        String counter=gson.toJson(studentservices.InsertStudent(studentId,studentName,departmentName,studentMobileNo,studentAddmissionDate));
        return counter;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/InsertBatch")
    public  String batchInserStudentDetails(@RequestBody  studentModel[]  studentinsert) throws IOException {

        Gson gson=new Gson();
       int []rows=sqloperation.batchInsertStudent(studentinsert);
        return gson.toJson("rows="+rows);

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateStudentDetails")

    public String updateStudentdetails(@RequestBody String studentupdatedetails) {
        int count = 0;
        Gson gson=new Gson();

        studentModel studentmodel=gson.fromJson(studentupdatedetails,studentModel.class);
        int studentId=studentmodel.getStudentId();
        String studentName=studentmodel.getStudentName();
        String departmentName=studentmodel.getDepartmentName();
        String studentMobileNo=studentmodel.getStudentMobileNo();
        String studentAddmissionDate=studentmodel.getStudentAddmissionDate();
        count=studentservices.UpdateStudent(studentId,studentName,departmentName,studentMobileNo,studentAddmissionDate);


        return  gson.toJson("Updated sucessfully.."+String.valueOf(count));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/BatchUpdateStudent")
    public String BatchUpdate(@RequestBody studentModel[] update){
        Gson gson=new Gson();
        int []rows=sqloperation.batchupdate(update);
        return gson.toJson("rows="+rows);

    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/deleteStudentDetails",method = RequestMethod.DELETE)
    public int deleteStudent(@RequestParam int studentId) throws Exception{

        int count=0;

        count=sqloperation.DeleteStudent(studentId);

        return count;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/totalStudentDetails")
    public int totalDetails(){
        int counter=0;
        counter=sqloperation.counttotalDetails();
        return counter;
    }


}
