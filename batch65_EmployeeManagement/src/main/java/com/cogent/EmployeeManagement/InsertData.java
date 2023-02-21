package com.cogent.EmployeeManagement;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insert")

public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/batch65";
	static final String USER = "root";
	static final String PASS = "Abhinav@1995";
	final String DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection conn = null;
	
	// Auto-generated constructor stub
	public InsertData() {
		super();
	}
	
	public void init() throws ServletException {

		// Database connection through Driver Manager
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	// HttpServlet doPost(HttpServletRequest request, HttpServletResponse response)
	// method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the values from the request using 'getParameter'
		String id = request.getParameter("ID");
		String Name = request.getParameter("Name");
		String Type = request.getParameter("Type");
		String Add = request.getParameter("Address");

		response.setContentType("text/html");

		// Get the PrintWriter object to write
		// the response to the text-output stream
		PrintWriter out = response.getWriter();

		// Print the data
		out.print("<html><body>");
		out.print("<h3>Details Entered</h3><br/>");

		out.print("ID: " + id + "<br/>");
		out.print("Name: " + Name + "<br/>");
		out.print("Type: " + Type + "<br/>");
		out.print("Address: " + Add + "<br/>");

		out.print("</body></html>");
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // Statement stmt =
			conn.createStatement(); //
			//String QUERY = "INSERT INTO persons (PersonID, LastName) VALUES (117, 'Abhi')"; //
			// Statement Or Prepared Statement

			PreparedStatement stmt = conn
					.prepareStatement("insert into Bank (ID, Name, Type, Address) values(?,?,?,?)");
			int val = Integer.parseInt(id);
			stmt.setInt(1, val);// 1 specifies the first parameter in the query
			stmt.setString(2, Name);
			stmt.setString(3, Type);
			stmt.setString(4, Add);

			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");

			// boolean rs = stmt.execute(QUERY);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

}
