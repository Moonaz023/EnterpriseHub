var optionsHtml = "";

$(document).ready(function () {
	getAllIngredients();
    $.ajax({
        type: "GET",
        url: "/getAllProducts",
        success: function (response) {
            // Populate the dropdown with the fetched products
            for (var i = 0; i < response.length; i++) {
                $('#product').append('<option value="' + response[i].id + '">' + response[i].name + '</option>');
                optionsHtml += '<option value="' + response[i].id + '">' + response[i].name + '</option>';
            }
        },
        error: function (err) {
            alert("Error: " + JSON.stringify(err));
            console.error("Error:", err);
        }
    });
});

//====================================Edit==========================================================================
function editRecord(id) {
    var record = data.find(function (item) {
        return item.id === id;
    });

    var editFormHtml = `
        <h2>Edit Production Record</h2>
        <form id="editForm" name="editForm" class="edit-form" action="@{/update}" method="post">
            <input type="hidden" id="id" name="id" value="${record.id}"><br>
            <label for="editProduct">Product</label>
            <select id="editProduct" name="product">${optionsHtml}</select><br>
            <label for="editdateOfProduction">Production Date</label>
            <input type="text" id="editdateOfProduction" name="dateOfProduction" value="${record.dateOfProduction}"><br>
            <label for="editProductionQuantity">Production Quantity</label>
            <input type="text" id="editProductionQuantity" name="productionQuantity" value="${record.productionQuantity}"><br>
            <button type="button" id="update" class="btn btn-success">Save</button>
            <button type="button" id="cancel" class="btn btn-primary">Cancel</button>
        </form>
    `;

    // Show the edit form
    $("#editFormContainer").html(editFormHtml).show();

    // Hide the container
    $(".container").addClass("hidden");

    // Set the selected product in the dropdown
    $("#editProduct").val(record.product.id);

    // Attach click event for the update button
    $("#update").click(function (event) {
        // Get the form associated with the clicked button
        var editForm = $("#editForm");

        // Prevent the default form submission
        event.preventDefault();
        
        var url = "";
       // if (record.product.id == editForm.find("select[name='product']").val() && record.productionQuantity == editForm.find("input[name='productionQuantity']").val())
			url = "/admin/updateProduction";
		//else  (record.product.id == editForm.find("select[name='product']").val() && record.productionQuantity != editForm.find("input[name='productionQuantity']").val())
		//	url = "/admin/updateProductionQuantity";
	//	else
		//	url = "/admin/updateProduction2";
      // console.log("Selected Product:", editForm.find("select[name='product']").val());

       // console.log("Form Data:", editForm.serialize());
        $.ajax({
			
            type: "POST",
            url:url,
            data: editForm.serialize(),
            success: function (result) {
                // Handle success, e.g., update the UI
                alert("Production updated successfully!");
                $("#editFormContainer").empty().hide();
                $(".container").removeClass("hidden");
                getAllProductionRecords();
            },
            error: function (err) {
                alert("Error: " + JSON.stringify(err));
            }
        });
    });

    // Attach click event for the cancel button
    $("#cancel").click(function (event) {
        $("#editFormContainer").empty().hide();
        $(".container").removeClass("hidden");
        getAllProductionRecords();
    });
}
//====================================================save data=================================================
$(document).ready(function () {
    getAllProductionRecords();
    
    $("#insertProduction").click(function (event) {
        // Get the form associated with the clicked button
        var form = $("#formProduction");
        // Prevent the default form submission
        event.preventDefault();
        const formData = gatherFormData();
        // Make the AJAX request
        $.ajax({
            type: "POST",
            url: form.attr("action"),
            data: formData,
			//data: form.serialize(),
            
            success: function (result) {//console.log(form.serialize());
            if(result=="no")
            {
				alert("Not enough ingrediant for this production");
				
			}else{
                getAllProductionRecords();
                $("#formProduction")[0].reset();
                $("#recipeContainerdynamic").empty();}
            },
            error: function (err) {
                alert("Error: " + JSON.stringify(err));
            }
        });
    });
    
});

///==================================view data====================================================

var data = "";

function getAllProductionRecords() {
    $.ajax({
        type: "GET",
        url: "/admin/getAllProductions", // Replace with your actual endpoint
        success: function (response) {
            console.log(response); // Add this line
            data = response;
            $('#ProductionTable').DataTable().destroy();
            $('#ProductionTableResult').empty();

            for (i = 0; i < response.length; i++) {
                var editUrl = "/edit/" + response[i].id; // Replace with your actual edit URL
                var deleteUrl = "/delete/" + response[i].id; // Replace with your actual delete URL
                // console.log("Row data:", response[i]); // Add this line


                $("#ProductionTableResult").append(
                    '<tr>' +
                    '<td>' + response[i].product.name + '</td>' +
                    '<td>' + response[i].dateOfProduction + '</td>' +
                    '<td>' + response[i].productionQuantity + '</td>' +
                    '<td><a href="#" onclick="editRecord(' + data[i].id + ')">Edit</a></td>' +
                    '<td><a href="#" onclick="deleteRecord(' + response[i].id + ')">Delete</a></td>' +
                    '</tr>'
                );
            }

            // Initialize DataTables plugin
            $('#ProductionTable').DataTable();
        },
        error: function (err) {
            alert("Error: " + JSON.stringify(err));
            console.error("Error:", err);
        }
    });
}


//=========================================DELETE====================================================================

    function deleteRecord(id) {
        $.ajax({
            type: "DELETE",
            url: "/admin/deleteProduction?id=" + id,
            success: function (result) {
                // Refresh the table after successful deletion
                getAllProductionRecords();
            },
            error: function (err) {
                alert("Error deleting record: " + JSON.stringify(err));
            }
        });
    }
   //========================================================================================================================
   $("#addProduct").click(function() {
		var productInput = $("<select>").attr({
			class: "productInput halfinput",
			name: "ingredient"
		});

		if (ingredientlist) {
			productInput.append(`<option value="">Select Ingredient</option>`);
			ingredientlist.forEach(function(ingredient) {
				productInput.append(`<option value="${ingredient.id}">${ingredient.ingredientName}</option>`);
			});
		}
		var productQuantity = $("<input>").attr({
			type: "text",
			class: "productQuantity halfinput",
			name: "ingredientQuantity",
			placeholder: "Ingredient Quantity"
		});
		$("#usedIngrediantContainerDynamic").append(productInput).append('<span>&nbsp;</span>').append(productQuantity).append("<br>");
	});
	
var ingredientlist = "";

function getAllIngredients() {
	$.ajax({
		type: "GET",
		url: "/getAllIngredients",
		success: function(responsex) {

			ingredientlist = responsex;
			var dropdown = $("#productInput");
			dropdown.empty();
			dropdown.append('<option value="">Select Ingredient</option>');
			$.each(responsex, function(index, ingredient) {
				dropdown.append('<option value="' + ingredient.id + '">' + ingredient.ingredientName + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}









//--------------------NEWWWWWWWWWWW---------------------------------

function addRecipeItem() {
            const container = document.getElementById('recipeContainerdynamic');
            const newItem = document.createElement('div');
            newItem.classList.add('recipeItem');

            let ingredientOptions = '<option value="">Select Ingredient</option>';
            ingredientlist.forEach(function(ingredient) {
                ingredientOptions += `<option value="${ingredient.id}">${ingredient.ingredientName}</option>`;
            });

            newItem.innerHTML = `
                <select type="text" class="ingredient productInput halfinput" required>
                    ${ingredientOptions}
                </select>
                <input type="number" class="quantity productQuantity halfinput" placeholder="Quantity" required>
                <i onclick="removeRecipeItem(this)" style="font-size:30px; color:red" class="fa fa-trash" aria-hidden="true"></i>
            `;
            container.appendChild(newItem);
        }
        
               function removeRecipeItem(button) {
            const item = button.parentNode;
            item.parentNode.removeChild(item);
        }

        
        
function gatherFormData() {
    const dateOfProduction = document.getElementById('dateOfProduction').value;
    const product = document.getElementById('product').value;
    const productionQuantity = document.getElementById('productionQuantity').value;

    const container = document.getElementById('recipeContainer');
    const ingredientInputs = container.getElementsByClassName('ingredient');
    const quantityInputs = container.getElementsByClassName('quantity');

    let formData = `product=${product}&dateOfProduction=${dateOfProduction}&productionQuantity=${productionQuantity}`;

    for (let i = 0; i < ingredientInputs.length; i++) {
        let ingredientId = ingredientInputs[i].value;
        let quantity = quantityInputs[i].value;

        formData += `&recipe[${i}].ingredient.id=${ingredientId}&recipe[${i}].ingredientQuantity=${quantity}`;
    }
console.log(formData);
    return formData;
}

