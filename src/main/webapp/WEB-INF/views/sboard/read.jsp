<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">REGISTER PAGE</h3>
					</div>	
					
					<div class="box-body">
						<form method="post" id="form1">
							<input type="hidden" name="bno" value="${boardVO.bno }">
							<input type="hidden" name="page" value="${cri.page}">
							<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
						</form>
						<div class="form-group">
							<label>Title</label>
							<input type="text" class="form-control" 
								value="${boardVO.title}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>Content</label>
							<input type="text" class="form-control" 
								value="${boardVO.content}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" class="form-control" 
								value="${boardVO.writer}" readonly="readonly">
						</div>
					</div>
					<div class="box-footer">
						<button type="submit" class="btn btn-warning" id="modifyBtn">수정하기</button>
						<button type="submit" class="btn btn-danger" id="removeBtn">삭제하기</button>
						<button type="submit" class="btn btn-primary" id="goListBtn">돌아가기</button>
					</div>				
				</div>
			</div>
		</div>
		
		
		
	<script>
		$(function(){
			$("#modifyBtn").click(function(){
				//location.href = "${pageContext.request.contextPath}/board/listPage";
				//history.go(-1);
				$("#form1").attr("action", "${pageContext.request.contextPath}/board/listPage");
				$("#form1").attr("method","get");
				$("#form1").submit();
			});
			
			$("#removeBtn").click(function(){
				//삭제 시에 form이 들고있는 bno도 같이 가지고 간다.
				$("#form1").attr("action", "${pageContext.request.contextPath}/board/remove");
				$("#form1").submit();
			});
			
			$("#goListBtn").on("click", function(){
				$("#form1").attr("action", "${pageContext.request.contextPath}/board/modify");
				$("#form1").attr("method", "get");		
				$("#form1").submit();
			});
		});
	</script>
		
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">ADD NEW REPLY</h3>
					</div>
					<div class="box-body">
						<label>Writer</label>
						<input type="text" placeholder="User ID" id="newReplyWriter" class="form-control">
						<label>Reply Text</label>
						<input type="text" placeholder="text" id="newReplyText" class="form-control">
					</div>
					<div class="box-footer">
						<button type="button" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
					</div>
				</div>
				<ul class="timeline">
					<li class="time-label" id="repliesDiv">
						<span class="bg-green">Replies List</span>
					</li>
				</ul>
				
				<div id="modifyModal" class="modal modal-primary fade" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"></h4>
							</div>
							<div class="modal-body" data-rno>
						        <p><input type="text" id="replytext" class="form-control"></p>
						    </div>
						    <div class="modal-footer">
						    	<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
						    	<button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
						    	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						    </div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	
	<script id="template" type="text/x-handlebars-template">
		{{#each .}}
			<li class="replyLi" data-rno={{rno}}>
				<i class="fa fa-comments bg-blue"></i>
				<div class="timeline-item" >
					<span class="time">
						<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
					</span>
					<h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
					<div class="timeline-body">{{replytext}}</div>
					<div class="timeline-footer">
						<a class="btn btn-primary btn-xs" 
							data-toggle="modal" data-target="#modifyModal">Modify</a>
					</div>					
				</div>
			</li>
		{{/each}}
	</script>
	<script>
		Handlebars.registerHelper("prettifyDate",function(t){
			var dateObj = new Date(t);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth()+1;
			var day = dateObj.getDate();
			return year + "-"+month+"-"+day;
		});
	
		var bno = ${boardVO.bno}; //게시판 번호
		
		function getPageList(page){
			$.ajax({
				url:"${pageContext.request.contextPath}/replies/"+bno+"/"+page,
				type:"get",
				dataType:"json",
				success:function(data){
					var source = $("#template").html();
					var te = Handlebars.compile(source);//함수
					var html = te(data.list);//배열만 집어넣어서 string뽑아냄
					
					$("#repliesDiv").after(html);
				}
			});
		}
		
		$("#repliesDiv").click(function(){
			getPageList(1);
		});
		
		//댓글 리스트의 modify 클릭		
		$(document).on("click",".replyLi a",function(){
			//모달에 제목과 내용을 채워야 한다.
			var reply = $(this).parents(".replyLi");
			$(".modal-title").html(reply.attr("data-rno"));
			$("#replytext").val(reply.find(".timeline-body").html());
			
		});
		
		//modal 숨기기
		/* $("#modifyModal").modal("hide"); */
	</script>
	
	

<%@ include file="../include/footer.jsp"%>







