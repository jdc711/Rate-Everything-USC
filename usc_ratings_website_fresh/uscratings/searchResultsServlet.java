package uscratings;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class searchResultsServlet
 */
@WebServlet("/searchResultsServlet")
public class searchResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchResultsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) request.getParameter("name");
		String next = "/searchResults.jsp";
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		SearchResult searchResult = new SearchResult();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://google/yo?cloudSqlInstance=usc-ratings-website:us-central1:uscratings&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=jdc711&password=0203");
					ps = conn.prepareStatement("SELECT * FROM entries WHERE name LIKE '%"+name+"%'");
					//ps.setString(1,  name);
					rs = ps.executeQuery();
					while (rs.next()) {
						Result result = new Result();
						result.name = rs.getString("name");
						BigDecimal bd = new BigDecimal(rs.getFloat("rating")).setScale(2, RoundingMode.HALF_UP);
				        float rating = bd.floatValue();
						result.rating = rating;
						result.id = rs.getInt("id");
						result.category = rs.getString("category");
						searchResult.results.add(result);
					}	
				}
				catch (SQLException sqle) {  
					System.out.println(sqle.getMessage());		
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			session.setAttribute("SearchResults", searchResult);
			request.setAttribute("search", name);
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(next);
		try {
			dispatch.forward(request, response);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ServletException e) {
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
