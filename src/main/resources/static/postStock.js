$(document).ready(
		function() {
			
			// SUBMIT FORM
			$("#stockForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				
				// PREPARE FORM DATA
				var formData = {
						productName : $("#addFormName").val(),
						perPrice : $("#addPrice").val(),
						type : $("#addType").val(),
						totalStock : $("#addStock").val(),
					
				}
				
				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/addNewProduct",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							alert("New Stock "+formData.productName+" Added");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});
			}
		})