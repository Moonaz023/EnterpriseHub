$(document).ready(function() {
	getVendors();
	getAllIngredient();
	getAllPurchasedIngredients();
});
var vendor = "";

function getVendors() {
	$.ajax({
		type: "GET",
		url: "/getAllVendors",
		success: function(vendor_response) {
			console.log(vendor_response);
			vendor = vendor_response;


			var dropdown = $("#vendor");
			dropdown.empty();
			dropdown.append('<option value="">Select Vendor</option>');
			$.each(vendor_response, function(index, vendor) {
				dropdown.append('<option value="' + vendor.id + '">' + vendor.name + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}
var Ingredientdata = "";
function getAllIngredient() {
	$.ajax({
		type: "GET",
		url: "/getAllIngredients",
		success: function(responseIngredient) {

			Ingredientdata = responseIngredient;
			var dropdown = $("#ingredient");
			dropdown.empty();
			dropdown.append('<option value="">Select Ingredient</option>');
			$.each(responseIngredient, function(index, ingredient) {
				dropdown.append('<option value="' + ingredient.id + '">' + ingredient.ingredientName + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

$("#insertPurchase").click(function() {

	var form = $("#formPurchaseIngredient");
	event.preventDefault();
	//console.log("yes");
	$.ajax({
		type: "POST",
		url: "/savePurchasedIngredient",
		data: form.serialize(),
		success: function(result) {
			getAllPurchasedIngredients();
			$("#formPurchaseIngredient")[0].reset();
		},
		error: function(err) {
			alert("Error: " + JSON.stringify(err));
		}
	});
});

//==========================================show table========================================================
var allPurchasedIngredientsdata = "";

function getAllPurchasedIngredients() {
	$.ajax({
		type: "GET",
		url: "/getAllPurchasedIngredients",
		success: function(response) {
			//console.log(response); 
			allPurchasedIngredientsdata = response;
			$('#PurchasedIngredientsTable').DataTable().destroy();
			$('#PurchasedIngredientsTableResult').empty();

			for (i = 0; i < response.length; i++) {
				$("#PurchasedIngredientsTableResult").append(
					'<tr class="tr">' +
					'<td>' + response[i].ingredient.ingredientName + '</td>' +
					'<td>' + response[i].dateOfPurchase + '</td>' +
					'<td>' + response[i].quantity + '</td>' +
					'<td>' + response[i].bill + '</td>' +
					'<td>' + response[i].paid + '</td>' +
					'<td>' + response[i].vendor.name + '</td>' +

					'<td><a href="#" onclick="editIngredientPurchaseRecord(' + allPurchasedIngredientsdata[i].id + ')">Edit</a></td>' +
					'<td><a href="#" onclick="deleteIngredientPurchaseRecord(' + response[i].id + ')">Delete</a></td>' +
					'</tr>'
				);
			}
			// Initialize DataTables plugin
			$('#PurchasedIngredientsTable').DataTable();
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}
function deleteIngredientPurchaseRecord(id) {
	$.ajax({
		type: "DELETE",
		url: "/deleteIngredientPurchaseRecord?id=" + id,
		success: function(result) {
			getAllPurchasedIngredients();
		},
		error: function(err) {
			alert("Error deleting record=" + Jeson.stringify(err));
		}
	});
}

function editIngredientPurchaseRecord(id) {
	var existing = allPurchasedIngredientsdata.find(function(record) {
		return record.id === id;
	});
	var editFormHtml = `
        <h2>Edit Ingredient Purchase</h2>
        <form id="editForm" name="editForm" class="edit-form" action="@{/update}" method="post">
        
        <input type="hidden" id="id" name="id" value="${existing.id}"><br>
        
						<label for="ingredient">Ingredient</label>
						<select class="form-control" id="editingredient" name="ingredient">
							<option value="${existing.ingredient.id}">${existing.ingredient.ingredientName}</option>	
						</select>
					
						<label for="dateOfPurchase">Purchase Date</label>
						<input type="date" class="form-control" id="dateOfPurchase" name="dateOfPurchase"
							value="${existing.dateOfPurchase}">
				

						<label for="purchaseQuantity">Purchase Quantity</label>
						<input type="text" class="form-control" id="purchaseQuantity" name="quantity"
							value="${existing.quantity}">
					
						<label for="bill">Bill</label>
						<input type="number" class="form-control" id="bill" name="bill"
							value="${existing.bill}">
							
						<label for="Paid">Paid</label>
						<input type="number" class="form-control" id="Paid" name="paid"
							value="${existing.paid}">
					
					<label for="vendor">Vendor</label>
					<select class="form-control" id="editvendor" name="vendor">
						<option value="${existing.vendor.id}">${existing.vendor.name}</option>	
							
						</select>

            <button type="button" id="updateIngredientPurchase" class="btn btn-success">Save</button>
            <button type="button" id="cancelIngredientPurchase" class="btn btn-primary">Cancel</button>
        </form>
    `;
	// Show the edit form
	$("#editFormContainer").html(editFormHtml).show();

	// Hide the container
	$(".container").addClass("hidden");


	var editvendor = $("#editvendor");
	var filteredVendorss = vendor.filter(function(vendor) {
		return vendor.id !== existing.vendor.id;
	});
	$.each(filteredVendorss, function(index, vendor) {

		editvendor.append('<option value="' + vendor.id + '">' + vendor.name + '</option>');
	});
	var editdropdown = $("#editingredient");
	// Filter out the existing ingredient from the list
	var filteredIngredients = Ingredientdata.filter(function(ingredient) {
		return ingredient.id !== existing.ingredient.id;
	});

	$.each(filteredIngredients, function(index, ingredient) {

		editdropdown.append('<option value="' + ingredient.id + '">' + ingredient.ingredientName + '</option>');
	});
	
	
	
		// Attach click event for the update button
	$("#updateIngredientPurchase").click(function(event) {
		// Get the form associated with the clicked button
		var editForm = $("#editForm");

		// Prevent the default form submission
		event.preventDefault();
		console.log(editForm.serialize())
		$.ajax({
			type: "POST",
			url: "/updateIngredientPurchase",
			data: editForm.serialize(),
			success: function(result) {
				// Handle success, e.g., update the UI
				alert("Ingredient Purchase updated successfully!");
				$("#editFormContainer").empty().hide();
				$(".container").removeClass("hidden");
				getAllPurchasedIngredients();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});

	// Attach click event for the cancel button
	$("#cancelIngredientPurchase").click(function(event) {
		$("#editFormContainer").empty().hide();
		$(".container").removeClass("hidden");
		getAllPurchasedIngredients();
	});

}