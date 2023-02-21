package com.cogent.EmployeeManagement;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetch")
public class FetchData extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final String URL = "jdbc:mysql://localhost/batch65";
	final String USER = "root";
	final String PASSWORD = "Abhinav@1995";
	final String DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection conn = null;

	public void init() throws ServletException {

		// Database connection through Driver Manager
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// Set the response content type and
			// get the PrintWriter object.
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// Set up HTML table formatting for the output
			out.println("<html><body>");
			out.println("<h3>Bank Details</h3>");
			out.println("<table border=1><tr>" + "<td><b>ID</b></td>" + "<td><b>Name</b></td>"
					+ "<td><b>Type</b></td>" + "<td><b>Address</b></td>");

			// Create JDBC statement object, construct
			// the SQL query and execute the query.
			Statement stmt = conn.createStatement();
			String sql = "select * from Bank;";
			ResultSet rs = stmt.executeQuery(sql);

			// Loop through the result set to
			// retrieve the individual data items.
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String type = rs.getString("Type");
				String add = rs.getString("Address");

				out.println("<tr>" + "<td>" + id + "</td>" + "<td>" + name + "</td>" + "<td>" + type + "</td>" + "<td>" + add + "</td>");

			}
			out.println("</table></body></html>");

			// Close Result set, Statement
			// and PrintWriter objects.
			rs.close();
			stmt.close();
			out.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {

		// Close connection object.
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
