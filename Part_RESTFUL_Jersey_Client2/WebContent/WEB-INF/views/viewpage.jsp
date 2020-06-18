<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>

	

<nav class="navbar navbar-inverse">
    <div class="navbar-header">
      <a class="navbar-brand" href="cancel"><%= session.getAttribute("userinfo") %></a>
    </div>
  <a href = "logout" class = "navbar-brand" style="float:right;"> Logout </a>
</nav>

	<h4>Employee Management Tool</h4> <a href = "uploadEmployeeDetailsForm">Add new Details</a> 
	<a href = "downloadData">Download Details</a>
	<table style="width: 100%">

		<tr>
			<th>Employee Id</th>
			<th>Employee Name</th>
			<th>Employee Location</th>
			<th>Employee email</th>
			<th>Employee dob</th>
			<th>Edit</th>
		</tr>

		<c:forEach var="emp" items="${employees}" >
			<tr>
				<td><c:out value="${emp.empId}" /></td>
				<td><c:out value="${emp.empName}" /></td>
				<td><c:out value="${emp.empLocation}" /></td>
				<td><c:out value="${emp.empEmail}" /></td>
				<td><c:out value="${emp.empDOB}" /></td>
				<td>
				<form action="editDetails" method = "get">
				  <input type="hidden" id="empId" name="empId" value=<c:out value="${emp.empId}" /> >
				  <input type="submit" value="Edit Details">
				</form>
				</td>
				<td>
				
			</tr>
		</c:forEach>

	</table>


</body>
</html>