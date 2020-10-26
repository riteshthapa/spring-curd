<!DOCTYPE html>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<title>Customer Ajax!!!</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="js/customer.js"></script>

</head>
<body>

	<header style="height: 30px; background-color: maroon;"> </header>

	<div class="container">
		<!-- <img src="img/header.png"> -->
		<h1>CREDIT CARD GENERATOR</h1>
		<hr />
		<span style="font-size: 18; color: black; font-weight: bold;">Customer Data
		
		<button type="button" class="btn btn-primary" onclick="loadCustomer()">Load Customers</button>
		<button type="button" class="btn btn-primary" onclick="addCustomer()">Add Customers</button>
		
		<button type="button" class="btn btn-primary" onclick="openAddRolePopup()">Add roles</button>
		<button type="button" class="btn btn-danger" onclick="$('#changePasswordPopup').modal('show')" >Change Password</button>
		
		<a href="${pageContext.request.contextPath}/customer/logout"><button type="button" class="btn btn-danger">Logout</button></a>
		</span>
		<hr />
		
		<div style="width: 100%">
			<table class="table table-bordered">
				<thead>
					<tr style="background-color: #50aaff;">
						<th>Cid</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
						<th>Role</th>
						<!-- <th>Debit Card</th>
						<th>Valid</th>
						<th>CVV</th> -->
						<th>Mobile</th>
						<th>Photo</th>
						<th>Company</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody id="cdata">

				</tbody>
			</table>

		</div>
	</div>

	<!--ROLE CARD MODAL-->
	<div class="modal" id="rolePopup">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 540px;">

				<input type="hidden" id="trcid">
				<input type="hidden" id="roid">
				<input type="hidden" id="roName">
				<input type="hidden" id="roDes">
				
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">User Role Info</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<span style="font-size: 18px; color: blue; font-weight: bold;" id="fName"></span> 
					<span style="font-size: 18px; color: blue; font-weight: bold;" id="lName"></span> 
					<br>
					<span style="font-size: 18px; color: blue; font-weight: bold;" id="remail"></span>
					<hr />

					<table class="table table-bordered">
						<thead>
							<tr style="background-color: #50aaff;">
								<th>SNO</th>
								<th>Role</th>
								<th>Action</th>
								<th>Edit/Delete</th>
							</tr>
						</thead>
						<tbody id="crdata">
							<tr>
								<!-- <td>12</td>
								<td>ADMIN</td>
								<td>TODO</td> -->
							</tr>

						</tbody>
					</table>

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="updateRoles()">Update</button>
				</div>

			</div>
		</div>
	</div>





	<!--CREDIT CARD MODAL-->
	<div class="modal" id="crediCardPopup">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 540px;">

				<input type="hidden" id="tpfname"> <input type="hidden"
					id="tplname"> <input type="hidden" id="tpcid">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Customer Credit Card!</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<span style="font-size: 18px; font-weight: bold;" id="pname"></span>
					<span style="font-size: 18px; color: red; font-weight: bold;" id="pemail"></span>
					<hr />
					<img src="img/credit-card-front-template.jpg" id="cphoto">
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="generateCard()">Generate Card</button>
					<!-- <button type="button" class="btn btn-warning" onClick="saveCard()">Save</button> -->
				</div>

			</div>
		</div>
	</div>


	<!-- ADD CUSTOMER MODAL -->
	<div class="modal" id="addCustomerPopup">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 540px;">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Enter Customer Information</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<label> First Name</label> <input type="text" class="form-control"
						name="firstName" id="firstName" required> <label>Last
						Name</label> <input type="text" class="form-control" name="lastName"
						id="lastName" required> <label>Email</label> <input
						type="text" class="form-control" name="email" id="email" required>
					<label> Mobile</label> <input type="text" class="form-control"
						name="mobile" id="mobile" required> <label>Age</label> <input
						type="text" class="form-control" name="age" id="age" required>
					<label> Company </label> <input type="text" class="form-control"
						name="company" id="company" required> 
						<label> Password</label> 
						<input type="password" class="form-control"
						name="password" id="password" required>
						

					
				</div>
				
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onClick="sendData()">Submit</button>
				</div>

			</div>
		</div>
	</div>


	<!-- ADD ROLE MODAL -->
	<div class="modal" id="addRolePopup">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 540px;">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Role Information</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<label for="roleName"> Role Name</label> <input type="text"
						class="form-control" name="firstName" id="roleName" required>

					<label for="roleDescription">Role Description</label> <input
						type="text" class="form-control" name="lastName"
						id="roleDescription" required>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onClick="addRole()">Submit</button>
				</div>
			</div>
		</div>
	</div>


	<!-- EDIT CUSTOMER MODAL -->
	<div class="modal" id="editCustomerPopup">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 540px;">
			
			<input type="hidden" id="ecid">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Edit Customer</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="cfname">First Name</label> 
							<input type="text" class="form-control" name="cfname" id="cfname">
						</div>
						<div class="form-group col-md-6">
							<label for="clname">Last Name</label> 
							<input type="text" class="form-control" name="clname" id="clname">
						</div>
					</div>
					<label for="cemail">Email</label> <input type="text"
						class="form-control" name="cemail" id="cemail" required>
						<hr/>
						<!-- <img src="" id="rprofilePic" style="height: 110px;"> -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						onClick="editCustomer()">Edit</button>
				</div>  
			</div>
		</div>
	</div>
	
	
	
	<!-- EDIT role information MODAL -->
	<div class="modal" id="edit_role_info">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 540px;">
			
			<input type="hidden" id="role_id">
			
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Edit role info</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="role_name">Role Name</label> 
							<input type="text" class="form-control" name="role_name" id="role_name">
						</div>
						<div class="form-group col-md-6">
							<label for="role_description">Role Description</label> 
							<input type="text" class="form-control" name="role_description" id="role_description">
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						onClick="edit_role()">Edit</button>
				</div>  
			</div>
		</div>
	</div>
	
	
	
	
	
	
	<!-- CHANGE PASSWORD MODAL -->
	<div class="modal" id="changePasswordPopup">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 540px;">
			
			<input type="text" id="reset_id" value="${sessionScope.customer.cid}">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Change Password</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="new_password">New Password</label> 
							<input type="text" class="form-control" name="new_password" id="new_password">
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onClick="changePassword()">Edit</button>
				</div>  
			</div>
		</div>
	</div>
	
	
	

</body>
</html>