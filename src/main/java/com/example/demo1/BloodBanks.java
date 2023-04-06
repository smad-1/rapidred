package com.example.demo1;

public class BloodBanks {
    private String name;
    private String location;
    private String phone;
    public BloodBanks(String name, String location, String phone)
    {
        this.name=name;
        this.location=location;
        this.phone=phone;
    }
    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public String getPhone(){
        return phone;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
}
