<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div class="container">
		<h1 style="color: white; margin: 0px 0px 15px 0px;">게시물 관리</h1>
		<div style="margin: 0px 0px 15px 0px">
			<select class = "form-control" name="board" style="display:inline; width:200px;" onchange="location.href=this.value">
				<option value="/member/postlist?board=general" ${(param.board=='general') ? "selected":""}>자유게시판</option>
				<option value="/member/postlist?board=tab" ${(param.board=='tab') ? "selected":""}>타브악보</option>
			</select>
			<h4 style="display:inline; color: white;">게시판</h4>
		</div>
		<div class="row">
				<!-- 게시판 글 목록 -->
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd; width: 1000px;">
					<thead>
						<tr class="bg-dark">
							<th style="text-align: center; width: 10%; color:white;">번호</th>
							<th style="text-align: center; color:white;">제목</th>
							<th style="text-align: center; color:white;">작성일</th>
							<th style="text-align: center; width: 10%; color:white;">수정</th>
							<th style="text-align: center; width: 10%; color:white;">삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="p"> 
						<tr style="background-color: #eeeeee;">
							<td>${p.id}</td>
							<td style="text-align: left;"><a href="/board/${param.board}/detail?id=${p.id}" link="black">${p.title}
								<c:if test="${p.reply_count > 0 }">
								<span style="color: blue">[${p.reply_count}]</span>
								</c:if>
								<c:if test="${p.new_post == true}">
									<span style="color:red;">[new]</span>
								</c:if>
								</a>
							</td>
							<td>
								<c:if test="${p.new_post == true}">
									<fmt:formatDate pattern="HH:mm" value="${p.regdate }"/>
								</c:if>
								<c:if test="${p.new_post == false}">
									<fmt:formatDate pattern="yy/MM/dd" value="${p.regdate }"/>
								</c:if> 
							</td>
							<td><a href="/board/${param.board}/edit?id=${p.id}">수정</a></td>
							<td><a href="/board/${param.board}/delpost?id=${p.id}" style="color:red">삭제</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

		</div>
		
		<!-- 게시판 페이징 및 버튼 -->
		<div>
			<!-- 페이징 번호 및 버튼 -->
			<div style="float: left;">
				<c:if test="${paging.prev}">
					<a href="list?page=${paging.startPageNum-1}&field=${param.field}&keyword=${param.keyword}" 
					class="btn bg-dark btn-arraw-left" style="color: white">이전</a>
				</c:if>
				
					<c:forEach begin="${paging.startPageNum}" end="${paging.endPageNum}" var="num">
						<c:if test="${paging.page != num}">
							<span style="margin: 5px;">
								<a href="list?page=${num}&field=${param.field}&keyword=${param.keyword}"> ${num} </a>
							</span>
						</c:if>
						<c:if test="${paging.page == num}">
							<span style="margin: 5px;">
								<b style="color:white"> ${num} </b>
							</span>							
						</c:if>
					</c:forEach>
					
				<c:if test="${paging.next}">
					<a href="list?page=${paging.endPageNum+1}&field=${param.field}&keyword=${param.keyword}" 
					class="btn bg-dark btn-arraw-left" style="color: white">다음</a>
				</c:if>
			</div>
			
			<!-- 검색 박스 및 버튼 (부트스트랩 css를 수정할수없어 float으로 input 한줄에 정렬)-->
			<form>
				<div style="float: right; padding: 0px 130px 0px 0px;">
					<input type="submit" class="btn bg-dark pull-right" style="color: white" value="검색">
				</div>
				<div style="float: right; padding: 0px 10px 0px 0px;">
					<input type="text" class="form-control" placeholder="검색어를 입력하세요" name="keyword" style="width: 300px;"
					value="${param.keyword }"/>
				</div>
				<div style="float: right; padding: 0px 10px 0px 0px;">
					<select name="field" class="form-control">
						<option ${(param.field=="title") ? "selected":"" } value="title">제목</option>
						<option ${(param.field=="title_content") ? "selected":"" } value="title_content">제목+내용</option>
					</select>
				</div>
			</form>
			
		</div>
	</div>