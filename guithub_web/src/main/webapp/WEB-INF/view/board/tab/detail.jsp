<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	<c:if test="${sessionScope.mem_id == null}">
		<script type="text/javascript">	
			alert("로그인 후 이용해주세요.");
			location.href="/home/login";
		</script>
	</c:if>	
	
	

	<div class="container">
		<!-- 글 상세보기 -->
		
		<form method="post" action="delete">
		
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd; width: 1000px;">
				<thead class="bg-dark">
					<tr>
						<th colspan="3" style="text-align: center; color: white;"><h4>${detail.title}</h4></th>
					</tr>
				</thead>
				<tbody style="background-color: #eeeeee;">
				<!-- 글 내용 -->
					<tr> 
						<th style = "width: 15%; background-color: #eeeeee;">${detail.writer_id}</th>
						<th style= "text-align: right; background-color: #eeeeee;">
							<fmt:formatDate pattern="yy/MM/dd HH:mm" value="${detail.regdate }"/>
						</th>
						<th style= "width:15%; text-align: right; background-color: #eeeeee;">${detail.hit}</th>
					</tr>
					<tr>
						<th style="width: 15%; background-color: #eeeeee;">첨부파일</th>
						<td colspan="2"  style="background-color: #ffffff;" >
							<c:forEach items="${files}" var="f">
								<a download href = "/static/tab/upload/${f.realName}">
									[다운로드] ${f.name} (${f.file_size}kb)<br/>
								</a>
							</c:forEach>
						
						</td>
					</tr>
					<tr class="content">
						<td colspan="3" style="background-color: #ffffff; white-space: pre-wrap;">${detail.content}</td>
					</tr>
				</tbody>
			</table>

		</div>
		<!-- 목록, 수정, 삭제 버튼 -->
		
			<div align="right" style="width: 985px;">
				
				<a href="list" class="btn bg-dark" style="color: white">목록</a>
				<a href="edit?id=${param.id}" class="btn bg-dark" style="color: white">수정</a>
				<a href="delpost?id=${param.id}" class="btn bg-dark" style="color: white">삭제</a>
			</div>
			
		</form>
		
		<!-- 댓글 작성 폼 -->
		<form method="post" action="regreply">
			<div class="row" align="right" style="padding: 20px 0px 20px 0px;">
				<input type="hidden" class="form-control" name="writer_id" value="${sessionScope.mem_nickname}"/>
				<div style="width: 900px" >
					<textarea class="form-control" placeholder="댓글을 입력해주세요" name="content" maxlength="500" 
					style="width: 900px; height: 87px;"></textarea>
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
					<th style="width:2%"></th>
					<th style="width: 15%;">날짜</th>
				</tr>
				
				<c:forEach items="${listReply}" var="r">
					<tr style="background-color:white;">
						<td>${r.writer_id }</td>
						<td class="content" style="white-space: pre-wrap;">${r.content }</td>
						<td><a href="delreply?rid=${r.id}&pid=${param.id }&rwriter=${r.writer_id}" style="color: red">[x]</a></td>
						<td><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${r.regdate }"/></td>
					</tr>
				</c:forEach>
			</table>
		
		</div>
	</div>
	
