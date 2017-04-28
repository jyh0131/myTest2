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
						<form role="form" action="register" method="post">
							<div class="form-group">
								<label>Title</label>
								<input type="text" placeholder="Enter Title" name="title" 
									class="form-control">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="5" cols="" class="form-control" placeholder="Enter.."></textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input type="text" placeholder="Enter Writer" name="writer" 
									class="form-control">
							</div>
							<div class="form-group">								
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div>				
				</div>
			</div>
		</div>
	</section>


<%@ include file="../include/footer.jsp"%>