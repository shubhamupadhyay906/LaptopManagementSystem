$(document).ready(function(){

	$("#btn").click(function(){
		var name= $("#addProductName").val();
		$.ajax({
			url: '/getStocks/'+name,
			type: 'GET',
			success: function(data){
			//var jsonStr = JSON.stringify(data);
				//document.getElementById("putResultDiv").innerHTML = jsonStr;
					
					var stockTable = $("#stock tbody");
				stockTable.empty();
				$(data).each(function(index,element){
				stockTable.append('<tr><td>'+element.productId+'</td>'+
				'<td>'+element.productName+'</td>'+
				'<td>'+element.perPrice+'</td>'+
				'<td>'+element.type+'</td>'+
				'<td>'+element.totalStock+'</td>'+
				'</tr>')
				});
				
				
			},
			error: function(data){
				alert("Not Found");
			}
			
		});
	});
});
