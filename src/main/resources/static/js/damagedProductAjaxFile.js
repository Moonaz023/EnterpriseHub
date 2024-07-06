$(document).ready(function() {
	getAllProduction();
	getDamagedProductList();
});
var productionList;
function getAllProduction() {
	$.ajax({
		type: "GET",
		url: "/admin/getAllProductions",
		success: function(response) {
			productionList = response;
			var dropdown = $("#productionInput");
			$.each(response, function(index, production) {
				dropdown.append('<option value="' + production.id + '">' + production.dateOfProduction + ' | ' + production.product.name + ' | ' + production.productionQuantity + '</option>');
			});
		},
		error: function(er) {
			alert("error:" + er);
		}

	});

}
$("#insert").click(function() {
	//alert("YYYOOOOOOOOOOOO");
	var form = $("#formDamagedProduct");
	event.preventDefault;
	var formdata = form.serializeArray();
	$.ajax({
		type: "POST",
		url: "/saveDamagedProduct",
		data: formdata,
		success: function(response) {
			//alert("YYYOOOOOOOOOOOO");
			form[0].reset();
			getDamagedProductList();
		},
		error: function(err) {
			alert("error:" + er);
		}
	});
});

$("#cancle").click(function() {
	var form = $("#formDamagedProduct");
	form[0].reset();

});

function getDamagedProductList() {
	$.ajax({
		type: "GET",
		url: "/getAllDamagedProduct",
		success: function(response) {

			$('#damagedProduct').DataTable().destroy();

			$("#damagedProduct_result").empty();
			$.each(response, function(index, record) {
				$("#damagedProduct_result").append(
					'<tr class="tr">' +
					'<td>' + record.productionId.dateOfProduction + '</td>' +
					'<td>' + record.productionId.product.name + '</td>' +
					'<td>' + record.quantity + '</td>' +
					'<td><a href="#" onclick="CheckOutValidation(' + record.id + ');">Edit</a></td>' +
					'<td><a href="#" onclick="deleteRecord(' + record.id + ')">Delete</a></td>' +
					'</tr>'
				);
			});

			$("#damagedProduct").DataTable();

		},
		error: function(err) {
			alert("error=" + err);
		}

	});
}