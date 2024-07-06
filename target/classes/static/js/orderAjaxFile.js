$(document).ready(function() {
	getAllProduct();
	getAllDistributors();
	getOrderList();

	$("#insertOrder").click(function() {
		var form = $("#orderForm");
		event.preventDefault();
		var formData = form.serializeArray();
		var products = [];
		if (!form[0].checkValidity()) {
			alert("Please fill out all required fields.");
			return;
		}
		$.ajax({
			type: "POST",
			url: "/addOrder",
			data: formData,
			success: function(result) {
				console.log(result);
				$("#productsContainerDynamic").empty();
				document.getElementById("orderForm").reset();
				getOrderList();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});

	$("#resetOrder").click(function() {
		$("#productsContainerDynamic").empty();
		document.getElementById("orderForm").reset();

	});

	$("#addProduct").click(function() {
		var productInput = $("<select>").attr({
			class: "productInput halfinput",
			name: "product"
		});

		if (data) {
			productInput.append(`<option value="">Select Product</option>`);
			data.forEach(function(product) {
				productInput.append(`<option value="${product.id}">${product.name}</option>`);
			});
		}
		var productQuantity = $("<input>").attr({
			type: "text",
			class: "productQuantity halfinput",
			name: "productQuantity",
			placeholder: "Product Quantity"
		});
		$("#productsContainerDynamic").append(productInput).append('<span>&nbsp;</span>').append(productQuantity).append("<br>");
	});


});

var data = "";

function getAllProduct() {
	$.ajax({
		type: "GET",
		url: "/getAllProducts",
		success: function(response) {

			data = response;
			var dropdown = $("#productInput");
			dropdown.empty();
			dropdown.append('<option value="">Select Product</option>');
			$.each(response, function(index, product) {
				dropdown.append('<option value="' + product.id + '">' + product.name + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}
var distributorOption = "";

function getAllDistributors() {
	$.ajax({
		type: "GET",
		url: "/getAllDistributors",
		success: function(distributor_response) {

			distributorOption = distributor_response;
			var dropdownDistributor = $("#distributor_id");
			dropdownDistributor.empty();
			dropdownDistributor.append('<option value="">Select Distributor</option>');
			$.each(distributor_response, function(index, distributor) {
				dropdownDistributor.append('<option value="' + distributor.id + '">' + distributor.name + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

//==========================================show table========================================================
var orderList = "";

function getOrderList() {
	$.ajax({
		type: "GET",
		url: "/getodd",
		success: function(orderList_response) {

			orderList = orderList_response;
			 $('#orderTable').DataTable().destroy();
			$('#order_result').empty();
			for (i = 0; i < orderList_response.length; i++) {

				$("#order_result").append(
					'<tr class="tr">' +
					'<td>' + orderList_response[i].orderDetails + '</td>' +
					'<td>' + orderList_response[i].date + '</td>' +
					'<td>' + orderList_response[i].distributor_id.name + '</td>' +
					'<td><a href="#" onclick="CheckOutValidation(' + orderList[i].id + ');">Checkout Now</a></td>' +
					'<td><a href="#" onclick="deleteRecord(' + orderList_response[i].id + ')"><i class="fa fa-ellipsis-v" style="font-size:24px"></i></a></td>' +
					'</tr>'
				);
			}
			// Initialize DataTables plugin
			$('#orderTable').DataTable();
		},
		error: function(err) {
			//alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

function CheckOutValidation(validity_id) {
	var CheckOutOrder = orderList.find(function(CheckOutOrder) {
		return CheckOutOrder.id === validity_id;
	});
	//console.log(validity_id);
	$.ajax({
		type: "GET",
		url: "/checkOutValidity?order_id=" + validity_id,
		data: validity_id,
		success: function(response) {

			if (response.success === true) {
				// checkOutOrderDiv(validity_id);
				var CheckOutForm = `
       <div class="CheckoutDiv">
             <h4>Checkout Order</h4>
            ${response.details.map(function(detail) {
					return `
					
					<div class="CheckoutContent">
					<div class="CheckoutContentLeft">
						<h6>${detail.productName} (*${detail.quantity})</h6> </div>
					<div class="CheckoutContentRight"> 
						<h6>${detail.price}</h6></div>
					</div>
					
					
					`;
				}).join('')}
			<hr>
			<div class="CheckoutContent">
			<div class="CheckoutContentLeft">
						<h4>Total</h4> </div>
			<div class="CheckoutContentRight"> 
			<h4> ${response.totalPrice}</h4>
			</div></div>
			<div class="formdiv">
			<form id="paymentForm">
			<input type="number" id="orderId" name="orderId" value="${validity_id}" class="hidden">
            <div>
                <label for="fullPaymentReceived">Full Payment Received</label>
                <input type="checkbox" id="fullPaymentReceived" onchange="togglePaymentFields(${response.totalPrice})">
            </div>
            <div class="CheckoutContent">
            <div class="CheckoutContentLeft">
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" required>
            </div>
            <div id="dueField" class="CheckoutContentRight dueField">
                <label for="due">Due:</label>
                <input type="number" id="due" name="due" readonly>
            </div>
            </div>
            <div class="btnDiv">
            <button type="button" id="Checkoutbtn"class="btn btn-success">Checkout</button>
            <button type="button"id="resetCheckout" class="btn btn-primary">Cancel</button>
            </div>
        </form>

			</div>
        </div>
    `;
				$("#CheckOutContainer").html(CheckOutForm).show();
				$(".container").addClass("hidden");
				const amountInput = document.getElementById('amount');

				amountInput.addEventListener('input', function() {

					var dueInput = document.getElementById("due");
					dueInput.value = response.totalPrice - amountInput.value;

				});

				$("#Checkoutbtn").click(function() {
					var paymentinfo = {
						orderId: $("input[name='orderId']").val(),
						receptAmount: $("input[name='amount']").val()
					};
					event.preventDefault();
					$.ajax({
						type: "POST",
						url: "/checkoutNow",
						data: paymentinfo,
						success: function(result) {
							if (result === "Checkout successful") {
								alert("Checkout successful");
								getOrderList();
								$("#CheckOutContainer").hide();
								$(".container").removeClass("hidden");
							}
						},
						error: function(err) {
							alert("Error Checkout: " + JSON.stringify(err));
						}
					});

				});
				$("#resetCheckout").click(function() {
					$("#CheckOutContainer").hide();
					$(".container").removeClass("hidden");
				});
			} else {

				alert("Not Enough product in stock. Please start new production.");
			}
		},
		error: function(xhr, status, error) {
			// Handle error response
			console.error("Error performing order validation:", error);
			alert("An error occurred while validating the order. Please try again later.");
		}
	});
}

function togglePaymentFields(recived) {
	//	console.log(recived);
	var fullPaymentReceived = document.getElementById("fullPaymentReceived");
	var dueField = document.getElementById("dueField");
	if (fullPaymentReceived.checked) {
		dueField.style.display = "none";
		var dueInput = document.getElementById("due");
		dueInput.value = 0;
		var dueInput = document.getElementById("amount");
		dueInput.value = recived;

	} else {
		dueField.style.display = "block";

	}
}

