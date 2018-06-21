/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Fatima Peygumbari 
 * STN# 500503954
 */
public class DataBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ResultSet rs = null;
        String url = "jdbc:sqlite:/Users/Fatima/fatima.db";
        Connection c;
        Statement stmt;
        PreparedStatement myStmt = null;
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("Class Not Found Exception:");
            System.err.println(e.getMessage());
        }
//to Break The loop press 0
        System.out.println("Welcome to the Shares Database Application: \n\n"
                + "Select: 0 to Exit \n" 
                + "Select: 1 To Display Customer Table Information\n"
                + "Select: 2 To Display Customer Portfolio Information\n"
                + "Select: 3 To Add New Customer Inforamtion \n"
                + "Select: 4 To List of All Quotes\n"
                + "Select: 5 To Display Quote Information\n"
                + "Select: 6 For List Purchared or Sold shares\n"
                + "Select: 7 To View Customer Feedback\n"
                + "Select: 8 To Add New Shares to Portfolio\n"
                + "Select: 9 To Update the Customer information\n"
                + "Select: 10 For Best or Worst Customer Rating\n"
                + "Select: 11 To Display the Employee Number ");

        Scanner scan = new Scanner(System.in);

        int num = scan.nextInt();

        int select = num;

        loop:
        while (true) {


            switch (select) {
                case 0:
                    break loop;

                case 1:
                    try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        ResultSet uprs = stmt.executeQuery("SELECT * FROM CUSTOMER");
                        System.out.println("Customer Table:");
                        System.out.println("C_id");
                        while (uprs.next()) {
                            String Name = uprs.getString("Name");
                            int C_id = uprs.getInt("C_id");
                            String DOB = uprs.getString("DOB");
                            String Address = uprs.getString("Address");
                            String PhoneNo = uprs.getString("PhoneNo");
                            System.out.print(C_id + "   " + Name + " " + DOB + "    ");
                            System.out.println("" + Address + "  " + PhoneNo + "\n");

                        }

                        uprs.close();
                        stmt.close();
                        c.close();

                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                    break;
                case 2:
                    try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        System.out.println("Please Enter the Customer ID Number:");
                        Scanner s = new Scanner(System.in);
                        //For string
                        String i = s.nextLine();
                        //System.out.println(C_id);

                        System.out.println("Portfolio Table for Customer ID:" + i);
                        String c_id = i;
                        ResultSet uprs = stmt.executeQuery("select NumberOFShares, CompanyName, Symbol from Portfolio where C_id=" + c_id);

                        while (uprs.next()) {
                            String CompanyName = uprs.getString("CompanyName");
                            String Symbol = uprs.getString("Symbol");
                            String NumberOFShares = uprs.getString("NumberOFShares");

                            System.out.print(CompanyName + " " + Symbol + " ");
                            System.out.println(" " + NumberOFShares);

                        }

                        uprs.close();
                        stmt.close();
                        c.close();

                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                    break;
                case 3:
                    try {

                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();

                        System.out.println("Please Enter the following Customer Information: \nFirst and Last Name");
                        Scanner s = new Scanner(System.in);
                        //For string
                        String Name = s.nextLine();

                        System.out.println("\nDate of Birth: YYYY-MM-DD");
                        String DOB = s.nextLine();

                        System.out.println("\nFull Address:");
                        String Address = s.nextLine();

                        System.out.println("\nPhone number:\n");
                        String PhoneNo = s.nextLine();

                        System.out.println("The following Customer information was added to Customer Table:");
                        System.out.println(Name);
                        System.out.println(DOB);
                        System.out.println(Address);
                        System.out.println(PhoneNo);

                        String sql = "insert into Customer" + " (Name,DOB,Address,PhoneNo)" + "values(?,?,?,?)";
                        myStmt = c.prepareStatement(sql);
                        myStmt.setString(1, Name);
                        myStmt.setString(2, DOB);
                        myStmt.setString(3, Address);
                        myStmt.setString(4, PhoneNo);
                        myStmt.executeUpdate();

                        myStmt.close();
                        c.close();

                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("Vendor: " + ex.getErrorCode());

                    }
                    break;
                case 4:

                    try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        ResultSet uprs = stmt.executeQuery("SELECT * FROM Quote");
                        System.out.println("Quote Table:");
                        System.out.println("Q_id " + "  " + "C_id" + "     " + "Symbol_id" + "    " + "Symbol ");
                        while (uprs.next()) {
                            int Q_id = uprs.getInt("Q_id");
                            int C_id = uprs.getInt("C_id");
                            String Symbol_id = uprs.getString("Symbol_id");
                            String Symbol = uprs.getString("Symbol");
                            String Date = uprs.getString("Date");
                            String HIGH = uprs.getString("HIGH");
                            String LOW = uprs.getString("LOW");
                            String CurrentPrice = uprs.getString("CurrentPrice");
                            System.out.print(Q_id + "      " + C_id + "        " + Symbol_id + "      ");
                            System.out.println("    " + Symbol + "  " + Date + " " + HIGH + " " + LOW + " " + CurrentPrice + " " + "\n");

                        }

                        uprs.close();
                        stmt.close();
                        c.close();

                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                    break;

                case 5:
                    try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        System.out.println("For Quote Please Enter the Customer ID:");
                        Scanner s = new Scanner(System.in);
                        //For string
                        String i = s.nextLine();

                        System.out.println("Quote Table for Customer ID:" + i);
                        String c_id = i;
                        ResultSet uprs = stmt.executeQuery("select Symbol, HIGH, LOW, CurrentPrice from Quote where C_id=" + c_id);

                        while (uprs.next()) {
                            String Symbol = uprs.getString("Symbol");
                            String HIGH = uprs.getString("HIGH");
                            String LOW = uprs.getString("LOW");
                            String CurrentPrice = uprs.getString("CurrentPrice");
                            System.out.println("Symbol       HIGH    LOW      Current Price");
                            System.out.print(Symbol + "         " + "$" + HIGH + "   " + "$" + LOW + "  ");
                            System.out.println("        " + "$" + CurrentPrice);

                        }

                        uprs.close();
                        stmt.close();
                        c.close();

                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                    break;

                case 6:

                    try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        System.out.println("Select: 1 for Sellers list \n        2 for Buyers list or \n        3 for Complete info in both tables");
                        Scanner a = new Scanner(System.in);
                        int i = a.nextInt();
                        if (i == 1) {
                            ResultSet uprs = stmt.executeQuery("SELECT C_id, CompanyName from Seller");
                            System.out.println("List of Sellers:");
                            while (uprs.next()) {
                                int C_id = uprs.getInt("C_id");
                                String CompanyName = uprs.getString("CompanyName");
                                System.out.print(C_id + "        ");
                                System.out.println("    " + CompanyName + "  " + "\n");
                            }
                            uprs.close();
                            stmt.close();
                            c.close();
                        } else if (i == 2) {
                            ResultSet uprs = stmt.executeQuery("SELECT C_id, CompanyName FROM Buyer");
                            System.out.println("List of Buyers:");
                            while (uprs.next()) {
                                String C_id = uprs.getString("C_id");
                                String CompanyName = uprs.getString("CompanyName");
                                System.out.print(C_id + "   ");
                                System.out.println("    " + CompanyName + "  " + "\n");
                            }
                            uprs.close();
                            stmt.close();
                            c.close();
                        } else if (i == 3) {
                            ResultSet uprs = stmt.executeQuery("SELECT * FROM Buyer");
                            System.out.println("Complete Buyers Table:");
                            while (uprs.next()) {
                                String Cid = uprs.getString("Cid");
                                String CompanyName = uprs.getString("CompanyName");
                                String Symbol = uprs.getString("Symbol");
                                String C_id = uprs.getString("C_id");
                                System.out.print(Cid + "   " + CompanyName + " " + Symbol + " ");
                                System.out.println(" " + C_id);
                            }
                            ResultSet uprs1 = stmt.executeQuery("SELECT * FROM Seller");
                            System.out.println("\nComplete Sellers Table:");
                            while (uprs.next()) {
                                int Cid1 = uprs.getInt("Cid");
                                String CompanyName1 = uprs.getString("CompanyName");
                                String Symbol1 = uprs.getString("Symbol");
                                int C_id1 = uprs.getInt("C_id");
                                System.out.print(Cid1 + "      " + CompanyName1 + "  " + Symbol1 + " ");
                                System.out.println("" + C_id1);

                            }
                            uprs.close();
                            uprs1.close();
                            stmt.close();
                            c.close();

                        } else {

                            System.out.println("This Selection is not available please try again");
                        }

                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                    break;

                case 7:
                    try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        System.out.println("Please Enter the Customer Name to Retrieve Feedback Information:");
                        System.out.println("Enter First Name:");
                        Scanner fn = new Scanner(System.in);
                        String f = fn.nextLine();
                        System.out.println("Enter Last Name:");
                        Scanner ln = new Scanner(System.in);
                        String l = ln.nextLine();
                        
                        String name = f + " " + l;

                        System.out.println("Retrieving Feedback from Customer:" + name);  
                        ResultSet uprs = stmt.executeQuery("select Comment from Feedback where Name='" + name+"'");

                        if (uprs.next()){
                            
                            String Comment = uprs.getString("Comment");
                            System.out.println("Customer Feedback:");
                            System.out.println("" + Comment+"\n");
                            
                        
                        }else{
                            System.out.println("This Name is not in the list please try again");
                            break;
                            
                        }
                        uprs.close();
                        stmt.close();
                        c.close();
                        
                     
                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                    break;

                case 8:
                    try{
                    c = DriverManager.getConnection(url);
                    stmt = c.createStatement();
        
                        System.out.println("Please Enter the Customer ID:");
                        Scanner sa = new Scanner(System.in);  
                        int C_id = sa.nextInt();
                      
                        System.out.println("Please Enter the following Share Information:");  
                        System.out.println("\nCompany Name:");
                        Scanner s = new Scanner(System.in);               
                        String CompanyName = s.nextLine();
                        System.out.println("Company Symbol:");
                        String Symbol = s.nextLine();
                        System.out.println("Number OF Shares:");
                        int NumberOFShares= s.nextInt();

                        System.out.println("The following Shares information was added to Portfolio Table:");
                        System.out.println(CompanyName);
                        System.out.println(Symbol);
                        System.out.println(NumberOFShares);
              

                        String sql = "insert into Portfolio" + " (CompanyName,Symbol,NumberOFShares,C_id)" + "values(?,?,?,?);";
                        myStmt = c.prepareStatement(sql);
                        myStmt.setString(1, CompanyName);
                        myStmt.setString(2, Symbol);
                        myStmt.setInt(3, NumberOFShares); 
                        myStmt.setInt(4,C_id);
                        myStmt.executeUpdate();

                        myStmt.close();
                        c.close();

                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("Vendor: " + ex.getErrorCode());

                    }
                    break;
            
                case 9:
                    try{
                    c = DriverManager.getConnection(url);
                    stmt = c.createStatement();
 
                    System.out.println("Please Enter the Customer ID to update Customer information: \n");
                    System.out.println("Customer ID: ");
                    Scanner s = new Scanner(System.in);    
                    int C_id = s.nextInt();
                    
                    System.out.println("Select: 1 To Update Customer Name \n        2 Update Customer Address \n        3 To Update Phone Number");
                        Scanner a = new Scanner(System.in);
                        int i = a.nextInt();
                switch (i) {
                    case 1:
                        {
                            System.out.println("Enter new First Name:");
                            Scanner fn = new Scanner(System.in);
                            String f = fn.nextLine();
                            System.out.println("Enter new Last Name:");
                            Scanner ln = new Scanner(System.in);
                            String l = ln.nextLine();
                            String name = f + " " + l;
                            String uprs = ("UPDATE Customer SET Name='" + name+"'" +"WHERE C_id="+C_id);
                            stmt.executeUpdate(uprs);
                            System.out.println("Update Completed: New Name is :\n"+ name);
                            stmt.close();
                            c.close();
                            break;
                        }
                    case 2:
                        {
                            System.out.println("Enter new Address: Street #, Street Name, City, Province, PostalCode \n");
                            Scanner st = new Scanner(System.in);
                            String street = st.nextLine();
                            String uprs=("UPDATE Customer SET Address='" +street+"'" +"WHERE C_id="+C_id);
                            stmt.executeUpdate(uprs);
                            System.out.println("Update Completed New Address:\n"+street);      
                            stmt.close();
                            c.close();
                            break;
                        }
                    case 3:
                        {
                            System.out.println("Enter new Phone Number: \n");
                            Scanner nu = new Scanner(System.in);
                            String pn = nu.nextLine();
                            String uprs = ("UPDATE Customer SET PhoneNo='" +pn+ "'" +"WHERE C_id="+C_id);
                            stmt.executeUpdate(uprs);
                            System.out.println("Update Completed new Phone is:\n"+pn);
                            stmt.close();
                            c.close();
                            break;
                        }
                    default:
                        break;
                }
                        
                        }catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("Vendor: " + ex.getErrorCode());

                    }
                    break;
                    
                    
                case 10:
                     try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        System.out.println("Select: 1 for Best Rating \n        2 for Worst Rating or \n        3 for Customer info");
                        Scanner a = new Scanner(System.in);
                        int i = a.nextInt();
                        if (i == 1) {
                            ResultSet uprs = stmt.executeQuery("SELECT MAX(Ranking) as Rating from Feedback");
                            System.out.println("Best Rating:");
                            while (uprs.next()) {
                                int y = uprs.getInt("Rating");              
                                System.out.println("    " + y + "  " + "\n");
                            }
                            uprs.close();
                            stmt.close();
                            c.close();
                        } else if (i == 2) {
                            ResultSet uprs = stmt.executeQuery("SELECT MIN(Ranking) as Rating FROM Feedback");
                            System.out.println("Worst Rating:");
                            while (uprs.next()) {
                                int x = uprs.getInt("Rating");      
                                System.out.println(x + "  " + "\n");
                            }
                            uprs.close();
                            stmt.close();
                            c.close();
                        } else if (i == 3) {
                            System.out.println("Please Enter the (Highest or Lowest) Rating to get Customer Name:");
                            Scanner s = new Scanner(System.in);
                            int r = s.nextInt();                 
          
                            ResultSet uprs = stmt.executeQuery("SELECT Name FROM Feedback where Ranking="+r);
                            System.out.println("Customer Name:");
                            while (uprs.next()) {
                                String Name = uprs.getString("Name");
                                System.out.println(" " + Name);
                            }
                           
                            uprs.close();                           
                            stmt.close();
                            c.close();

                        } else {

                            System.out.println("This Selection is not available please try again");
                        }
 
                    } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                    break;
                    
                    case 11:
                    try {
                        c = DriverManager.getConnection(url);
                        stmt = c.createStatement();
                        System.out.println("Please Enter the Employee Name to Retrieve Employee Number:");
                        System.out.println("Enter First Name:");
                        Scanner fn = new Scanner(System.in);
                        String f = fn.nextLine();
                        System.out.println("Enter Last Name:");
                        Scanner ln = new Scanner(System.in);
                        String l = ln.nextLine();
                        
                        String name = f + " " + l;

                        System.out.println("Retrieving Employee Number:" + name);  
                        ResultSet uprs = stmt.executeQuery("select EmployeeNumber from Admin where Name='" + name+"'");

                        if (uprs.next()){
                            
                            String EmployeeNumber = uprs.getString("EmployeeNumber");
                            System.out.println("Employee Number:");
                            System.out.println("" + EmployeeNumber+"\n");
                            
                        
                        }else{
                            System.out.println("This Name is not in the Employee list please try again");
                            break;
                            
                        }
                            uprs.close();
                            stmt.close();
                            c.close();
                            
                          } catch (SQLException ex) {
                        System.err.println("----SQLException-----");
                        System.err.println("SQLState: " + ex.getSQLState());
                        System.err.println("Message: " + ex.getMessage());
                        System.err.println("SQLState: " + ex.getErrorCode());

                    }
                     
                        
                    break;        
  
                default:
                break;
            }
            
              System.out.println("Welcome to the Shares Database Application: \n\n"
                + "Select: 0 to Exit \n"      
                + "Select: 1 To Display Customer Table Information\n"
                + "Select: 2 To Display Customer Portfolio Information\n"
                + "Select: 3 To Add New Customer Inforamtion \n"
                + "Select: 4 To List of All Quotes\n"
                + "Select: 5 To Display Quote Information\n"
                + "Select: 6 For List Purchared or Sold shares\n"
                + "Select: 7 To View Customer Feedback\n"
                + "Select: 8 To Add New Shares to Portfolio\n"
                + "Select: 9 To Update the Customer information\n"
                + "Select: 10 For Best or Worst Customer Rating\n"
                + "Select: 11 To Display the Employee Number ");

            select = scan.nextInt();
        }
    }


}
