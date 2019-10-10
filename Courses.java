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
import java.util.Scanner;

/**
 *
 * 
 */
public class Courses {

    Scanner input = new Scanner(System.in);
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);
    private String title;
    private String unit;
    private String schedule;
    private int id = 1;
    private int account_id = Accounts.account_id - 1;

    Courses() throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\morrejo_sd2023\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt"))) {
            while (reader.readLine() != null) {
                id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    
    public Courses(int id, int ac, String title, String unit, String sc) {
        this.id = id;
        this.account_id = ac;
        this.title = title;
        this.schedule = sc;
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

}