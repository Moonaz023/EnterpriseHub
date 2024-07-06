$(document).ready(function() {
	getAllStockRecords();

});

   //==========================================show table========================================================
var data = "";

function getAllStockRecords() {
    $.ajax({
        type: "GET",
        url: "/getAllProductsStock", 
        success: function (stock) {
            console.log(stock); 
            stockdata = stock;
            $('#Stock').DataTable().destroy();
            $('#Stockresult').empty();

            for (i = 0; i < stock.length; i++) {
                
                $("#Stockresult").append(
                    '<tr class="tr">' +
                    '<td>' + stock[i].product.productCode + '</td>' +
                    '<td>' + stock[i].product.name + '</td>' +
                    '<td>' + stock[i].productQuantity + '</td>' +
                    '</tr>'
                );
            }

           
            // Initialize DataTables plugin
            $('#Stock').DataTable();
        },
        error: function (err) {
            alert("Error: " + err);
            console.error("Error:", err);
        }
    });
}