package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		
		List<Student> students = new ArrayList<>();
		
		Connection myCon = null;
		Statement myStmt = null;
		ResultSet myResultSet = null;
		
		try {
			// get a connection 
			myCon = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from student order by last_name";
			
			myStmt = myCon.createStatement();
					
			// execute query
			myResultSet = myStmt.executeQuery(sql);
					
			// process result set 
			while(myResultSet.next()) {
				
				// retrieve data from result set row
				int id = myResultSet.getInt("id");
				String firstName = myResultSet.getString("first_name");
				String lastName= myResultSet.getString("last_name");
				String email = myResultSet.getString("email");
				
				// create new student object
				Student tempSudent = new Student(id, firstName, lastName, email);
				
				// add it to the list of students
				students.add(tempSudent);			
			}

			return students;
		
		}finally {
			
			// close JDBS objects
			close(myCon, myResultSet, myStmt); // if we don't clos connection we'll eventually have memory leak
			
		}
		
		
	}



	public void addStudent(Student theStudent) throws SQLException {

		Connection myCon = null;
		PreparedStatement myStmt = null;
		
		try {
			
			// get db connection
			myCon = dataSource.getConnection(); 
			
			// create sql for insert
			String sql = "insert into student"
					+	"(first_name, last_name, email) "
					+	"values (?, ?, ?)";
			
			myStmt = myCon.prepareStatement(sql);
			
			// set the param values for the student 
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			// execute sql insert 
			myStmt.execute();
			
			// clean up  JDBC objects
			
			
		} finally {
			close(myCon, null, myStmt);
		} 
		
	}

	public Student getStudent(String theStudentId) throws Exception {

		Student theStudent = null;
		
		Connection myConnection = null;
		PreparedStatement myStmt = null;
		ResultSet myResultSet = null;
		int studentId;
		
		
		try {
			
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConnection = dataSource.getConnection();
			
			// create sql to get selected student
			String sqlString = 	"select * from student where id=?";
			
			// create prepared statement
			myStmt = myConnection.prepareStatement(sqlString);
					
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myResultSet = myStmt.executeQuery();
			
			// retrieve data from result set row
			if(myResultSet.next()) {
				String firstNameString = myResultSet.getString("first_name");
				String lastNameString = myResultSet.getString("last_name");
				String emailString = myResultSet.getString("email");
				
				// use the studentId during construction
				theStudent 	= new Student(studentId, firstNameString, lastNameString, emailString);
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}
			
			return theStudent;
	
		} finally {
			close(myConnection, myResultSet, myStmt);
		}
		
		
	}

	public void updateStudent(Student theStudent) throws Exception {

		Connection myConnection = null;
		PreparedStatement myStmt = null;
		
		try {
			
			// get db connection
			myConnection = dataSource.getConnection();
			
			// create sql update statement
			String sql = "update student "
						+ 	"set first_name=?, last_name=?, email=? "
						+ 	"where id=?";
			
			// prepare statement
			myStmt = myConnection.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			// execute sql statement
			myStmt.execute();
		
		} finally {
			close(myConnection, null, myStmt);
		}
		
	}

	public void deleteStudent(int studentId) throws Exception{

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sqlString = "delete from student where id=?" ;
			myStmt = myConn.prepareStatement(sqlString);
			
			myStmt.setInt(1, studentId);
			
			myStmt.execute();
			
		} finally {
			close(myConn, null, myStmt);
		}
	}

	private void close(Connection myCon, ResultSet myResultSet, Statement myStmt) {

		try {
			
			if(myResultSet != null) {
				myResultSet.close();
			}
			
			if(myCon != null) {
				myCon.close(); 		// doesn't really close it... just put back in connection pool, makes it available for someone else to use
			}
			
			if(myStmt != null) {
				myStmt.close();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}








