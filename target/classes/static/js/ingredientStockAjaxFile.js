$(document).ready(function() {
	getAllIngredientStockRecords();

});

   //==========================================show table========================================================
var data = "";

function getAllIngredientStockRecords() {
    $.ajax({
        type: "GET",
        url: "/getAllIngredientStock", 
        success: function (ingredient_stock) {
            //console.log(stock); 
            ingredient_stockdata = ingredient_stock;
            $('#IngredientStock').DataTable().destroy();
            $('#IngredientStockresult').empty();

            for (i = 0; i < ingredient_stock.length; i++) {
                
                $("#IngredientStockresult").append(
                    '<tr class="tr">' +
                     '<td>' + ingredient_stock[i].ingredient.ingredientCode + '</td>' +
                    '<td>' + ingredient_stock[i].ingredient.ingredientName + '</td>' +
                    '<td>' + ingredient_stock[i].ingredientQuantity + '</td>' +
                    '</tr>'
                );
            }

           
            // Initialize DataTables plugin
            $('#IngredientStock').DataTable();
        },
        error: function (err) {
            alert("Error: " + err);
            console.error("Error:", err);
        }
    });
}