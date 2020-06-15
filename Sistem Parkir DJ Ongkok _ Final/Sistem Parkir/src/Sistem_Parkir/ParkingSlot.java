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
public class ParkingSlot {
    private String slotNo;
    private String availableStatus;

    public ParkingSlot(String slotNo, int set){
        this.slotNo = slotNo;
        if(set==0){
            this.availableStatus = "Not Available";
        }
        else if(set==1){
            this.availableStatus = "Available";
        }
    }
    
    public void setSlotNo(String slotNo){
        this.slotNo = slotNo;
    }
    
    public void setStatus(int set){
        if(set==0){
            this.availableStatus = "Not Available";
        }
        else if(set==1){
            this.availableStatus = "Available";
        }
    }
    
    public String getSlotNo(){
        return slotNo;
        }
    
    public String getStatus(){
        return availableStatus;
    }
    
}
