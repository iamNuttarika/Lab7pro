package sg.edu.nyp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Search Servlet will search through the database for books titles with an
 * approximate match to the search term supplied by the user
 *
 * @author FlexTio
 */


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    //Inject SearchBean into SearchServlet
    @EJB
    private RegisterBean RegisterBean;

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
            throws ServletException, IOException {


     //Retrieve the form attributes
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String postal = request.getParameter("postal");
        String phNum = request.getParameter("phNum");

        
             //Declare a list data structure, storing results returned 
        String message  = RegisterBean.addUser(name,email,address,postal,phNum);    
        
        
        //Set the search term and the search results in the session
        HttpSession session = request.getSession();
          session.setAttribute("message", message);

        //Make a client side redirect to the search result page
        response.sendRedirect(this.getServletContext().getContextPath() + "/");
    }
}


   
