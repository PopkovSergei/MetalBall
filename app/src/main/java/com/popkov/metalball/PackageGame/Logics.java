package com.popkov.metalball.PackageGame;


import com.popkov.metalball.PackageDB.DBConnection;

public class Logics {

    int  [][] mapMas;
    DBConnection dbConnection = new DBConnection();

    public Logics(){
        mapMas = dbConnection.GetMap(1);
    }





}
