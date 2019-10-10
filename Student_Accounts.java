/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Objects.hash;
import java.util.Scanner;
import static javathrowexperiment.Accounts.account_id;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 *
 */
public class Student_Accounts {

    ArrayList<Accounts> a = new ArrayList();
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentAccount";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public Student_Accounts() {
    }

    public void retrieveDatabase() {
        Connection conn;
        Statement stmt;
        a = new ArrayList();
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM account";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("--- RETRIEVE ---");
            System.out.println("\n\t\t♣♣♣♣♣♣♣ Accounts ♣♣♣♣♣♣♣\n");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(id + "\t\t" + username + "\t\t" + password + "\t\t");
                a.add(new Accounts(id, username, password));
            }
            if (a.size() == 0) {
                account_id = 1;
            } else {
                account_id = a.get(a.size() - 1).getAcc_id();

            }
//            STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
//        System.out.println("Retrieved!");
    }

    public void createToDB() {
        Connection conn;
        Statement stmt;
        String username, password, confirm;
        System.out.print("\n♣♣♣♣♣♣♣ CREATE ♣♣♣♣♣♣♣\nUsername : ");
        username = input1.next();
        while (!Check.isString(username)) {
            System.out.println("Username contains merely number.");
            createToDB();
        }
        while (true) {
            try {
                System.out.print("Password : ");
                password = input1.next();
                if (password.length() >= 8) {
                    break;
                } else {
                    throw new PasswordException("Password is too short.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        while (true) {
            try {
                System.out.print("Confirm password : ");
                confirm = input2.next();
                if (confirm == null ? password == null : confirm.equals(password)) {
                    break;
                } else {
                    throw new PasswordException("Password mismatch.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO Account (username, password) VALUES ";

            sql += "('" + username + "', '" + hash(password.toCharArray()) + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            ++account_id;
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        System.out.println("Saved!");
    }

    public void search(int acc_id) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getAcc_id() == acc_id) {
                System.out.println(a.get(i).getAcc_id() + "\t" + a.get(i).getUsername() + "\t" + a.get(i).getPassword());
            }
        }
    }

}
