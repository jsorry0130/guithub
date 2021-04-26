<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- 글 작성 양식 -->
	<div class="container">
		<div class="row">
			<form method="post" action="regPost">
				<table class="table" style="text-align: center; width: 1000px; border: 1px solid white;">
					<thead>
						<tr>
							<th class="bg-dark" colspan="3" style="text-align: center; color: white;">게시물 등록</th>
						</tr>
					</thead>
					<tbody style="background-color:#eeeeee;">
						<tr>
							<td/>
							<td style = "width: 15%" ><input type="text" class="form-control" placeholder="작성자" name="writer_id"></td>
							<td style = "width: 15%" ><input type="password" class="form-control" placeholder="비밀번호" name="password"></td>
						</tr>
						<tr><!-- <input type="text" class="form-control" placeholder="작성자" name="writer_id"> -->
							<td colspan="3"><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50"></td>
						</tr>
						<tr>
							<td colspan="3"><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height: 400px;"></textarea></td>
						</tr>
						<tr>
							<td/>
							<td/>
							<td style="width:10%">
								<button type="submit" class="btn bg-dark" value="글쓰기" style="color: white">작성</button>
							</td>
						</tr>
						
					</tbody>
				</table>

			</form>
		</div>
	</div>