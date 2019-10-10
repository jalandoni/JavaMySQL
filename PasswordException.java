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
class PasswordException extends Exception{
    public PasswordException(String pass) {
        super(pass);
    }
}
