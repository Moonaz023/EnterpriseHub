$(document).ready(function() {
	getAllSalesRecords();

});
   //==========================================show table========================================================
var sales = "";

function getAllSalesRecords() {
    $.ajax({
        type: "GET",
        url: "/getAllSales", 
        success: function (sales) {
            console.log(sales); 
            salesdata = sales;
            $('#Sales').DataTable().destroy();
            $('#Salesresult').empty();

            for (i = 0; i < sales.length; i++) {
                
                $("#Salesresult").append(
                    '<tr class="tr">' +
                    '<td>' + sales[i].date + '</td>' +
                    '<td>' + sales[i].details + '</td>' +
                    '<td>' + sales[i].receptAmount + '</td>' +
                    '<td>' + sales[i].due + '</td>' +
                    '<td>' + sales[i].distributor.name + '</td>' +
                    '</tr>'
                );
            }

           
            // Initialize DataTables plugin
            $('#Sales').DataTable();
        },
        error: function (err) {
            alert("Error: " + err);
            console.error("Error:", err);
        }
    });
}