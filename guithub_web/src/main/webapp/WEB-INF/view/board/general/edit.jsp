<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<script>
        const add_fileForm = () => {
            const addFileForm = document.getElementById("addFileForm");
            const newP = document.createElement('tr');
            newP.innerHTML = "<td colspan='3'><input type='file' class='form-control' name='file'></td>";
            newP.innerHTML += "<td style='width: 15%'><button type='button' class='btn' style='color:red' onclick='remove(this)'>파일삭제</button></td>";
            addFileForm.appendChild(newP);
        }
        
        const remove = (obj) => {
        	document.getElementById('addFileForm').removeChild(obj.parentNode.parentNode);
        }
    </script>
    
	<!-- 글 작성 양식 -->
	<div class="container">
		<div class="row">
			<form method="post" enctype="multipart/form-data">
				<table class="table" style="text-align: center; width: 1000px; border: 1px solid white;">
					<thead>
						<tr>
							<th class="bg-dark" colspan="4" style="text-align: center; color: white;">게시물 수정</th>
						</tr>
					</thead>
					<tbody style="background-color:#eeeeee;">
						<tr>
							<td colspan="2"/>
							<td style = "width: 15%" >${detail.writer_id}</td>
							<td style = "width: 15%" >********</td>
						</tr>
						<tr>
							<td colspan="4">
							<input type="text" class="form-control" name="title" maxlength="50"
							value="${detail.title}">
							</td>
						</tr>
					
						<tr>
							<td style="width: 15%; background-color: #eeeeee;">첨부파일</td>
							<td colspan="3" style="background-color: #ffffff; text-align: left;" >
								<c:forEach items="${files}" var="f">
									<span>${f.name} (${f.file_size}kb) </span>
									<span style="color:red;">
									[삭제]<input type="checkbox" name="delFiles" value="${f.id}"><br/>
									</span>
								</c:forEach>
							</td>
						</tr>
						
						<tr><!-- PostVO의 files와 파일태그의 name값이 달라야 컨트롤러에서 읽어낼수있다.-->
							<td colspan="3"><input type="file" class="form-control" name="file"></td>
							<td style="width: 15%"><button type="button" class="btn" onclick="add_fileForm()">파일추가</button></td>
						</tr>
					</tbody>
					<tbody style="background-color:#eeeeee;" id="addFileForm">
					
					</tbody>
					<tbody style="background-color:#eeeeee;">
						<tr>
							<td colspan="4">
							<textarea class="form-control" name="content" maxlength="2048" style="height: 400px;">${detail.content}
							</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="3">
							<td style="width:10%">
								<button type="submit" class="btn bg-dark" value="글쓰기" style="color: white">작성</button>
							</td>
						</tr>
						
					</tbody>
				</table>

			</form>
		</div>
	</div>