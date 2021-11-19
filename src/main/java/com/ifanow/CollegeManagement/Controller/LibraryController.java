package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.LibraryModel;
import com.ifanow.CollegeManagement.Services.LibraryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api")

public class LibraryController {
    @Autowired
    LibraryServices libraryServices;


    @CrossOrigin("http://localhost:4200")
    @PostMapping("/storeLibraryDetails")
    public String savLibraryDetails(@RequestBody String libraryInsertDetail) throws IOException {
        System.out.println(libraryInsertDetail);
        Gson gson = new Gson();
        LibraryModel libraryModel = gson.fromJson(libraryInsertDetail, LibraryModel.class);
        int srNo = libraryModel.getStudentId();
        int numberOfBook = libraryModel.getNumberOfBook();
        String studentName = libraryModel.getStudentName();
        String bookName = libraryModel.getBookName();
        String issueDate = libraryModel.getIssueDate();
        String returnDate = libraryModel.getReturnDate();
        String librarian = libraryModel.getLibrarian();
        String count = gson.toJson("Data Successfully Inserted..No of Rows=" + libraryServices.saveLibraryDetails(srNo, studentName, bookName, issueDate, returnDate, numberOfBook, librarian));
        return count;
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/showLibraryDetails")
    public String showLibrayDetails()
    {
        Gson libraryModeljson=new Gson();
        String libraryjsonObj =libraryModeljson.toJson(libraryServices.showAllLibraryDetail());
        System.out.println(libraryjsonObj);
        return libraryjsonObj;
    }
    @CrossOrigin("http://localhost:4200")
    @PutMapping("/updateLibraryDetails")
    public String updateLibraryDetails(@RequestBody String libraryUpdateDetail){
        int updateRow =0;
        System.out.println(libraryUpdateDetail);
        Gson gson = new Gson();
        LibraryModel libraryModel = gson.fromJson(libraryUpdateDetail, LibraryModel.class);
        int srNo = libraryModel.getSrNo();
        int numberOfBook = libraryModel.getNumberOfBook();
        String studentName = libraryModel.getStudentName();
        String bookName = libraryModel.getBookName();
        String issueDate = libraryModel.getIssueDate();
        String returnDate = libraryModel.getReturnDate();
        String librarian = libraryModel.getLibrarian();
        updateRow =libraryServices.updateLibraryDetail( srNo,  studentName,  bookName,  issueDate,  returnDate,  numberOfBook,  librarian);
        return gson.toJson("Successfully..Updated Rows="+String.valueOf(updateRow));
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/deleteLibraryDetails")
    public int deleteLibraryDetail(@RequestParam int srNo) throws IOException {
        //int studentId=3;
        int deletedRow=0;
        deletedRow=libraryServices.deleteLibraryDetail(srNo);
        return deletedRow;
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/totalLibraryDetails")
    public int totalLibrrayDetail(){
        int count = 0;
        count = libraryServices.LibraryDetails();
        return count;
    }
}

