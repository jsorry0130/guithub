<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==true}">
<script>
	if(confirm("정말로 회원탈퇴하시겠습니까?")){
		location.href="/member/withdraw?reconfirm=true"; //재확인 완료 값 컨트롤러로 전달
	}else{
		location.href="/member/withdraw";
	}
</script>
</c:if>

<c:if test="${check==false}">
<script>
	alert("비밀번호가 일치하지 않습니다.");
</script>
</c:if>

<c:if test="${success==true}">
<script>
	alert("회원탈퇴 완료되었습니다.");
	location.href="/home/index";
</script>
</c:if>

	<!-- 회원탈퇴 비밀번호 확인 -->
	<div class="container">
		<h1 style="color: white;">회원탈퇴</h1>
		<div class="container" style="margin-left: 25%;">
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;">
					<form method="post">
						<h3 style="text-align: center;">비밀번호 확인</h3>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호" name="pwdCheck" maxlength="20">
						</div>
						<input type="submit" class="btn bg-dark form-control" style="color:white" value="확인">
					</form>
						<a href="/home/index" class="btn bg-dark form-control" style="color: white">취소</a>
				</div>
			</div>
		</div>
	</div>
		
