package com.bridgelabz;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
/*
Ability to create a payroll service
database and have java program
connect to database
- Use the payroll_service database created in MySQL
- Download and register the jdbc driver jar to your Build
Path in Eclipse
- Alternatively use Maven or Gradle to add jdbc driver to
your project
- Check if the Driver Class is available for the java
program
- Check all the drivers registered with the jdbc driver
- Check if the database connection to payroll_service
mysql DB is established
 */
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
            Driver driverClass = driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }

    }



}
