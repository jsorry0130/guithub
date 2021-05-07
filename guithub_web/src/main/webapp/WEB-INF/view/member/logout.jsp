<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- 로그아웃 양식 -->
	<c:if test="${sessionScope.mem_id==null}">
		<script type="text/javascript">	
			alert("로그아웃 되었습니다.");
			location.href="/home/index";
		</script>
	</c:if>		
	<div class="container" style="margin-right:-180px">
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="logout">
					<h4 style="text-align: center;">로그아웃 하시겠습니까</h4>
					<input type="submit" class="btn bg-dark form-control" style="color:white" value="확인">
				</form>
			</div>
		</div>
	</div>