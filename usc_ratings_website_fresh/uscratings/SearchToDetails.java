package uscratings;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchToDetails
 */
@WebServlet("/SearchToDetails")
public class SearchToDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchToDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idx = Integer.parseInt(request.getParameter("idx"));
		int id = Integer.parseInt(request.getParameter("id"));
		request.getSession().setAttribute("idx", idx);
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://google/yo?cloudSqlInstance=usc-ratings-website:us-central1:uscratings&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=jdc711&password=0203");
			ps = conn.prepareStatement("SELECT * FROM reviews WHERE id=?");
			ps.setInt(1,  id);
			rs = ps.executeQuery();
		while (rs.next()) {
			Review newReview = new Review();
			newReview.description =rs.getString("description");
			
			BigDecimal bd = new BigDecimal(rs.getFloat("rating")).setScale(2, RoundingMode.HALF_UP);
	        float rating = bd.floatValue();
	        //System.out.println(rating);
			newReview.rating = rating;
	        reviews.add(newReview);
		}
		
		
		
		}
		catch (SQLException sqle) {  
			System.out.println(sqle.getMessage());		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("chosenReview", reviews);
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/Details.jsp");
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
