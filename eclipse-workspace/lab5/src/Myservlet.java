
 import java.io.PrintWriter;
 import java.sql.*;
 //import java.util.*;
import java.io.IOException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Myservlet
 */
@WebServlet("/Myservlet")
public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	PrintWriter out = response.getWriter();
	    // out.println("<html><body>Hello world</body></html>"); 
	     
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try (
			    Connection conn = DriverManager.getConnection(
			    		"jdbc:postgresql://localhost:5410/postgres", "labuser", "");
			   //Statement stmt1 = conn.createStatement();
			)
			{
			PrintWriter out = response.getWriter();
			out.println("\n" + 
        	 		"<html> \n" + 
        	 		"<form method=\"post\"> \n" + 
        	 		"Enter your name: <input type=\"text\" name = \"name\"> <br />\n" + 
        	 		"your password: <input type=\"password\" name = \"pass\" align=\"right\"> <br />\n" + 
        	 		"<input type=\"submit\" value = \"Submit\"> \n" + 
        	 		"</form>\n" + 
        	 		"</html>"); 
		     String n=request.getParameter("name");  
             String p=request.getParameter("pass");  
           
             if(n!=null && !n.isEmpty() && p!=null && !p.isEmpty())
             {
            	    
            //out.println("checks if user is in table");
            PreparedStatement ps=conn.prepareStatement( "select password from password where id=? ");  
        			ps.setString(1,n);  
        			//ps.setString(2,p);  
        			      
        			ResultSet rs=ps.executeQuery();  
        			while(rs.next()) {
        			
        			if(p.equals(rs.getString(1))  ) {
        				   HttpSession session = request.getSession();
        			        session.setAttribute("MyAttribute", n);

        			        // response.sendRedirect("index.jsp");
        			     //   RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
        			     //   dispatcher.forward(request, response);
        			        response.sendRedirect("Home");
        				//request.setAttribute("attributeName",n);
        			//	RequestDispatcher rd = request.getRequestDispatcher("Myservlet.java");
        			//	rd.forward(request,response);
        				
        				out.println("success ful login");
        				
        			}
        			else
        			{
        				out.println("wrong username or password");
        			}
        			}
            }      
             else 
             {
            	 out.println("username or password can not be null");
            	 
             }
                   
             out.close();  
			}
		
			catch(Exception e) {
			       e.printStackTrace();
			}
		
	}

}
