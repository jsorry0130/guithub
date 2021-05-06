<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


	<div class="container" style="margin-right:-10px">
		<!-- 로그인 유저 정보 -->
		
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd; width: 750px;">
				<thead class="bg-dark">
					<tr>
						<th colspan="3" style="text-align: center; color: white;"><h4>회원정보</h4></th>
					</tr>
				</thead>
				<tbody style="background-color: #eeeeee;">
				<!-- 글 내용 -->
				<tr>
					<td style="width:30%"><b>회원 아이디</b></td>
					<td>${sessionScope.mem_id}</td>
				</tr>
				<tr>
					<td><b>회원 닉네임</b></td>
					<td>${sessionScope.mem_nickname}</td>
				</tr>
				<tr>
					<td><b>회원 이메일</b></td>
					<td>${sessionScope.mem_email}</td>
				</tr>
				</tbody>
			</table>

		</div>
		
	</div>
	
