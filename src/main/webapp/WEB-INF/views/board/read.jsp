<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
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
						<button type="submit" class="btn btn-warning">수정하기</button>
						<button type="submit" class="btn btn-danger">삭제하기</button>
						<button type="submit" class="btn btn-primary">돌아가기</button>
					</div>				
				</div>
			</div>
		</div>
	</section>
	
	<script>
		$(function(){
			$(".btn-primary").click(function(){
				//location.href = "${pageContext.request.contextPath}/board/listPage";
				//history.go(-1);
				$("#form1").attr("action", "${pageContext.request.contextPath}/board/listPage");
				$("#form1").attr("method","get");
				$("#form1").submit();
			});
			
			$(".btn-danger").click(function(){
				//삭제 시에 form이 들고있는 bno도 같이 가지고 간다.
				$("#form1").attr("action", "${pageContext.request.contextPath}/board/remove");
				$("#form1").submit();
			});
			
			$(".btn-warning").on("click", function(){
				$("#form1").attr("action", "${pageContext.request.contextPath}/board/modify");
				$("#form1").attr("method", "get");		
				$("#form1").submit();
			});
		});
	</script>

<%@ include file="../include/footer.jsp"%>







