<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Know-ya-numba Game</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
					$("#answer").keypress(function(e) {
						if(e.keyCode == 13) {
							$.ajax({
								type: "POST",
								url: "${pageContext.request.contextPath}/answer",
								data: {
									"answer" : e.target.value,
									"dummy" : new Date().getTime()
								}})
								.done(function(data, textStatus, jqXHR ){
										if (data.answer == "CORRECT") {
											$("#message").html("Correct answer: " + $("#answer").val());
											$("#current").html(data.value);
											$("#answer").val('');
										} else if (data.answer == "WRONG") {
											$("#message").html("Incorrect answer: " + $("#answer").val());
											$("#current").html("Should have answered: " + data.value);
											$("#answer").prop('disabled', true);

											$("#reset").prop('disabled', false);
											$("#reset").focus();
										} else if (data.answer == "INVALID") {
											$("#message").html("Invalid answer: " + $("#answer").val());
										} else {
											handleError();
										}
									})
								.fail(function(jqXHR, textStatus, errorThrown){
										handleError();
									});
						}
					});

					$("#reset").keypress(function(e) {
						if(e.keyCode == 13) {
							window.location.href = "${pageContext.request.contextPath}/";
						}
					});

					$("#answer").focus();
					$("#reset").prop('disabled', true);
				}
			);

			function handleError() {
				$("#message").html("ERROR");
				$("#answer").prop('disabled', true);
				$("#reset").prop('disabled', true);
			}
		</script>
	</head>
	<body>
		<div id="content">
			<h1>Know-ya-numba Game</h1>
			<table>
				<thead>
					<tr>
						<td id="message">Your first answer is: ${previous}</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id="current">${current}</td>
					</tr>
					<tr>
						<td>
							<input id="answer" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<input id="reset" type="button" value="reset">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>