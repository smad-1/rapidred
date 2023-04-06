package com.example.demo1;

public class Donors {
    private String firstName;
    private String lastName;
    private String blood;
    private String location;
    private String phone;
    private String eligible;

    public Donors(String firstName, String lastName, String blood, String location, String phone,String eligible) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.blood=blood;
        this.location=location;
        this.phone=phone;
        this.eligible=eligible;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getBlood(){
        return blood;
    }
    public String getLocation(){
        return location;
    }
    public String getPhone(){
        return phone;
    }
    public String getEligible(){
        return eligible;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setBlood(String blood){
        this.blood=blood;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setEligible(String eligible){
        this.eligible=eligible;
    }
}
