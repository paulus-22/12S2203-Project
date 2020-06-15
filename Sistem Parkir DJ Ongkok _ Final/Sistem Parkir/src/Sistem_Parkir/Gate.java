/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem_Parkir;

/**
 *
 * @author ASUS
 */
public class Gate {
    private String status = "Close";
    
    public void open(){
        status = "Open";
    }
    
    public void close(){
        status = "Close";
    }
    
    public void getCard(){
        
    }
    
    public void insertCard(){
        
    }
}
