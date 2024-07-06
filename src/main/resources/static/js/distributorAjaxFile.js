//===========================================Insert=========================================================
$(document).ready(function() {
	getDistributors();
	$("#insert").click(function() {
		// Get the form associated with the clicked button
		var form = $("#formDistributor");
		// Prevent the default form submission
		event.preventDefault();
		// Make the AJAX request
		$.ajax({
			type: "POST",
			url: form.attr("action"),
			data: form.serialize(),
			success: function() {
				getDistributors();
				$("#formDistributor")[0].reset();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});
});

//==========================================show table========================================================
var distributor = "";

function getDistributors() {
	$.ajax({
		type: "GET",
		url: "/getAllDistributors", // Replace with your actual endpoint
		success: function(distributor_response) {
			console.log(distributor_response); // Add this line
			distributor = distributor_response;
			$('#distributor').DataTable().destroy();
			$('#distributor_result').empty();

			for (i = 0; i < distributor_response.length; i++) {
				var editUrl = "/edit/" + distributor_response[i].id; // Replace with your actual edit URL
				var deleteUrl = "/delete/" + distributor_response[i].id; // Replace with your actual delete URL
				// console.log("Row data:", response[i]); // Add this line

				$("#distributor_result").append(
					'<tr class="tr">' +

					'<td><a href="DistributorProfile/' + distributor_response[i].id + '" >' + distributor_response[i].name + '</a></td>' +
					'<td>' + distributor_response[i].address + '</td>' +
					'<td>' + distributor_response[i].phone + '</td>' +
					'<td>' + distributor_response[i].email + '</td>' +
					'<td>' + distributor_response[i].total_order + '</td>' +
					//  '<td>' + response[i].discount + '</td>' +

					'<td><a href="#" onclick="editRecord(' + distributor[i].id + ')">Edit</a></td>' +
					'<td><a href="#" onclick="deleteRecord(' + distributor_response[i].id + ')">Delete</a></td>' +
					'</tr>'
				);
			}


			// Initialize DataTables plugin
			$('#distributor').DataTable();
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
		url: "/admin/deleteDistributor?id=" + id,
		success: function() {
			// Refresh the table after successful deletion
			getDistributors();
		},
		error: function(err) {
			alert("Error deleting record: " + JSON.stringify(err));
		}
	});
}

//====================================Edit==========================================================================

function editRecord(id) {
	var record = distributor.find(function(item) {
		return item.id === id;
	});

	var editFormHtml = `
        <h2>Edit Product Record</h2>
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
			url: "/admin/updateDistributor",
			data: editForm.serialize(),
			success: function(result) {
				// Handle success, e.g., update the UI
				alert("Distributor updated successfully!");
				$("#editFormContainer").empty().hide();
				$(".container").removeClass("hidden");
				getDistributors();
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
		getDistributors();
	});
}