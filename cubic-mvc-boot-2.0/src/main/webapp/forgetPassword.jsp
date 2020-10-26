<!DOCTYPE html>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<title>Forget Password!!!</title>
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
	<form style="width:60%; padding-left:40%;">
		<label>Email</label> <input type="text" class="form-control"
			name="email" id="email" required> <label> Debit card</label>
		<input type="text" class="form-control" name="debit_card"
			id="debit_card" required> 
			<!-- <label>CVV</label> <input
			type="text" class="form-control" name="cvv" id="cvv" required> -->

		<button type="submit" class="btn btn-primary" onClick="verifyUser()">Verify User</button>
		
	</form>



</body>
</html>