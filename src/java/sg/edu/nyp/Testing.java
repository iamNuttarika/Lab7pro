/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;
import java.io.Serializable;

public class Testing implements Serializable {
   
    public static final long serialVersionUID = -1L;
    
    private int itemID;
    private String itemDescription;
    private String brand;
    private double price;
    private int points;
    
    
    
     public Testing() {
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
       public int getPoints() {
        return points;
    }
    
     public void setPoints(int points) {
        this.points = points;
    }
    
}
