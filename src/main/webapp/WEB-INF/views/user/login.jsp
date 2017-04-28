<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>
	<section class="content">
		<div class="row">
			<div class="col-md-3 col-md-offset-2" >
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">LOGIN</h3>
					</div>	
					
					<div class="box-body">
						<form role="form" action="loginPost" method="post" >
							<div class="form-group">
								<label>ID</label>
								<input type="text" placeholder="Enter ID" name="uid" 
									class="form-control">
							</div>							
							<div class="form-group">
								<label>PASSWORD</label>
								<input type="text" placeholder="Enter password" name="upw" 
									class="form-control">
							</div>
							<div class="form-group">								
								<button type="submit" class="btn btn-primary">login</button>
							</div>
						</form>
					</div>	
				</div>
			</div>
		</div>
	</section>		

<%@ include file="../include/footer.jsp"%>