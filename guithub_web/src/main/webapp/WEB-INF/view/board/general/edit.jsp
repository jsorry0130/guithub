<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- 글 작성 양식 -->
	<div class="container">
		<div class="row">
			<form method="post" enctype="multipart/form-data">
				<table class="table" style="text-align: center; width: 1000px; border: 1px solid white;">
					<thead>
						<tr>
							<th class="bg-dark" colspan="3" style="text-align: center; color: white;">게시물 수정</th>
						</tr>
					</thead>
					<tbody style="background-color:#eeeeee;">
						<tr>
							<td/>
							<td style = "width: 15%" >${detail.writer_id}</td>
							<td style = "width: 15%" >********</td>
						</tr>
						<tr>
							<td colspan="3">
							<input type="text" class="form-control" name="title" maxlength="50"
							value="${detail.title}">
							</td>
						</tr>
						<tr><!-- PostVO의 files와 파일태그의 name값이 달라야 컨트롤러에서 읽어낼수있다. -->
							<td colspan="3"><input type="file" multiple class="form-control" name="file"></td>
						</tr>
						<tr>
							<td colspan="3">
							<textarea class="form-control" name="content" maxlength="2048" style="height: 400px;">${detail.content}
							</textarea>
							</td>
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