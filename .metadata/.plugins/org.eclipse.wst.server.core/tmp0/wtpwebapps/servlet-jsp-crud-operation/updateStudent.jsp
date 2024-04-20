<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<title>Update Student</title>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-md-8 offset-2">
				<div class="card mt-5">

					<div class="card-header">
						<h3 class="text-center">Update Student</h1>
					</div>

					<div class="card-body">

						<form method="post" action="${pageContext.request.contextPath}/updateStudent">
						
						 <div class="mb-3">
								<input type="hidden" name="id" value="${student.id}" class="form-control">
							</div>
						
						   <div class="mb-3">
								<label for="name" class="form-label">Name</label>
								<input type="text" name="name" value="${student.name}" class="form-control" placeholder="Enter Name" id="name">
							</div>
						
							<div class="mb-3">
								<label for="email" class="form-label">Email</label> 
								<input type="email" name="email" value="${student.email}" class="form-control" id="email" placeholder="Enter Email">
							</div>
							
							<div class="mb-3">
								<label for="city" class="form-label">City</label>
								<input type="text" name="city" value="${student.city}" class="form-control" placeholder="Enter City" id="city">
							</div>
							
							<div class="mb-3">
								<label for="contactNo" class="form-label">Contact No</label>
								<input type="text" name="contactNo" value="${student.contactNo}" class="form-control" placeholder="Enter Contact" id="contactNo">
							</div>
							
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
</html>