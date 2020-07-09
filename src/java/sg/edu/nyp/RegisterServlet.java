/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;
import java.io.Serializable;
import javax.ejb.Stateless;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/RegisterServlet")
@Stateless
public class RegisterServlet extends HttpServlet
{
    //Inject your EJB here
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //Get the name parameter into a local variable
        
        //Retrieve the form attributes
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String postal = request.getParameter("postal");
        String phNum = request.getParameter("phNum");
        
            
      //Set the results in the session
        HttpSession session = request.getSession();
    
        
        
        //Redirect to analysis results page
        response.sendRedirect(this.getServletContext().getContextPath() + "/");
    }
}

   
    

