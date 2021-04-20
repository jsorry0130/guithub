<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<div class="container">
		<div class="row">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd;
				width: 1000px;">
					<thead>
						<tr>
							<th style="background-color: #eeeeee; text-align: center; width: 10%;">번호</th>
							<th style="background-color: #eeeeee; text-align: center;">제목</th>
							<th style="background-color: #eeeeee; text-align: center;">작성자</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
							<th style="background-color: #eeeeee; text-align: center;">조회수</th>
						</tr>
					</thead>
					<tbody>
						<!-- 게시판 글 목록 -->
						<c:forEach items="${list}" var="p"> 
						<tr style="background-color: #e2e2e2;">
							<td>${p.id}</td>
							<td class="title indent text-align-left"><a href="detail?id=${p.id}">${p.title}</a></td>
							<td>${p.writer_id}</td>
							<td>
								${p.regDate}		 
							</td>
							<td>${p.hit}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			<!-- 게시판 페이지 이동 버튼 -->
			<div>
				<a href="bbs.jsp?pageNumber=" class="btn btn-success btn-arraw-left">이전</a>
				<a href="bbs.jsp?pageNumber=" class="btn btn-success btn-arraw-left">다음</a>
				<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
			</div>
		</div>
	</div>