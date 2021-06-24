$(document).ready(
	function() {

		// SUBMIT FORM
		$("#orderForm").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {

			var formData = {
				
				"quantity": $("#addQuantity").val(),
				"customer": {
					"customerId": $("#addCustomerId").val()
				},
				"stock": [
					{
						"productName": $("#addProductName").val()
					}
				]
			}

			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/addNewOrder",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(result) {
					if (result.status == "success") {
						alert(" Order successfull ....");

					} else {
						$("#postResultDiv").html("<strong>Error</strong>");
					}
				},
				error: function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			});
		}
	})