<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<script>
	//영어와 숫자만 입력되도록 하는 함수
	function InputABnum(e)  {
		  e.value = e.value.replace(/[^0-9A-Za-z]/ig, '')
		}
	
	//영어와 한글만 입력되도록 하는 함수
	function InputABkor(e)  {
		  e.value = e.value.replace(/[^ㄱ-힣A-Za-z]/ig, '')
		}
	
	//영어와 이메일에 필요한 특수문자만
	function InputEmail(e)  {
		  e.value = e.value.replace(/[^A-Za-z@_.-]/ig, '')
		}
	</script>
	<!-- 비밀번호 확인 처리 -->
	<c:if test="${pwdError==true}">
		<script>
			alert('두 비밀번호 값이 일치하지 않습니다!');
		</script>
	</c:if>
	<!-- 아이디 중복 처리 -->
	<c:if test="${idDup==true}">
		<script>
			alert('이미 존재하는 아이디입니다!');
		</script>
	</c:if>
	<!-- 닉네임 중복처리 -->
	<c:if test="${nickDup==true}">
		<script>
			alert('이미 존재하는 닉네임입니다!');
		</script>
	</c:if>
	<!--회원가입 완료-->
	<c:if test="${success==true}">
		<script>
			alert('회원가입이 완료되었습니다!');
			location.href="/home/login";
		</script>
	</c:if>	

	
	<!-- 회원가입 양식 -->
	<div class="container" style="margin-left: 37%;">
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post">
					<h3 style="text-align: center;">회원가입</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디(영문,숫자)" name="id" maxlength="15"
						pattern="[A-Za-z0-9]+">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호(영문,숫자)" name="password" maxlength="15"
						pattern="[A-Za-z0-9]+">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 확인" name="pwdcheck" maxlength="15"
						pattern="[A-Za-z0-9]+">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="닉네임(영문,한글)" name="nickname" maxlength="10"
						pattern="[A-Za-zㄱ-힣]+">
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="email" maxlength="20">
					</div>
					<input type="submit" class="btn bg-dark form-control" style="color:white"value="회원가입">
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>