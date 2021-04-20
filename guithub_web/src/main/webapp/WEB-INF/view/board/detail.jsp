<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd; width: 1000px;">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">${detail.title}</th>
					</tr>
				</thead>
				<tbody>
				<!-- 글 내용 -->
					<tr style="background-color: #eeeeee;"> 
						<td style = "width: 15%">${detail.writer_id}</td>
						<td style= "text-align: right;">${detail.regDate}</td>
						<td style= "width:15%; text-align: right;">${detail.hit}</td>
					</tr>
					<tr>
						<th style="width: 15%; background-color: #eeeeee;">첨부파일</th>
						<td colspan="2" >${detail.files}</td>
					</tr>
					<tr class="content">
						<td colspan="3" style="background-color: #ffffff;">
						${detail.content}
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<a href="bbs.jsp" class="btn btn-primary">목록</a>
				<a href="update.jsp?bbsID=" class="btn btn-primary">수정</a>
				<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="deleteAction.jsp?bbsID=" class="btn btn-primary">삭제</a>
			</div>
		</div>
	</div>