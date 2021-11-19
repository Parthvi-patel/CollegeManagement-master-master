package com.ifanow.CollegeManagement.Services;
import com.ifanow.CollegeManagement.Models.LoginModel;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Connection.DbConnection;
import com.ifanow.CollegeManagement.Models.LoginModel;
import com.ifanow.CollegeManagement.Query.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component

public class LoginServices {
    PreparedStatement ps = null;

    @Autowired
    DbConnection dbconnection;
    Connection connection;
    public List getLogin() {
        List<LoginModel> Listifa = new ArrayList<>();
        connection = dbconnection.getconnect();
        try {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(Queries.selectQueryLogin);
            ResultSetMetaData rsmd=rs.getMetaData();
            int i=rsmd.getColumnCount();
            LoginModel[] login=new LoginModel[i];
            for(int i1=0;i1<i;i1++)
                while (rs.next()) {
                    //System.out.println(rs.getString("person_id") +" " +rs.getString("name"));
                    login[i1] = new LoginModel(rs.getInt("userId"),rs.getString("userName"),rs.getString("password"));
                    Listifa.add(login[i1]);
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listifa;
        //  return null;
    }
    public int Insert(String userName,String email,String password)
    {
        int count=0;
        try {
            connection = dbconnection.getconnect();
            Statement stmt = connection.createStatement();
            ps = connection.prepareStatement(Queries.insertQueryRegister);

            ps.setString(1,userName);
            ps.setString(2,email);
            ps.setString(3,password);
            count = ps.executeUpdate ();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }


    public int update(int userId,String userName, String password)
    {
        int count=0;
        try {
            connection = dbconnection.getconnect();
            Statement stmt = connection.createStatement();
            ps = connection.prepareStatement(Queries.updateQueryLogin);
            ps.setString(1,userName);
            ps.setString(2,password);
            ps.setInt(3,userId);
            count = ps.executeUpdate ();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }

    public int delete(int userId)
    {
        int count=0;
        // int length=0;
        try {
            connection = dbconnection.getconnect();
            Statement stmt = connection.createStatement();
            ps = connection.prepareStatement(Queries.deleteQueryLogin);
            ps.setInt(1,userId);
            count= ps.executeUpdate();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }

    public String convertListtoJson(List list)
    {
        Gson gsdepartment=new Gson();
        String St_ObjTojson11 =gsdepartment.toJson(list);
        return  St_ObjTojson11;
    }
    public int totallogin() {
        connection = dbconnection.getconnect();
        int i=0;
        try {
            ResultSet rs;
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(Queries.totalcountLogin);
            if(rs.next()){
                i = rs.getInt("total");
            }
            return i;

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
        return i;
    }

    public String finduser(String email, String password)
    {
        connection = dbconnection.getconnect();
        String user="";
        int find=0;
        try {
            ResultSet rs;
            Statement stmt = connection.createStatement();
            ps=connection.prepareStatement(Queries.findRegisterUser);
            ps.setString(1,email);
            ps.setString(2,password);
            rs =ps.executeQuery();
            if(rs.next())
                user="found";
            else
                user="not found";
            return user;
        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
        return user;
    }

}
