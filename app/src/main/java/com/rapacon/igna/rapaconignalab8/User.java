package com.rapacon.igna.rapaconignalab8;

public class User {

    private String ifullname, iage, igender;

    public String getFullname() {

        return ifullname;
    }

    public void setFullname(String fullname) {

        this.ifullname = fullname;
    }

    public String getAge() {

        return iage;
    }

    public void setAge(String age) {

        this.iage = age;
    }

    public String getGender() {

        return igender;
    }

    public void setGender(String gender) {

        this.igender = gender;
    }

    public User(String fullname, String age, String gender) {
        this.ifullname = fullname;
        this.iage = age;
        this.igender = gender;
    }

    public User() {

    }
}