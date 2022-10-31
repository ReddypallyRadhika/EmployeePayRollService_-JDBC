package com.bridgelabz;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*UC3
Ability to update the salary i.e.
the base pay for Employee
Terisa to 3000000.00 and sync it
with Database
- Update the employee payroll in the database
- Update the Employee Payroll Object with the Updated
Salary
- Compare Employee Payroll Object with DB to pass the
Junit Test.
- Any Error throw Custom Exceptions
 */
public class EmployeePayrollDBService {

    private PreparedStatement employeePayrollDataStatement;
    private static EmployeePayrollDBService employeePayrollDBService;

    public EmployeePayrollDBService() {
    }

    // Method -- class method -- one instance will be there only... if you call it one or more it will be same.
    public static EmployeePayrollDBService getInstance(){
        if (employeePayrollDBService == null)
            employeePayrollDBService = new EmployeePayrollDBService();
        return employeePayrollDBService;
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://127.0.0.7:3306/payroll_service?useSSL=false";
        // localhost = 127.0.0.1
        String userName = "root";
        String password = "root";
        Connection connection;

        System.out.println("Connecting to database:"+jdbcUrl);
        connection = DriverManager.getConnection(jdbcUrl,userName,password);
        System.out.println("================================");
        System.out.println("Connection is successfull"+connection);

        return connection;
    }

   public List<EmployeePayrollData> readData(){

        String sqlStatement = "SELECT id,empName,basic_pay,startdate from employee_payroll;";
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("empName");
                double basicSalary = resultSet.getDouble("basic_pay");
                LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                employeePayrollDataList.add(new EmployeePayrollData(id,name,basicSalary,startDate));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollDataList;

    }


    public int updateEmployeeSalary(String name, double salary){

        return this.updateEmployeeDataUsingStatement(name, salary);
    }


    //SQL injection
    private int updateEmployeeDataUsingStatement(String name, double salary){
        String sqlStatement = String.format("Update employee_payroll set basic_pay = %.2f where empName='%s';",salary,name);


        try(Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sqlStatement);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


    private void prepareStatementForEmployeeData(){
        try {
            Connection connection = this.getConnection();
            String sqlStatement = "Select * from employee_payroll where empName = ?;";
            employeePayrollDataStatement = connection.prepareStatement(sqlStatement);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet){
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();

        try {
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("empName");
                double basicSalary = resultSet.getDouble("basic_pay");
                LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                employeePayrollDataList.add(new EmployeePayrollData(id,name,basicSalary,startDate));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollDataList;
    }


    public List<EmployeePayrollData> getEmployeePayrollData(String name){
        List<EmployeePayrollData> employeePayrollDataList = null;
        if(this.employeePayrollDataStatement == null)
            this.prepareStatementForEmployeeData();

        try{
            employeePayrollDataStatement.setString(1,name);
            ResultSet resultSet = employeePayrollDataStatement.executeQuery();
            employeePayrollDataList = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return  employeePayrollDataList;
    }


}
