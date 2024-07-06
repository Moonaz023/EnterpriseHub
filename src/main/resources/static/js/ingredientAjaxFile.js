$(document).ready(function() {
	getAllIngredients();
	$("#insertIngredient").click(function() {
		// Get the form associated with the clicked button
		var form = $("#formIngredient");
		// Prevent the default form submission
		event.preventDefault();
		console.log("yes");
		// Make the AJAX request
		$.ajax({
			type: "POST",
			url: "/addIngredient",
			data: form.serialize(),
			success: function(result) {
				getAllIngredients();
				$("#formIngredient")[0].reset();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});
});

//==========================================show table========================================================
var allIngredientsdata = "";

function getAllIngredients() {
	$.ajax({
		type: "GET",
		url: "/getAllIngredients",
		success: function(response) {
			console.log(response);
			allIngredientsdata = response;
			$('#IngredientTable').DataTable().destroy();
			$('#IngredientResult').empty();

			for (i = 0; i < response.length; i++) {
				$("#IngredientResult").append(
					'<tr class="tr">' +
					'<td>' + response[i].ingredientCode + '</td>' +
					'<td>' + response[i].ingredientName + '</td>' +

					'<td><a href="#" onclick="editIngredientRecord(' + allIngredientsdata[i].id + ')">Edit</a></td>' +
					'<td><a href="#" onclick="deleteIngredientRecord(' + response[i].id + ')">Delete</a></td>' +
					'</tr>'
				);
			}
			// Initialize DataTables plugin
			$('#IngredientTable').DataTable();
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

//=========================================DELETE====================================================================

function deleteIngredientRecord(id) {
	$.ajax({
		type: "DELETE",
		url: "/deleteIngredient?id=" + id,
		success: function(result) {

			getAllIngredients();
		},
		error: function(err) {
			alert("Error deleting record: " + JSON.stringify(err));
		}
	});
}

//====================================Edit==========================================================================

function editIngredientRecord(id) {
	var record = allIngredientsdata.find(function(item) {
		return item.id === id;
	});

	var editFormHtml = `
        <h2>Edit Ingredient Record</h2>
        <form id="editForm" name="editForm" class="edit-form" action="@{/update}" method="post">
        
        <input type="hidden" id="id" name="id" value="${record.id}"><br>
        <label for="ingredientCode">Ingredient Code</label>
		<input type="text" class="form-control" name="ingredientCode" id="ingredientCode" value="${record.ingredientCode}">

		<label for="ingredientName">Ingredient Name</label>
		<input type="text" class="form-control" name="ingredientName" id="ingredientName" value="${record.ingredientName}"><br>
        
            <button type="button" id="updateIngredient" class="btn btn-success">Save</button>
            <button type="button" id="cancelIngredient" class="btn btn-primary">Cancel</button>
        </form>
    `;

	// Show the edit form
	$("#editFormContainer").html(editFormHtml).show();

	// Hide the container
	$(".container").addClass("hidden");

	// Attach click event for the update button
	$("#updateIngredient").click(function(event) {
		// Get the form associated with the clicked button
		var editForm = $("#editForm");

		// Prevent the default form submission
		event.preventDefault();
		console.log(editForm.serialize())
		$.ajax({
			type: "POST",
			url: "/updateIngredient",
			data: editForm.serialize(),
			success: function(result) {
				// Handle success, e.g., update the UI
				alert("Ingredient updated successfully!");
				$("#editFormContainer").empty().hide();
				$(".container").removeClass("hidden");
				getAllIngredients();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});

	// Attach click event for the cancel button
	$("#cancelIngredient").click(function(event) {
		$("#editFormContainer").empty().hide();
		$(".container").removeClass("hidden");
		getAllIngredients();
	});
}