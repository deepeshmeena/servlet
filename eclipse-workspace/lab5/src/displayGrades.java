

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class displayGrades
 */
@WebServlet("/displayGrades")
public class displayGrades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayGrades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out= response.getWriter();
		HttpSession session =request.getSession(false);
		String ss = (String) session.getAttribute("MyAttribute");
		//out.println("succesfulogin");
		out.println("<h1> User id is :"+ ss +"</h1> ");
		
		try (
			    Connection conn = DriverManager.getConnection(
			    		"jdbc:postgresql://localhost:5410/postgres", "labuser", "");
			   //Statement stmt1 = conn.createStatement();
			)
			{		
			PreparedStatement ps=conn.prepareStatement( " select t.course_id, c.title , t.sec_id, t.semester, t.year ,t.grade from course c ,takes t ,student s where  c.course_id=t.course_id and t.ID=s.ID and s.ID=?;");  
			ps.setString(1,ss);  
			//ps.setString(2,p);  
			out.println("<table><tr> <th>course_id </th>  <th>title </th> <th>section_id </th> <th>semester </th><th>year </th><th> grade </th> ");
	      
			ResultSet rs=ps.executeQuery();  
			while(rs.next()) {
				
			out.println("<tr> <td>" + rs.getString(1) + "</td> <td>"
						+rs.getString(2) +"</td>  <td>"
						+rs.getString(3) 
						+" </td> <td>" + 
						rs.getString(4)+  "</td> <td>"+ 
						rs.getString(5)+ "</td><td>"
						+rs.getString(6)+" </td> </tr>" );
		//out.println(" title is:"+ rs.getString(2) );
		//out.println(" section_id is:"+ rs.getString(3) );
	//	out.println(" semester is:"+ rs.getString(4) );
		//out.println(" yar is:"+ rs.getString(5) );
	//	out.println(" grade is:"+ rs.getString(6) );
		//out.println("success");
		//out.print("<html><a href=\"http://localhost:8080/lab5/displayGrades\">grades</a></html>");
		
			}
			out.print("</table>");
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
