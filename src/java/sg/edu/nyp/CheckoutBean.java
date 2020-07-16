/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
@Stateless
public class CheckoutBean {
    public double calculateDelivery(List cartlist)
    {
        double total = 0;
        for(int i = 0; i < cartlist.size(); i++)
        {
            //total += cartlist.get(i);
        }
        
        if(total<50)
        {
            return 5;
        }
        else
        {
            return 0;
        }
    }
}
