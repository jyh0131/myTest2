<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<div id="replies"></div>
	<script id="template" type="text/x-handlebars-template">
		<h1>{{major}}</h1>
		<table>
		{{#users}}
		<tr>
			<td>{{name}}</td>
			<td>{{id}}</td>
			<td>{{email}}</td>
		</tr>
		{{/users}}
		</table>
	</script>
	<script>
	var data = { major:"컴퓨터과"	
				 ,users:[{name:"홍길동", id:"aaa1", email:"aaa1@gmail.com"},
					{name:"홍길동2", id:"aaa2", email:"aaa2@gmail.com"},
					{name:"홍길동3", id:"aaa3", email:"aaa3@gmail.com"},
					{name:"홍길동4", id:"aaa4", email:"aaa4@gmail.com"},
					{name:"홍길동5", id:"aaa5", email:"aaa5@gmail.com"}]};
	
	var source = $("#template").html();
	var te = Handlebars.compile(source);	
	
	$("#replies").html(te(data));
	</script>
</body>
</html>