<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
	<div class="container">
		<!-- 댓글 삭제 패스워드 확인 페이지 -->	
		<form method="post" action="edit?id=${param.id}">	
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd; width: 1000px;">
				<thead class="bg-dark">
					<tr>
						<th colspan="3" style="text-align: center; color: white;">게시물 수정을 위해 비밀번호를 입력해주세요.</th>
					</tr>
				</thead>
				<tbody style="background-color: #eeeeee;">
				<!-- 글 내용 -->
					<tr> 
						<td style="width: 33%"></td>
						<td style="width: 33%">
						<input type="password" class="form-control" placeholder="비밀번호" name="password" style="width: 320px;">
						</td>
						<td><input type="submit" class="btn bg-dark" style="color: white" value="확인"></td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
	</div>