<html>

<head>
	<title>Update Student</title>
	<link rel="stylesheet" href="css/style.css" type="text/css"></link>
	<link rel="stylesheet" href="css/add-student-style.css" type="text/css"></link>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	
	<div>
		<h3>Update student</h3>
		
		<form action = "StudentControllerServlet" method = "GET">
		
			<input type="hidden" name="command" value="UPDATE">
			
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}">
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name: </label></td>
						<td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" value="${THE_STUDENT.email}"></td>
					</tr>
					<tr>
					<td><label></label></td>
					<td><input type = "submit" value = "Save" class="save"/>
					</tr>
				</tbody>
				
		
			</table>
		
		
			<!-- 
		
	         First Name: <input type = "text" name = "first_name">
	         <br /> <br>
	         Last Name: <input type = "text" name = "last_name" />
	         <br /> <br>
	         Email: <input type = "text" name = "last_name" />
	         <br> <br>
	         <input type = "submit" value = "Save" />
	         -->
	 
				
	      </form>
	      
	      <div style="clear: both;">
	      	<p>
	      		<a href="StudentControllerServlet">Back to list</a>
	      	</p>
	      </div>
	      
	      
      
      </div>
      

</body>


</html>