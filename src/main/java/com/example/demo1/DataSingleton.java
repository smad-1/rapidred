package com.example.demo1;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    public static String userName="";
    public static String place_chosen="null";
   // public static String lastName="";
    private DataSingleton(){}
    public static DataSingleton getInstance(){
        return instance;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPlace_chosen(){
        return place_chosen;
    }
    public void setPlace_chosen(String place_chosen){
        this.place_chosen = place_chosen;
    }
}
