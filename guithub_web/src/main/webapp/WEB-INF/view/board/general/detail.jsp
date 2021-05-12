<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	<script>
		//get 방식으로파라미터를 받는 함수
		function getParameter(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
	
		function validation(e) {
			  var writer_id = document.getElementById("writer_id").value;
			  var password = document.getElementById("password").value;
			  var content = document.getElementById("content").value;
			  
			  if (writer_id=="" || password=="" || content=="") {
				  e.preventDefault();
			      alert("빈칸을 채워주세요!");
			  }
			  
			}
		
		var id = getParameter("id");
		
		function reconfirmEdit() {
			if(confirm("정말로 수정하시겠습니까")==true){
				location.href="editmem?id="+id;
			}else{
				return false;
			}
		}
		
		function reconfirmDel() {
			if(confirm("정말로 삭제하시겠습니까")==true){
				location.href="delmempost?id="+id;
			}else{
				return false;
			}
		}
		
	</script>
	
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
						<th style = "width: 15%; background-color: #eeeeee;">${detail.writer_id}
						<c:if test="${detail.password==null}"><span style="color:red">[★]</span></c:if>
						</th>
						<th style= "text-align: right; background-color: #eeeeee;">
							<fmt:formatDate pattern="yy/MM/dd HH:mm" value="${detail.regdate }"/>
						</th>
						<th style= "width:15%; text-align: right; background-color: #eeeeee;">${detail.hit}</th>
					</tr>
					<tr>
						<th style="width: 15%; background-color: #eeeeee;">첨부파일</th>
						<td colspan="2"  style="background-color: #ffffff;" >
							<c:forEach items="${files}" var="f">
								<a download href = "/static/upload/${f.realName}">
									[다운로드] ${f.name} (${f.file_size}kb)<br/>
								</a>
							</c:forEach>
						
						</td>
					</tr>
					<tr class="content">
						<td colspan="3" style="background-color: #ffffff; white-space: pre;">${detail.content}</td>
					</tr>
				</tbody>
			</table>

		</div>
		<!-- 목록, 수정, 삭제 버튼 -->
		
			<div align="right" style="width: 985px;">
				
				<a href="list" class="btn bg-dark" style="color: white">목록</a>
				<!-- 비회원 게시물 -->
				<c:if test="${detail.password!=null}">
					<a href="checkpwd?id=${param.id}" class="btn bg-dark" style="color: white">수정</a>
					<a href="delpost?id=${param.id}" class="btn bg-dark" style="color: white">삭제</a>
				</c:if>
				<!-- 회원 자신의 게시물일때 -->
				<c:if test="${detail.password==null && detail.writer_id == sessionScope.mem_nickname }">
					<a href="#" class="btn bg-dark" style="color: white" onClick="reconfirmEdit()">수정</a>
					<a href="#" class="btn bg-dark" style="color: white" onClick="reconfirmDel()">삭제</a>
				</c:if>
			</div>
			
		</form>
		
		<!-- 댓글 작성 폼 -->
		<form method="post" action="regreply">
			<div class="row" align="right" style="padding: 20px 0px 20px 0px;">
				<!-- 비회원 댓글 -->
				<c:if test="${sessionScope.mem_id==null }">
				<c:set var="form_width" value="690"/>
					<div style="width: 200px; margin: 0px 10px 0px 0px;" >
						<input type="text" class="form-control" placeholder="작성자" name="writer_id" id="writer_id" style="width: 200px;"/>
						<input type="password" class="form-control" placeholder="비밀번호" name="password" id="password"
						style="width: 200px; margin: 10px 0px 0px 0px;"/>
					</div>
				</c:if>
				<c:if test="${sessionScope.mem_id!=null }">
				<c:set var="form_width" value="900"/>	
					<input type="hidden" name="writer_id" value="${sessionScope.mem_nickname}"/>
					<input type="hidden" name="password"  value=""/>
				</c:if>				
				<div>
					<textarea class="form-control" placeholder="댓글을 입력해주세요" name="content" id="content" maxlength="500" 
					style="width: ${form_width}px; height: 87px;"></textarea>
				</div>
				<p>
					<input type="hidden" name="post_id" value="${detail.id}"/>
				</p>
				<div style="width: 100px" >
					<input type="submit" onClick="validation(event)" class="btn bg-dark" style="color: white; width: 90px; height: 87px;" value="작성" />
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
						<td>${r.writer_id }
						<c:if test="${r.password==null }">
							<span style="color:red">[★]</span>
						</c:if>
						</td>
						<td class="content" style="white-space: pre-wrap;">${r.content }</td>
						<td>
						<!-- 비회원 -->
						<c:if test="${r.password!=null}">
							<a href="delreply?rid=${r.id}&pid=${param.id}" style="color: red">[x]</a>
						</c:if>
						<!-- 회원 -->
						<c:if test="${r.password==null && r.writer_id==sessionScope.mem_nickname}">
							<a href="delmemreply?rid=${r.id}&pid=${param.id}&writer=${r.writer_id}" style="color: red">[x]</a>
						</c:if>
						</td>
						<td><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${r.regdate }"/></td>
					</tr>
				</c:forEach>
			</table>
		
		</div>
	</div>
	
