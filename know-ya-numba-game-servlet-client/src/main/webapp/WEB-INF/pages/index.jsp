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
											$("#currentValue").html(data.value);
											$("#answer").val('');
											$("#currentScore").html("Score: " + data.currentScore);
										} else if (data.answer == "WRONG") {
											$("#message").html("Incorrect answer: " + $("#answer").val());
											$("#currentValue").html("Should have answered: " + data.value);
											$("#answer").prop('disabled', true);

											$("#reset").prop('disabled', false);
											$("#reset").focus();
											$("#currentScore").html("Score: " + data.currentScore);
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
					$("#answer").focus();

					$("#reset").keypress(function(e) {
						if(e.keyCode == 13) {
							window.location.href = "${pageContext.request.contextPath}/";
						}
					});
					$("#reset").prop('disabled', true);

					$("#currentValue").html("${currentValue}");

					$("#currentScore").html("Score: ${currentScore}");
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
						<td id="message">Your first answer is: ${previousValue}</td>
						<td id="currentScore" />
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id="currentValue" colspan="2" />
					</tr>
					<tr>
						<td colspan="2">
							<input id="answer" type="text" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input id="reset" type="button" value="reset">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>