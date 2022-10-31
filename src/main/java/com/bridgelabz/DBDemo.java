package com.bridgelabz;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class DBDemo {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
                        // localhost = 127.0.0.1
        String userName = "root";
        String password = "root";
        Connection con;

        // Driver loading
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e){
            throw new IllegalStateException("Cannot find the driver", e);
        }

        listDrivers();


        // Create a connection

        try {
            System.out.println("Connecting to database:"+jdbcUrl);
            con = DriverManager.getConnection(jdbcUrl,userName,password);
            System.out.println("================================");
            System.out.println("Connection is successfull"+con);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void listDrivers(){
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }

    }



}
