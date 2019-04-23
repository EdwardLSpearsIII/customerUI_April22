package com.example.admin1.firstlogin;

public class User {

    String userId;
    String userFname;
    String userLname;
    String userEmail;
    String userUname;
    String userPassword;
    String userSchool;
    //String userPhoto;

    public User () {

    }

    public User(String userId, String userFname, String userLname, String userEmail, String userUname, String userPassword, String userSchool) {
        this.userId = userId;
        this.userFname = userFname;
        this.userLname = userLname;
        this.userEmail = userEmail;
        this.userUname = userUname;
        this.userPassword = userPassword;
        this.userSchool = userSchool;
        //this.userPhoto = userPhoto;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserFname() {
        return userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserUname() {
        return userUname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserSchool() {
        return userSchool;
    }

    //public String getUserPhoto() {
     //   return userPhoto;
   // }
}
