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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import static javathrowexperiment.Accounts.account_id;
import static javathrowexperiment.Student_Accounts.JDBC_DRIVER;

/**
 *
 * 
 */
public class Student_Schedules {

    ArrayList<Courses> c;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentAccount";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public Student_Schedules() {
        c = new ArrayList();
    }

    public void retrieveDatabase() {
        java.sql.Connection conn;
        Statement stmt;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Schedule";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n\t    ♣♣♣♣♣♣♣ Schedules ♣♣♣♣♣♣♣\n");
            c = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accid = rs.getInt("ACC_ID");
                String title = rs.getString("title");
                int unit = rs.getInt("unit");
                String schedule = rs.getString("schedule");
                System.out.println(id + "\t" + accid + "\t" + title + "\t" + unit + "\t" + schedule + "\t");
                c.add(new Courses(id, accid, title, Integer.toString(unit), schedule));
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

    public boolean createToDB() {
        java.sql.Connection conn;
        Statement stmt;
        String title, unit, schedule;
        System.out.print("Subject : ");
        title = input1.nextLine();
        System.out.print("Units : ");
        unit = input2.nextLine();
        while (Check.isString(unit)) {
            System.out.println("Units is not a string.");
            createToDB();
        }
        System.out.print("Schedule : ");
        schedule = input3.nextLine();
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO Schedule (ACC_ID, title, unit, schedule) VALUES ('" + account_id + "', '" + title + "', '" + unit + "', '" + schedule + "')";;
            System.out.println(sql);
            stmt.executeUpdate(sql);
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
        return true;
    }

    public boolean update(int acc_id) throws IOException {
        java.sql.Connection conn;
        Statement stmt;
        String title = null, unit = null, schedule = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            for (int i = 0; i < c.size(); i++) {
                if (c.get(i).getAccount_id() == acc_id) {
                    System.out.println("\t\t *** Schedules ***\n");
                    System.out.println(c.get(i).getId() + "\t\t" + c.get(i).getAccount_id() + "\t\t" + c.get(i).getTitle() + "\t\t" + c.get(i).getUnit() + "\t\t" + c.get(i).getSchedule());
                    System.out.print("\nNew subject : ");
                    title = input1.nextLine();
                    System.out.print("New units : ");
                    unit = input2.nextLine();
                    while (Check.isString(unit)) {
                        System.out.println("Units is not s string.");
                        update(acc_id);
                    }
                    System.out.print("New schedule : ");
                    schedule = input3.nextLine();
                    String sql = "UPDATE Schedule SET `ACC_ID` = '" + acc_id + "', `title` = '" + title + "', `unit` = '" + unit + "', `schedule` = '" + schedule + "' WHERE `ID` = '" + c.get(i).getId() + "'";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    System.out.println("Saved!");
                    return true;
                } else if (c.get(i).getAccount_id() != acc_id && i == c.size() - 1 && title == null && unit == null && schedule == null) {
                    System.out.print("Subject : ");
                    title = input1.nextLine();
                    System.out.print("Units : ");
                    unit = input2.nextLine();
                    while (Check.isString(unit)) {
                        System.out.println("Units is not a string.");
                        update(acc_id);
                    }
                    System.out.print("Schedule : ");
                    schedule = input3.nextLine();
                    String sql = "INSERT INTO Schedule (ACC_ID, title, unit, schedule) VALUES ('" + account_id + "', '" + title + "', '" + unit + "', '" + schedule + "')";;
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    System.out.println("Saved!");
                    return true;
                }
            }
            if (acc_id == 1) {
                System.out.println("\t\t    *** Schedules ***");
                System.out.print("Enter title/subject : ");
                title = input1.nextLine();
                System.out.print("Enter units : ");
                unit = input2.nextLine();
                while (Check.isString(unit)) {
                    System.out.println("Units is not a string.");
                    update(acc_id);
                }
                System.out.print("Enter schedule : ");
                schedule = input3.nextLine();
                String sql = "INSERT INTO Schedule (ACC_ID, title, unit, schedule) VALUES ('" + account_id + "', '" + title + "', '" + unit + "', '" + schedule + "')";;
                System.out.println(sql);
                stmt.executeUpdate(sql);
                System.out.println("Saved!");
                return true;
            }
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
        return false;
    }

    public void delete(int acc_id) {
        java.sql.Connection conn;
        Statement stmt;
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getAccount_id() == acc_id) {
                System.out.println(c.get(i));
                try {
                    //STEP 2: Register JDBC driver
                    Class.forName(JDBC_DRIVER);

                    //STEP 3: Open a connection
//                    System.out.println("Connecting to database...");
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    //STEP 4: Execute a query
//                    System.out.println("Creating statement...");
                    stmt = conn.createStatement();
                    String sql = "DELETE FROM Schedule WHERE `ACC_ID` = '" + acc_id + "'";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                } catch (SQLException se) {
                    //Handle errors for JDBC
                    se.printStackTrace();
                } catch (Exception e) {
                    //Handle errors for Class.forName
                    e.printStackTrace();
                }
                System.out.println("Deleted!");
            }
        }
    }

    public void search(int acc_id) {
        c.stream().filter((c1) -> (c1.getAccount_id() == acc_id)).forEach((c1) -> {
            System.out.println(c1.getId() + "\t" + c1.getAccount_id() + "\t" + c1.getTitle() + "\t" + c1.getUnit() + "\t" + c1.getSchedule());
        });
    }
}
