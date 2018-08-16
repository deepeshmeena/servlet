
 import java.io.PrintWriter;

 import java.sql.*;
 import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		HttpSession session =request.getSession(false);
		String sss = (String) session.getAttribute("MyAttribute");
		out.println("<h1> successfull login </h1>");
		out.println("<h1>User id is : "+ sss+"</h1>");
		
		try (
			    Connection conn = DriverManager.getConnection(
			    		"jdbc:postgresql://localhost:5410/postgres", "labuser", "");
			   //Statement stmt1 = conn.createStatement();
			)
			{		
			PreparedStatement ps=conn.prepareStatement( "select student.dept_name from department,student where student.dept_name=department.dept_name and id=? ");  
			ps.setString(1,sss);  
			//ps.setString(2,p);  
			      
			ResultSet rs=ps.executeQuery();  
			while(rs.next()) {
				
		out.println("<h1>student dept. is:"+ rs.getString(1)+"</h1>" );
		//out.println("success");
		out.println("<a href=\"http://localhost:8080/lab5/displayGrades\">grades</a>");
		
			}
			
			 
			  
			
			out.println("<form><input type=\"button\" value=\"Log Out\" onclick=\"window.location.href='http://localhost:8080/lab5/Logout'\" ></form>");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//request.getAttribute("attributeName",n);
		//out.println(n);
			}
		catch(Exception e) {
		       e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
