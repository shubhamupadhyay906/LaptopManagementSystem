$(document).ready(function() {
	
	// Do GET all Customers from Back-End with JQUERY AJAX
	$(function () {
		$.ajax({
			type : "GET",
			url :  "/sortByProductName",
			success: function(result){
				$.each(result, function(index, stock){
					
					var stockProduct = '<tr>' +
										'<td>' + stock.productId + '</td>' +
										'<td>' + stock.productName + '</td>' +
										'<td>' + stock.perPrice + '</td>' +
										'<td>' + stock.type + '</td>' +
										'<td>' + stock.totalStock + '</td>' +
									  '</tr>';
					
					$('#stockTable tbody').append(stockProduct);
					
		        });
				
			//	$( "#stockTable tbody tr:odd" ).addClass("success");
				//$( "#stockTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});	
	});
})

