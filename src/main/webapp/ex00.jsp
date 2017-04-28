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
	<div id="displayDiv">
	</div>
	
<script id="template" type="text/x-handlebars-template">
    <span> {{name}}</span>
    <div>
        <span> {{userid}}</span>
        <span> {{ addr }}</span>
    </div>
</script>
	
	<script>
		var source = $("#template").html();//string 
		var te = Handlebars.compile(source);//함수 반환
	    var data  = {name:"홍길동", userid:"user00", addr:"조선 한양"};

	    $("#displayDiv").html(te(data));
	</script>

</body>
</html>








