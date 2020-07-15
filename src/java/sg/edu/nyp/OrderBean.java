/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;

/**
 *
 * @author heman
 */

import javax.ejb.Stateless;

@Stateless
public class OrderBean {
    private Integer customerid;
    private Integer orderid;
    private Float orderprice;
     public OrderBean() {
    }

    public OrderBean(Integer customerid) {
        this.customerid = customerid;
    }
    

    public Integer getOrderId() {
        return orderid;
    }

    public void setOrderId(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCustomerId() {
        return customerid;
    }

    public void setCustomerId(Integer customerid) {
        this.customerid = customerid;
    }

    public Float getOrderPrice() {
        return orderprice;
    }

    public void setOrderPrice(Float orderprice) {
        this.orderprice = orderprice;
    }
}
