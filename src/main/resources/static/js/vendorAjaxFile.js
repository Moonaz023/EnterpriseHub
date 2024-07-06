//===========================================Insert=========================================================
$(document).ready(function() {
	getVendors();
	$("#insert").click(function() {
		// Get the form associated with the clicked button
		var form = $("#formVendor");
		// Prevent the default form submission
		event.preventDefault();
		// Make the AJAX request
		$.ajax({
			type: "POST",
			url: form.attr("action"),
			data: form.serialize(),
			success: function() {
				getVendors();
				$("#formVendor")[0].reset();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});
	$("#resett").click(function() {
		
		$("#formVendor")[0].reset();
	
	});
});

//==========================================show table========================================================
var vendor = "";

function getVendors() {
	$.ajax({
		type: "GET",
		url: "/getAllVendors", 
		success: function(vendor_response) {
			console.log(vendor_response); 
			vendor = vendor_response;
			$('#Vendor').DataTable().destroy();
			$('#Vendor_result').empty();

			for (i = 0; i < vendor_response.length; i++) {
				$("#Vendor_result").append(
					'<tr class="tr">' +
					
					'<td>' + vendor_response[i].name + '</td>' +
					'<td>' + vendor_response[i].address + '</td>' +
					'<td>' + vendor_response[i].phone + '</td>' +
					'<td>' + vendor_response[i].email + '</td>' +
					

					'<td><a href="#" onclick="editRecord(' + vendor[i].id + ')">Edit</a></td>' +
					'<td><a href="#" onclick="deleteRecord(' + vendor_response[i].id + ')">Delete</a></td>' +
					'</tr>'
				);
			}


			// Initialize DataTables plugin
			$('#Vendor').DataTable();
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

//=========================================DELETE====================================================================

function deleteRecord(id) {
	$.ajax({
		type: "DELETE",
		url: "/admin/deleteVendor?id=" + id,
		success: function() {
			// Refresh the table after successful deletion
			getVendors();
		},
		error: function(err) {
			alert("Error deleting record: " + JSON.stringify(err));
		}
	});
}

//====================================Edit==========================================================================

function editRecord(id) {
	var record = vendor.find(function(item) {
		return item.id === id;
	});

	var editFormHtml = `
        <h2>Edit Vendor Record</h2>
        <form id="editForm" name="editForm" class="edit-form" action="@{/update}" method="post">
            <input type="hidden" id="id" name="id" value="${record.id}"><br>
            <label for="editName">Name</label>
            <input type="text" id="editProductCode" name="name" value="${record.name}"><br>
            <label for="editAddress">Address</label>
            <input type="text" id="editAddress" name="address" value="${record.address}"><br>
            <label for="editPhone">Phone</label>
            <input type="text" id="editPhone" name="phone" value="${record.phone}"><br>
            <label for="editEmail">Email</label>
            <input type="text" id="editEmail" name="email" value="${record.email}"><br>
            <button type="button" id="update" class="btn btn-success">Save</button>
            <button type="button" id="cancel" class="btn btn-primary">Cancel</button>
        </form>
    `;

	// Show the edit form
	$("#editFormContainer").html(editFormHtml).show();

	// Hide the container
	$(".container").addClass("hidden");

	// Attach click event for the update button
	$("#update").click(function(event) {
		// Get the form associated with the clicked button
		var editForm = $("#editForm");

		// Prevent the default form submission
		event.preventDefault();
		console.log(editForm.serialize())
		$.ajax({
			type: "POST",
			url: "/admin/updateVendor",
			data: editForm.serialize(),
			success: function(result) {
				// Handle success, e.g., update the UI
				alert("Vendor updated successfully!");
				$("#editFormContainer").empty().hide();
				$(".container").removeClass("hidden");
				getVendors();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});

	// Attach click event for the cancel button
	$("#cancel").click(function(event) {
		$("#editFormContainer").empty().hide();
		$(".container").removeClass("hidden");
		getVendors();
	});
}