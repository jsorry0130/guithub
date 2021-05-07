<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${sessionScope.mem_id == null}">
		<script type="text/javascript">	
			alert("로그인 후 이용해주세요.");
			location.href="/home/login";
		</script>
	</c:if>	
	
	<!-- 글 작성 양식 -->
	<script>
        const add_fileForm = () => {
            const addFileForm = document.getElementById("addFileForm");
            const newP = document.createElement('tr');
            newP.innerHTML = "<td colspan='2'><input type='file' class='form-control' name='file'></td>";
            newP.innerHTML += "<td style='width: 15%'><button type='button' class='btn' style='color:red' onclick='remove(this)'>파일삭제</button></td>";
            addFileForm.appendChild(newP);
        }
        
        const remove = (obj) => {
        	document.getElementById('addFileForm').removeChild(obj.parentNode.parentNode);
        }
    </script>

	<div class="container">
		<h1 style="color: white;">타브악보 게시판</h1>
		<h3 style="color: white;">-회원-</h3>
		<div class="row">
			<form method="post" enctype="multipart/form-data">
				<table class="table" style="text-align: center; width: 1000px; border: 1px solid white;">
					<thead>
						<tr>
							<th class="bg-dark" colspan="3" style="text-align: center; color: white;">게시물 등록</th>
						</tr>
					</thead>
					<tbody style="background-color:#eeeeee;">
						<tr>
							<td/>
							<td style = "width: 15%" ><input type="hidden" name="writer_id" value="${sessionScope.mem_nickname}"></td>
							<td/>
						</tr>
						<tr>
							<td colspan="3"><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50"></td>
						</tr>
						<tr><!-- PostVO의 files와 파일태그의 name값이 달라야 컨트롤러에서 읽어낼수있다.-->
							<td colspan="2"><input type="file" class="form-control" name="file"></td>
							<td style="width: 15%"><button type="button" class="btn" onclick="add_fileForm()">파일추가</button></td>
						</tr>
					</tbody>
					<tbody style="background-color:#eeeeee;" id="addFileForm">
					
					</tbody>
					<tbody style="background-color:#eeeeee;">
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