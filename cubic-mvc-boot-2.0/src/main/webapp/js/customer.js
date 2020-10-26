function openAddRolePopup() {
	$("#addRolePopup").modal("show");
}


function verifyUser(){
	
}

function openEditRolePopUp(rid, name, description){
	
	$("#edit_role_info").modal("show")
	var rid = $('#role_id').val(rid);
	var name = $('#role_name').val(name);
	var description = $('#role_description').val(description);
	
	
}



function edit_role(){
	var rid = $('#role_id').val();
	var name = $('#role_name').val();
	var description = $('#role_description').val();
	
	var obj = {
		rid: rid,
		name: name,
		description: description
	}

	var jsonData = JSON.stringify(obj);

	const options = {
		method: 'PUT',
		body: jsonData,
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};

	var promise = fetch("v3/roles/edit", options);
	promise.then(response => response.text())
		.then(function(data) {
			console.log(data)
		});
}







function deleteRole(rid){ 
	fetch("v3/roles?rid=" + rid).then(function(response) {
		return response.json();
	}).then(function(data) {
		console.log(data);

		$("#roid").val(data.rid);
		$("#roName").val(data.name);
		$("#roDes").val(data.description);
		
		confirmDeleteRole();

	});
}


function confirmDeleteRole(){
	var rid = $("#roid").val();
	var name = $("#roName").val();
	var description = $("#roDes").val();
	
	var cdata = {
		rid: rid,
		name: name,
		description: description
	};

	//convert above object into JSON
	var jsonData = JSON.stringify(cdata);

	//Creating 
	const options = {
		method: 'DELETE',
		body: jsonData,
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};

	var promise = fetch("v3/roles/delete", options);
	promise.then(response => response.json())
		.then(function(data) {
		});
		
}


function openCutomerEditPopup(cid) {
	fetch("v3/customers?cid=" + cid).then(function(response) {
		return response.json();
	}).then(function(data) {
		console.log(data);

		$("#cfname").val(data.firstName);
		$("#clname").val(data.lastName);
		$("#ecid").val(data.cid);
		$("#cemail").val(data.email);
		$("#rprofilePic").attr("src", "/customer/photo?cid=" + cid);
		$("#editCustomerPopup").modal("show");

	});
}


function changePassword(){
	var id = $("#reset_id").val();
	var password = $("#new_password").val();
	
	var obj = {
		id: id,
		password: password
	}
	
	var jsonData = JSON.stringify(obj);
	
	const options = {
		method: 'PUT',
		body: jsonData,
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};
	
	var promise = fetch("v3/changePassword", options);
	promise.then(response => response.text())
		.then(function(data) {
			console.log(data);
		});
	$("#changePasswordPopup").modal("hide");
	
}


function editCustomer() {
	var firstName = $('#cfname').val();
	var lastName = $('#clname').val();
	var email = $('#cemail').val();
	var cid = $('#ecid').val();
	/*$("#rprofilePic").attr("src","customer/photo?cid="+cid);*/

	var obj = {
		cid: cid,
		firstName: firstName,
		lastName: lastName,
		email: email
	}

	var jsonData = JSON.stringify(obj);

	const options = {
		method: 'PUT',
		body: jsonData,
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};

	var promise = fetch("v3/customers", options);
	promise.then(response => response.text())
		.then(function(data) {
			loadCustomer();
			
			
		});
	alert("Customer information updated!")
	$("#editCustomerPopup").modal("hide");
}



function addRole() {
	var name = $("#roleName").val();
	var description = $("#roleDescription").val();


	var roleData = {
		name: name,
		description: description
	};

	//convert above object into JSON
	var jsonData = JSON.stringify(roleData);

	//Creating 
	const options = {
		method: 'POST',
		body: jsonData,
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};

	var promise = fetch("v3/roles", options);
	promise.then(response => response.json())
		.then(function(data) {
			console.log(data);
			
		});
	alert("New role added!")
	$("#addRolePopup").modal("hide");
}


function updateRoles() {
	//Http client ->>
	var pcid = $("#trcid").val();
	//creating array in JavaScript
	var editedRolesId = [];
	$.each($("input[name='srole']:checked"), function() {
		editedRolesId.push($(this).val());
	});
	//Making JavaScript object
	var data = {
		cid: pcid,
		sroles: editedRolesId
	};
	var jsonData = JSON.stringify(data);

	//Creating GET,POST,PUT,DELETE
	const options = {
		method: 'PUT',
		body: jsonData,
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};

	var promise = fetch("v3/customers/roles", options);
	//public String
	promise.then(response => response.text())
		.then(function(data) {
			console.log(data);
			$("#rolePopup").modal("hide");
		});

}


function generateCard() {
	var firstName = $("#tpfname").val();
	var lastName = $("#tplname").val();
	var cid = $("#tpcid").val();
	var debitCard = $("#debitCard").val();

	// <img src="img/credit-card-front-template.jpg" id="cphoto">
	var tete = new Date();
	$("#cphoto").attr("src", "loadCrediCard?cid=" + cid + "&firstName=" + firstName + "&lastName=" + lastName + "&time=" + tete);
}

function openPopup(pcid) {
	//	@GetMapping(value="/customers",params={"cid"})
	fetch("v3/customers?cid=" + pcid).then(function(response) {
		return response.json();
	}).then(function(data) {
		console.log(data);
		$("#pname").html("Name: " + data.firstName + " " + data.lastName);
		$("#pemail").html("Email : " + data.email);

		//setting these values inside hidden field
		$("#tpfname").val(data.firstName);
		$("#tplname").val(data.lastName);
		$("#tpcid").val(pcid);

		$("#crediCardPopup").modal("show");
	});
}

function editRolePopup(cid, firstName, lastName, email) {
	$("#fName").html(firstName);
	$("#lName").html(lastName);
	$("#remail").html(email);
	$("#trcid").val(cid);

	/* $("#rprofilePic").attr("src","customer/photo?cid="+cid); */

	//first call to fetch only customer specific roles
	fetch("v3/customers/roles?cid=" + cid).then(function(response) {
		return response.json();
	}).then(function(myRoles) {
		console.log(myRoles);
		var tbcontents = "";

		//Second call to fetch all the roles
		fetch("v3/roles").then(function(response) {
			return response.json();
		}).then(function(roles) {
			console.log(roles);
			var tdata = "";
			roles.forEach((pdata) => {
				tdata = tdata + '<tr>';
				tdata = tdata + '<td>' + pdata.rid + '</td>';
				tdata = tdata + '<td>' + pdata.name + '</td>';

				var yes = myRoles.includes(pdata.name);
				console.log(yes);
				if (yes) {
					tdata = tdata + '<td><input type="checkbox" name="srole" id="srole" value="'+pdata.rid+'" checked></td>';
				} else {
					tdata = tdata + '<td><input type="checkbox" name="srole" id="srole" value="'+pdata.rid+'" ></td>';
				}
				tdata = tdata + '<td> <button type="button" class="btn btn-primary" onClick="openEditRolePopUp(' +pdata.rid + ',\'' + pdata.name + '\',\'' + pdata.description + '\');">Edit </button> <button type="button" class="btn btn-danger" onClick="deleteRole(' + pdata.rid + ');"> Delete </button> </td>';
				tdata = tdata + '</tr>';
			});
			document.getElementById("crdata").innerHTML = tdata;
		});

		$("#rolePopup").modal("show");
	});
}


function loadCustomer() {
	fetch("v3/customers").then(function(response) {
		return response.json();
	}).then(function(data) {
		//var pk=[];
		//pk.push("Nagendra");
		var tdata = "";
		data.forEach((pdata) => {
			console.log(pdata);
			tdata = tdata + '<tr>';
			tdata = tdata + '<td>' + pdata.cid + '</td>';
			tdata = tdata + '<td>' + pdata.firstName + '</td>';
			tdata = tdata + '<td>' + pdata.lastName + '</td>';
			tdata = tdata + '<td>' + pdata.email + '</td>';
			tdata = tdata + '<td> <button type="button" class="btn btn-primary" onclick="openCutomerEditPopup(' + pdata.cid + ');">EDIT</button></td>';
			tdata = tdata + '<td> <button type="button" class="btn btn-warning" onclick="editRolePopup(' +pdata.cid + ',\'' + pdata.firstName + '\',\'' + pdata.lastName + '\',\'' + pdata.email + '\');">Role</button></td>';
            /* tdata=tdata+'<td>'+pdata.debitcard+'</td>';
            tdata=tdata+'<td>'+pdata.valid+'</td>';
            tdata=tdata+'<td>'+pdata.cvv+'</td>'; */
			tdata = tdata + '<td>' + pdata.mobile + '</td>';
			tdata = tdata + '<td>coming soon</td>';
			tdata = tdata + '<td>' + pdata.company + '</td>';
			tdata = tdata + '<td> <button type="button" class="btn btn-primary" onclick="openPopup(' + pdata.cid + ');">GEN</button></td>';
			tdata = tdata + '</tr>';
		});
		document.getElementById("cdata").innerHTML = tdata;
	});
}

function addCustomer() {
	$("#addCustomerPopup").modal("show");
}

function sendData() {
	var firstName = $("#firstName").val();
	var lastName = $("#lastName").val();
	var email = $("#email").val();
	var mobile = $("#mobile").val();
	var age = $("#age").val();
	var company = $("#company").val();
	var password = $("#password").val();

	var cdata = {
		firstName: firstName,
		lastName: lastName,
		email: email,
		mobile: mobile,
		age: age,
		company: company,
		password: password
	};

	//convert above object into JSON
	var jsonData = JSON.stringify(cdata);

	//Creating 
	const options = {
		method: 'POST',
		body: jsonData,
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};

	var promise = fetch("v3/customers", options);
	promise.then(response => response.json())
		.then(function(data) {
			console.log(data);
			var statusCode = data.status;
			if (statusCode == "200"){
				loadCustomer();
				$("#addCustomerPopup").modal("hide");
			}else{
				alert("customer addition failed!")
			}
		});
}