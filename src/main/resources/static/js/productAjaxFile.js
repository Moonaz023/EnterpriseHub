//===========================================Insert=========================================================
$(document).ready(function() {
	getAllRecords();
	$("#insert").click(function() {
		// Get the form associated with the clicked button
		var form = $("#formProduct");
		// Prevent the default form submission
		event.preventDefault();
		// Make the AJAX request
		$.ajax({
			type: "POST",
			url: form.attr("action"),
			data: form.serialize(),
			success: function(result) {
				getAllRecords();
				$("#formProduct")[0].reset();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});
});
//==========================================show table========================================================
var data = "";

function getAllRecords() {
	$.ajax({
		type: "GET",
		url: "/getAllProducts", 
		success: function(response) {
			console.log(response); 
			data = response;
			$('#example').DataTable().destroy();
			$('#tableresult').empty();

			for (i = 0; i < response.length; i++) {
				var editUrl = "/edit/" + response[i].id; // Replace with your actual edit URL
				var deleteUrl = "/delete/" + response[i].id; // Replace with your actual delete URL
				// console.log("Row data:", response[i]); // Add this line

				$("#tableresult").append(
					'<tr class="tr">' +
					'<td>' + response[i].productCode + '</td>' +
					'<td>' + response[i].name + '</td>' +
					'<td>' + response[i].category + '</td>' +
					'<td>' + response[i].price + '</td>' +
					//                    '<td>' + response[i].discount + '</td>' +

					'<td><a href="#" onclick="editRecord(' + data[i].id + ')">Edit</a></td>' +
					'<td><a href="#" onclick="deleteRecord(' + response[i].id + ')">Delete</a></td>' +
					'</tr>'
				);
			}


			// Initialize DataTables plugin
			$('#example').DataTable();
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

//====================================Edit==========================================================================

function editRecord(id) {
	var record = data.find(function(item) {
		return item.id === id;
	});

	var editFormHtml = `
        <h2>Edit Product Record</h2>
        <form id="editForm" name="editForm" class="edit-form" action="@{/update}" method="post">
            <input type="hidden" id="id" name="id" value="${record.id}"><br>
            <label for="editProductCode">product Code</label>
            <input type="text" id="editProductCode" name="productCode" value="${record.productCode}"><br>
            <label for="editName">Name</label>
            <input type="text" id="editName" name="name" value="${record.name}"><br>
            <label for="editCategory">Category</label>
            <input type="text" id="editCategory" name="category" value="${record.category}"><br>
            <label for="editPrice">Price</label>
            <input type="text" id="editPrice" name="price" value="${record.price}"><br>
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
			url: "/admin/updateProduct",
			data: editForm.serialize(),
			success: function(result) {
				// Handle success, e.g., update the UI
				alert("product updated successfully!");
				$("#editFormContainer").empty().hide();
				$(".container").removeClass("hidden");
				getAllRecords();
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
		getAllRecords();
	});
}

//=========================================DELETE====================================================================

function deleteRecord(id) {
	$.ajax({
		type: "DELETE",
		url: "/admin/deleteProduct?id=" + id,
		success: function(result) {
			// Refresh the table after successful deletion
			getAllRecords();
		},
		error: function(err) {
			alert("Error deleting record: " + JSON.stringify(err));
		}
	});
}
