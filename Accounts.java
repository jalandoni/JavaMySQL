/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * 
 */
public class Accounts {

    Scanner input = new Scanner(System.in);
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    private String username;
    private String password;
    private String confirm;
    static int account_id = 1;
    private int acc_id;
    ArrayList<Accounts> a;

    Accounts() throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\morrejo_sd2023\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts.txt"))) {
            while (reader.readLine() != null) {
                account_id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        a = new ArrayList();
    }

    public Accounts(int id, String username, String password) {
        this.acc_id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    @Override
    public String toString() {
        return "Accounts{"+"accountID="+ account_id + ", username=" + username + ", password=" + password + '}';
    }
    

}
