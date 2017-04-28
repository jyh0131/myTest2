<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
  
<%@ include file="../include/header.jsp"%>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<select name="searchType">
							<option value="n" ${cri.searchType == null?'selected':''}>--</option>
							<option value="t" ${cri.searchType eq "t" ?'selected':''}>Title</option>
							<option value="c" ${cri.searchType eq "c" ?'selected':''}>Content</option>
							<option value="w" ${cri.searchType eq "w" ?'selected':''}>Writer</option>
							<option value="tc" ${cri.searchType eq "tc" ?'selected':''}>Title or Content</option>
							<option value="cw" ${cri.searchType eq "cw" ?'selected':''}>Content or Writer</option>
							<option value="tcw" ${cri.searchType eq "tcw" ?'selected':''}>Title OR Content OR Writer</option>
						</select>
						<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}">
						<button id="searchBtn">Search</button>
						<button id="newBtn">new Board</button>
					</div>
				</div>
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">List All PAGE</h3>
					</div>	
					
					<div class="box-body">
						<table class="table table-bordered">
							<tr>
								<th style="width:10px">BNO</th>
								<th>TITLE</th>
								<th>WRITER</th>
								<th>REGDATE</th>
								<th style="width:40px">VIEWCNT</th>
							</tr>
							
							<c:forEach var="boardVO" items="${list }">
								<tr>
									<td>${boardVO.bno }</td>
									<td><a href="${pageContext.request.contextPath}/sboard/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}&isUpdateCount=true">${boardVO.title }</a></td>
									<td>${boardVO.writer }</td>
									<td><fmt:formatDate value="${boardVO.regdate }" 
										pattern="yyyy-MM-dd HH:mm"/> </td>
									<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
								</tr>
							</c:forEach>
						 	
						</table>
					</div>		
					
					<div class="box-footer">
						<div class="text-center">
							<ul class="pagination">
								<c:if test="${pageMaker.prev }">
									<li><a href="list${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a> </li>
								</c:if>
								
								<c:forEach 	begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
									<li ${pageMaker.cri.page == idx ? 'class="active"' : '' }>
										<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
									</li>
								</c:forEach>
								
								<c:if test="${pageMaker.next }">
									<li><a href="list${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a> </li>
								</c:if>
							</ul>
						</div>
					</div>		
				</div>
			</div>
		</div>
	</section>
	
	<script>
		var result = '${result}';
		if(result == "success"){
			alert("처리가 완료되었습니다");
		}
		
		$(function(){
			$("#searchBtn").click(function(){
				self.location = "list" + '${pageMaker.makeQuery(1)}'
								+ "&searchType=" + $("select").val()
								+ "&keyword=" + $("#keywordInput").val();
			});
			
			$('#newBtn').on("click", function(evt) {

				self.location = "register";

			});
		});
	</script>

<%@ include file="../include/footer.jsp"%>



















