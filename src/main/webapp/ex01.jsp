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
<ul id="replies">
</ul>


<script id="template" type="text/x-handlebars-template">
	{{#each.}}
    <li class="replyLi">
		<div>{{rno}}</div>
		<div>{{replytext}}</div>
		<div>{{temp replydate}}</div>
	</li>
	{{/each}}
</script>

<script>
	Handlebars.registerHelper("temp", function(t) {
		var dateObj = new Date(t);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth()+1;
		var day = dateObj.getDate();
		return year + "-" + month + "-" + day + (요일);
	})

	var source = $("#template").html();//템플릿 스트링 뽑기
	var te = Handlebars.compile(source);//함수만들어짐
	console.log(te);
	var data = [{rno:1, replytext:"1번 댓글",replydate:new Date()},
	            {rno:2, replytext:"2번 댓글",replydate:new Date()},
				{rno:3, replytext:"3번 댓글",replydate:new Date()},
				{rno:4, replytext:"4번 댓글",replydate:new Date()},
				{rno:5, replytext:"5번 댓글",replydate:new Date()}];
	
	$("#replies").html(te(data));
	
</script>

</body>
</html>


















