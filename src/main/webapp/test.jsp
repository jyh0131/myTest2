<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#modDiv{
		width: 300px;
		height:100px;
		background-color: gray;
		position:absolute;
		top:30%;
		left:30%;
		z-index:1000;
		padding:10px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	var bno = 2000;
	
	function getPageList(page){
		$.ajax({
			url:"replies/"+bno+"/"+page,
			type:"get",
			dataType:"json",
			success:function(data){
				console.log(data);
				
				//list
				var str = "";
				
				$(data.list).each(function(i, obj){
					str += "<li data-rno="+obj.rno+">" + obj.rno + ", " + obj.replytext
							+"<button class='modBtn'>수정</button>"
							+"<button class='delBtn'>삭제</button>"
							+ "</li>";	
				});
				
				$("#list").html("<ul></ul>");
				$("#list ul").html(str);
				
				
				//page번호				
				var str2 ="";
				
				if(data.pageMaker.prev){
					str2 = "<a href='"+(data.pageMaker.startPage-1)+"'> << </a>"; 
				}
				
				for(var i = data.pageMaker.startPage; i <= data.pageMaker.endPage ; i++){
					str2 += "<a href='"+i+"'> "+ i +" </a>"; 
				}
				
				if(data.pageMaker.next){
					str2 += "<a href='"+(data.pageMaker.endPage+1)+"'> >> </a>"; 
				}
				
				$("#pagination").html(str2);
			}				
		});
	}

	$(function(){
		$("#addBtn").click(function(){
			var user = $("#newReplyUser").val();
			var content = $("#newReplyContent").val();
			var sendData = JSON.stringify({bno:bno,
										replyer:user,
										replytext:content}); //"{}"
										
			$.ajax({
				url:"replies",
				type:"post",
				data:sendData,
				dataType:"text", //돌려받는 data의 종류 :SUCCESS
				headers:{
					"Content-Type":"application/json"
				},
				success:function(result){
					if(result == "SUCCESS"){
						//list가져와서 나타나게 한다.
						getPageList(1);//1:page번호
					}
				}
			})
		});
		
		$(document).on("click", "#pagination a", function(e){
			e.preventDefault();//a 태그 링크 차단
			
			var pageNum = $(this).attr("href");
			
			getPageList(pageNum);
			
		});
		
		$(document).on("click",".delBtn", function(){
			var rno = $(this).parent().attr("data-rno");
			
			$.ajax({
				url:"replies/" + rno,
				type:"delete",
				dataType:"text",
				success:function(data){
					if(data == "SUCCESS"){
						alert("삭제되었습니다.");
						getPageList(1);
					}
				}
			});
		});
		
		$(document).on("click", ".modBtn", function(){
			$("#modDiv").show(1000);
			var rno = $(this).parent().attr("data-rno");
			var replytext = $(this).parent().text();
			$("#modDiv .modal-title").text(rno);
			$("#modDiv input").val(replytext);
		});
		
		$(document).on("click", "#modify", function(){
			var rno = $(".modal-title").text();
			var replytext = $("#replytext").val();
			
			//replies/rno번호 , data : replytext만 넘김, method=PUT
			
			$.ajax({
				url:"replies/" + rno,
				type:"put",
				data: JSON.stringify({replytext:replytext}),
				headers:{
					"Content-type":"application/json" //@RequestBody
				},
				dataType:"text",
				success:function(data){
					if(data == "SUCCESS"){
						alert("수정되었습니다.");
						$("#modDiv").hide(500);
						getPageList(1);
					}
				}
			})
			
		});
	});
</script>
</head>
<body>
	<div id="modDiv" style="display:none">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext">
		</div>
		<div>
			<button id="modify">수정하기</button>
			<button id="close">닫기</button>
		</div>
	</div>

	<h1>Ajax Test Page</h1>
	<div>
		작성자 : <input type="text" name="user" id="newReplyUser"><br>
		내용 : <input type="text" name="content" id="newReplyContent"><br>
		<button id="addBtn">추가</button>
	</div>
	
	<div id="list">
	</div>
	
	<div id="pagination">
	
	</div>
	
	
	
</body>
</html>




