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
 * Servlet implementation class LeaveReviewServlet
 */
@WebServlet("/LeaveReviewServlet")
public class LeaveReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) request.getParameter("name");
		String category = (String) request.getParameter("category");
		String review = (String) request.getParameter("review");
		Float rating = (Float) Float.valueOf(request.getParameter("rating"));

		String next = "/main.jsp";
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://google/yo?cloudSqlInstance=usc-ratings-website:us-central1:uscratings&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=jdc711&password=0203");
					ps = conn.prepareStatement("SELECT * FROM entries WHERE name=? AND category=?");
					ps.setString(1,  name);
					ps.setString(2, category);
					rs = ps.executeQuery();
					//result has already been reviewed before
					if(rs.next()) {
						id = rs.getInt("id");
						ps = conn.prepareStatement("INSERT INTO reviews (id, description, rating, category) VALUES (?, ?, ?,?)");
						ps.setInt(1,  id);
						ps.setString(2, review);
						ps.setFloat(3, rating);
						ps.setString(4,category);
						ps.execute();
						float newRating = rs.getFloat("rating");
						float size = 2;
						newRating+=rating;
						ps = conn.prepareStatement("SELECT * FROM reviews WHERE id=?");
						ps.setInt(1,  id);
						rs = ps.executeQuery();
						while (rs.next()) {
							newRating += rs.getFloat("rating");
							size++;
						}
						ps = conn.prepareStatement("UPDATE entries SET rating =? WHERE id=?");
						BigDecimal bd = new BigDecimal((float)newRating/(float)size).setScale(2, RoundingMode.HALF_UP);
				        float roundedRating = bd.floatValue();
						ps.setFloat(1,  roundedRating);
						ps.setInt(2, id);
						ps.execute();
						
					}
					//result has never been reviewed before
					else {
						ps.close();
						rs.close();
						
						ps = conn.prepareStatement("INSERT INTO entries (name, rating, category ) VALUES (?, ?, ?)");
						ps.setString(1,  name);
						ps.setFloat(2, rating);
						ps.setString(3, category);
						ps.execute();
						ps = conn.prepareStatement("SELECT * FROM entries WHERE name=? AND category=?");
						ps.setString(1,  name);
						ps.setString(2, category);
						rs = ps.executeQuery();
						if (rs.next()) {
							id = rs.getInt("id");
						}
						ps = conn.prepareStatement("INSERT INTO reviews (rating, description, id, category) VALUES(?,?,?,?)");
						ps.setFloat(1,  rating);
						ps.setString(2, review);
						ps.setInt(3, id);
						ps.setString(4, category);
						ps.execute();
					}
				
				}
				catch (SQLException sqle) {  
					System.out.println(sqle.getMessage());		
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
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

}
