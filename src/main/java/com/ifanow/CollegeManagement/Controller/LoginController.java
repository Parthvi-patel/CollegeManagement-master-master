package com.ifanow.CollegeManagement.Controller;

import com.ifanow.CollegeManagement.Models.LoginModel;
import com.ifanow.CollegeManagement.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    LoginServices service;
    @RequestMapping(path = "/getLoginCount", method = RequestMethod.GET)
    @ResponseBody
    public int getRegistercount()
    {
        int totaldepartment= service.totallogin();
        return totaldepartment;
    }


    @RequestMapping(path = "/getLogin", method = RequestMethod.GET)
    @ResponseBody

    public String getLogingson() {

        List<String> listLogin;
        LoginServices loginservice = new LoginServices();
        List<LoginModel> ListDao = new ArrayList<>();
        listLogin = loginservice.getLogin();
        String gsonlistDepartment=service.convertListtoJson(listLogin);
        return gsonlistDepartment;
    }



    @RequestMapping(path = "/insertLogin", method = RequestMethod.POST)
    @ResponseBody
    public int LoginInsert(@RequestParam String userName,@RequestParam String email,@RequestParam String password) throws IOException {
        String  usernameString=userName;
        String  emailstring=email;
        String passwordstring = password;
        int count = service.Insert(usernameString,emailstring,passwordstring);
        return count;
    }

    @RequestMapping(path = "/updateLogin", method = RequestMethod.PUT)
    @ResponseBody
    public int updateDepartment(@RequestParam int userId,@RequestParam String userName,@RequestParam String password) throws IOException
    {
        int userIdString = userId;
        String username = userName;
        String passwprd = password;
        int count = service.update(userId,userName,password);
        return count;
    }
    @RequestMapping(path = "/deleteLogin", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteDepartment(@RequestParam int userId )throws IOException
    {
        int userIdString = userId;
        int count = service.delete(userIdString);
        return count;
    }
    @RequestMapping(path = "/findregisteredUser", method = RequestMethod.GET)
    @ResponseBody
    public String  findregisteredUser(@RequestParam String email, @RequestParam String password)throws IOException
    {
        String emailstring=email;
        String passwordString=password;
        String  find=service.finduser(emailstring,passwordString);
        return find;
    }
}
