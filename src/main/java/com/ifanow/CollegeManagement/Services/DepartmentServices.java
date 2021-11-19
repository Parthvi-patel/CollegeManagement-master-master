package com.ifanow.CollegeManagement.Services;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Connection.DbConnection;
import com.ifanow.CollegeManagement.Models.DepartmentModel;
import com.ifanow.CollegeManagement.Query.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DepartmentServices {
    PreparedStatement ps;
    @Autowired
    DbConnection dbConnection = new DbConnection();
    Connection connection;
  //  @Autowired
    DepartmentModel departmentModel;
    @Autowired
    Queries queries;
    public List getDepartment() {
        List<DepartmentModel> Listifa = new ArrayList<>();
        Connection  connection = dbConnection.getconnect();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.selectQuery);
            ResultSetMetaData rsmd=rs.getMetaData();
            int i=rsmd.getColumnCount();
            DepartmentModel[] department=new DepartmentModel[i];
            for(int i1=0;i1<i;i1++)
                while (rs.next()) {
                    //System.out.println(rs.getString("person_id") +" " +rs.getString("name"));
                    department[i1] = new DepartmentModel(rs.getInt("departmentId"),rs.getString("departmentName"),rs.getString("departmentHead"),rs.getString(("teachersAll")));
                    Listifa.add(department[i1]);
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listifa;
        //  return null;
    }
    public int Insert(String deptname,String deptHead,String teachersAll)
    {
        int count=0;
        // int length=0;
        try {
            Connection con = dbConnection.getconnect();
            Statement stmt = con.createStatement();
            ps = con.prepareStatement(Queries.insertQueryDepartment);
            ps.setString(1,deptname);
            ps.setString(2,deptHead);
            ps.setString(3,teachersAll);
            count = ps.executeUpdate ();


            con.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }


    public int update(int deptid,String deptname, String deptHead, String teachersall)
    {
        int count=0;
        // int length=0;
        try {
            Connection con = dbConnection.getconnect();


            Statement stmt = con.createStatement();
            ps = con.prepareStatement(Queries.updateQueryDepartment);

            ps.setString(1,deptname);
            ps.setString(2,deptHead);
            ps.setString(3,teachersall);
            ps.setInt(4,deptid);
            count = ps.executeUpdate ();


            con.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }

    public int delete(int deptid)
    {
        int count=0;
        // int length=0;
        try {
            Connection con = dbConnection.getconnect();


            Statement stmt = con.createStatement();
            ps = con.prepareStatement(Queries.deleteQueryDepartment);


            ps.setInt(1,deptid);
            count = ps.executeUpdate ();


            con.close();
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
    public int totaldepartments() {
        Connection connection = dbConnection.getconnect();
        int i=0;
        try {
            ResultSet rs;
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(Queries.totalcountdepartment);
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
}

