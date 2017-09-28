/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model.exception;

/**
 *
 * @author Wallison Carlos
 */
public class IllegalCodeExcpetion extends IllegalArgumentException{
    
    public IllegalCodeExcpetion(){}
    
    public IllegalCodeExcpetion(String message){
        super(message);
    }
}
