/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem_Parkir;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class Transaction {
    private int slipNo;
    private int totalFee;
    private Date date = new Date();
    private int time;
    
    public void pay(int money){
        if(totalFee==0){
            System.out.println("Gratis");
        }
        else{
            if((money-totalFee)<0){
                System.out.println("Uang kurang");
            }
            else{
                int kembalian = money-totalFee;
                if(kembalian>0){
                    System.out.println("Terimakasih, kembalian anda Rp." + kembalian);
                }
                else if(kembalian==0){
                    System.out.println("Terimakasih.");
                }
            }
        }
    }
}
