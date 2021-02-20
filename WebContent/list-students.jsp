<%@page import="com.luv2code.web.jdbc.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students Tracker App</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" 
		rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

	 <%
	 /*
	 	// get the students from the request object (send by servlet)
	 	List<Student> theStudents = (List<Student>)request.getAttribute("STUDENT_LIST");
	 */
	 %>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: add student -->
			
			<input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp'; 	return false;"
					class="add-student-button"/>
		
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
					
					
				</tr>
				
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				
					<!-- Setup a link for each student -->
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>
					
					<c:url var="tempLink2" value="StudentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>
				
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.email} </td>
						<td> <a href="${tempLink}">Update</a> 
							 <a href="${tempLink2}" onclick="if (!(confirm('Are you sure?'))) return false"> | Delete</a> </td>
					</tr>
				</c:forEach>
	
				
			
			</table>
		
		</div>
	
	
	</div>
	
		

</body>


</html>








