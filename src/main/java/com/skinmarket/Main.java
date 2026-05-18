package com.skinmarket;

import com.skinmarket.util.DBConnection;
import com.skinmarket.util.DatabaseMigrator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            DBConnection.DbConnect();
            DatabaseMigrator.migrate();
        }
        catch (Exception e){
            System.out.println("Db not connected or migration was broken" + e.getMessage());
        }

    }
}