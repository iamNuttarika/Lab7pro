/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heman
 */

package sg.edu.nyp;


import java.io.Serializable;


public class Cart implements Serializable {
    public static final long serialVersionUID = -1L;
    private String itemDescription;
    private String item;
    private Integer quantity;
    private Integer itemId;
    private Integer categoryId;
    private Float cost;
   
    public Cart() {
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
 public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    
     public void setCategoryID(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

   
    
    
}