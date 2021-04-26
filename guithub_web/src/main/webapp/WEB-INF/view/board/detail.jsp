<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
	<div class="container">
		<!-- 글 상세보기 -->
		
		<form method="post" action="delete">
		
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd; width: 1000px;">
				<thead class="bg-dark">
					<tr>
						<th colspan="3" style="text-align: center; color: white;">${detail.title}</th>
					</tr>
				</thead>
				<tbody style="background-color: #eeeeee;">
				<!-- 글 내용 -->
					<tr> 
						<th style = "width: 15%; background-color: #eeeeee;">${detail.writer_id}</th>
						<th style= "text-align: right; background-color: #eeeeee;">
							<fmt:formatDate pattern="yy/MM/dd HH:mm" value="${detail.regDate }"/>
						</th>
						<th style= "width:15%; text-align: right; background-color: #eeeeee;">${detail.hit}</th>
					</tr>
					<tr>
						<th style="width: 15%; background-color: #eeeeee;">첨부파일</th>
						<td colspan="2"  style="background-color: #ffffff;" >${detail.files}</td>
					</tr>
					<tr class="content">
						<td colspan="3" style="background-color: #ffffff;">
						${detail.content}
						</td>
					</tr>
					<tr>
						<td colspan="2"/>
						<td>
						<p><input type="hidden" name="id" value="${detail.id}"/></p>
						<input type="password" class="form-control" placeholder="비밀번호" name="password" style="width: 150px;">
						
						</td>
						
					</tr>
				</tbody>
			</table>

		</div>
		<!-- 목록, 수정, 삭제 버튼 -->
		
			<div align="right" style="width: 990px;">
				
				<div style="float: right; margin: 0px 5px 0px 0px;">
					<input onclick="return confirm('정말로 삭제하시겠습니까?')" type="submit" class="btn bg-dark" style="color: white" value="삭제" />
					<!-- delete 컨트롤러에 의해 delCnt값이 담긴채로 general로 Redirect -->
					<c:if test="${delCnt == 0}">
						<input type="hidden" name="delCnt" value="${delCnt}"/>
					</c:if>	
				</div>
				
				<div style="float: right; margin: 0px 5px 0px 5px;">
					<input onclick="return confirm('정말로 수정하시겠습니까?')" type="submit" class="btn bg-dark" style="color: white" value="수정" />	
				</div>
				<c:if test="${delCnt == 0}">
					<script type="text/javascript">	
					  alert("비밀번호가 일치하지 않습니다.");
					</script>
				</c:if>
				<a href="general" class="btn bg-dark" style="color: white">목록</a>
			</div>
			
		</form>
		
		<!-- 댓글 작성 폼 -->
		<form method="post" action="regReply">
			<div class="row" align="right" style="padding: 20px 0px 20px 0px;">
				<div style="width: 200px" >
					<input type="text" class="form-control" placeholder="작성자" name="writer_id" style="width: 200px;"/>
					<input type="password" class="form-control" placeholder="비밀번호" name="password" 
					style="width: 200px; margin: 10px 0px 0px 0px;"/>
				</div>
				<div style="width: 700px" >
					<textarea class="form-control" placeholder="댓글을 입력해주세요" name="content" maxlength="500" 
					style="width: 690px; height: 87px;"></textarea>
				</div>
				<p>
					<input type="hidden" name="post_id" value="${detail.id}"/>
				</p>
				<div style="width: 100px" >
					<input type="submit" class="btn bg-dark" style="color: white; width: 90px; height: 87px;" value="작성" />
				</div>
			</div>
		</form>
		
		<!-- 댓글 목록 -->
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd; width: 1000px;">
				<tr class="bg-dark" style="color: white">
					<th style="width: 15%;">작성자</th>
					<th >댓글</th>
					<th></th>
					<th style="width: 15%;">날짜</th>
				</tr>
				
				<c:forEach items="${listReply}" var="r">
					<tr style="background-color:white;">
						<td>${r.writer_id }</td>
						<td class="cotent">${r.content }</td>
						<td><a href="#" style="color: red">[x]</a></td>
						<td><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${r.regdate }"/></td>
					</tr>
				</c:forEach>
			</table>
		
		</div>
	</div>
	
