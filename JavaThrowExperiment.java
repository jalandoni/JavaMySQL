/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * 
 */
public class JavaThrowExperiment {

    public static void main(String args[]) throws IOException, PasswordException {
        Student_Accounts ai = new Student_Accounts();
        Student_PersonalInfo pi = new Student_PersonalInfo();
        Student_Schedules ci = new Student_Schedules();
        Scanner input1 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        Scanner input4 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input5 = new Scanner(System.in);
        boolean exit = false;

        while (exit == false) {

            System.out.println("\n♣♣♣♣♣♣♣ Select list of commands ♣♣♣♣♣♣♣");
            System.out.println("1 for create\n2 for retrieve\n3 for update\n4 for delete\n5 for search\n6 for exit");
            System.out.print("Choice : ");
            String choice1 = input1.next();
            switch (choice1) {
                case "1":
                    ai.createToDB();
                    System.out.print("Add personal information (yes or no) ? ");
                    String choice4 = input2.next();
                    switch (choice4) {
                        case "yes":
                            pi.createToDB();
                            while (true) {
                                System.out.print("Do you want to add courses (yes/no) ? ");
                                String choice5 = input3.next();
                                if ("yes".equals(choice5)) {
                                    ci.createToDB();
                                } else {
                                    break;
                                }
                            }
                        case "no":
                            break;
                    }
                    break;
                case "2":
                    System.out.println("\n☺☺☺☺☺☺☺☺ Retrieving information ☺☺☺☺☺☺☺☺");
                    ai.retrieveDatabase();
                    pi.retrieveDatabase();
                    ci.retrieveDatabase();
                    break;
                case "3":
                    try {
                        System.out.println("\n☺☺☺☺☺☺☺☺ Updating Information ☺☺☺☺☺☺☺☺");
                        System.out.print("Account ID :");
                        int choice6 = input2.nextInt();
                        pi.update(choice6);
                        while (true) {
                            System.out.print("Update Courses (yes or no) ? ");
                            String choice8 = input5.next();
                            if ("yes".equals(choice8)) {
                                ci.update(choice6);
                            } else {
                                break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Mismatch input.");
                    }
                    break;
                case "4":
                    try {
                        System.out.println("\n☺☺☺☺☺☺☺☺ DELETE Information ☺☺☺☺☺☺☺☺");
                        System.out.print("Account ID :");
                        int choice2 = input4.nextInt();
                        pi.delete(choice2);
                        ci.delete(choice2);
                    } catch (InputMismatchException e) {
                        System.out.println("Mismatch input.");
                    }
                    break;
                case "5":
                    try {
                        System.out.println("\n☺☺☺☺☺☺☺☺ SEARCH ☺☺☺☺☺☺☺☺");
                        System.out.print("Account ID :");
                        int choice3 = input3.nextInt();
                        ai.search(choice3);
                        pi.search(choice3);
                        ci.search(choice3);
                    } catch (InputMismatchException e) {
                        System.out.println("Mismatch input.");
                    }
                    break;
                case "6":
                    System.out.println("Thank you!");
                    exit = true;
                    break;
            }

        }
    }

}
