/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

/**
 *
 * @author 2ndyrGroupC
 */
public class Check {

    public static boolean isString(String input) {
        try {
            int number = Integer.parseInt(input);
            return false;
        } catch (NumberFormatException ex) {
            return true;
        }
    }
    
}
