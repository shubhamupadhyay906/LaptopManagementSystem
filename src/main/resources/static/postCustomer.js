$(document).ready(
		function() {
			
			// SUBMIT FORM
			$("#customerForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				
				// PREPARE FORM DATA
				var formData = {
						name : $("#addCusName").val(),
						phoneNumber : $("#addPhone").val(),
						address : $("#addAdd").val(),
						
					
				}
				
				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/addcustomer",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							alert("New customer "+formData.name+" Added");
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