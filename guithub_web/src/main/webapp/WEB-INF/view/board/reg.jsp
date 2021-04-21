<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- 글 작성 양식 -->
	<div class="container">
		<div class="row">
			<form method="post" action="regPost">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd; width: 1000px;">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시물 등록</th>
						</tr>
					</thead>
					<tbody>
						<tr><!-- <input type="text" class="form-control" placeholder="작성자" name="writer_id"> -->
							<td style = "width: 15%"><input type="text" class="form-control" placeholder="작성자" name="writer_id"></td>
							<td><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50"></td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height: 400px;"></textarea></td>
						</tr>
					</tbody>
				</table>
				<button type="submit" class="btn btn-primary pull-right" value="글쓰기">작성</button>
			</form>
		</div>
	</div>