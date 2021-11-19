package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.AttendenceInsertModel;
import com.ifanow.CollegeManagement.Models.AttendenceModel;
import com.ifanow.CollegeManagement.Models.AttendenceUpdateModel;
import com.ifanow.CollegeManagement.Services.AttendenceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AttendenceController {
    Gson gson=new Gson();
    @Autowired
    AttendenceServices attendence;
    @PostMapping(value = "/attendence/insert")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    public String AttendenceInsert(@RequestBody String attendModel) throws IOException {
        System.out.println(attendModel);
        //Declaration

        AttendenceInsertModel attendenceinsertmodel=gson.fromJson(attendModel, AttendenceInsertModel.class);
        int sid = attendenceinsertmodel.getStudentId();
        String sName = attendenceinsertmodel.getStudentName();
        String department = attendenceinsertmodel.getDepartment();
        String loginTime = attendenceinsertmodel.getLoginTime();
        String logoutTime = attendenceinsertmodel.getLogoutTime();
        //System.out.println();
        int attendencePercentage=(int)attendence.attendencePercentage(sid);
        //Constant Intiallization
        String count =gson.toJson("Data Successfully Inserted..No of Rows="+attendence.insertAttendence(sid,sName,department,loginTime,logoutTime,attendencePercentage));
        return count;
    }
    //Select Statement
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/attendence")
    public String attendenceHome() throws IOException {
        //Declaration
        Gson gson=new Gson();
        String listModel = gson.toJson(attendence.selectAttendence());
        return listModel;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/attendence/delete")
    public String attendenceDelete(@RequestParam("srNo") int srNo) throws IOException {
        String  deletedRow;
        deletedRow= gson.toJson("Successfully Deleted..No of Rows="+attendence.Delete(srNo));
        return deletedRow;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path = "/attendence/update")
    public String attendenceUpdate(@RequestBody String updateData) throws IOException {
        //Constant Intiallization
        int updatedRow=0;
        Gson gson=new Gson();
        AttendenceUpdateModel attendenceupdatemodel=gson.fromJson(updateData,AttendenceUpdateModel.class);
        int sId=attendenceupdatemodel.getStudentId();
        int attendencePercentage= (int)attendence.attendencePercentage(sId);
        int srNo=attendenceupdatemodel.getSrNo();
        String studentName=attendenceupdatemodel.getStudentName();
        String department = attendenceupdatemodel.getDepartment();
        String loginTime=attendenceupdatemodel.getLoginTime();
        String logoutTime=attendenceupdatemodel.getLogoutTime();
        updatedRow=attendence.attendenceUpdate(srNo,studentName,department,loginTime,logoutTime,attendencePercentage);
        return gson.toJson("Successfully..Updated Rows="+String.valueOf(updatedRow));


    }

    @PostMapping(path = "/attendence/insertBatch")
    public void setAttendenceBatch(@RequestBody AttendenceInsertModel[] attendenceBatch)
    {
        System.out.println(attendenceBatch);
    }
}
