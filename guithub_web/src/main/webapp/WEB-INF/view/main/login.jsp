<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- 로그인 양식 -->
	<c:if test="${check==true}">
		<script type="text/javascript">	
			alert("환영합니다.");
			location.href="/index";
		</script>
	</c:if>		
	<c:if test="${check==false}">
		<script type="text/javascript">	
			alert("회원정보가 일치하지 않습니다.");
		</script>
	</c:if>	
	
	<div class="container" style="margin-right:-180px">
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="login">
					<h3 style="text-align: center;">회원 로그인</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">
					</div>
					<input type="submit" class="btn bg-dark form-control" style="color:white" value="로그인">
				</form>
					<a href="/join" class="btn bg-dark form-control" style="color: white">회원가입</a>
			</div>
		</div>
	</div>