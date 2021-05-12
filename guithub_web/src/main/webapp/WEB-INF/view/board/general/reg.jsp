<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- 글 작성 양식 -->
	<script>
		function validation(e) {
			  var writer_id = document.getElementById("writer_id").value;
			  var title = document.getElementById("title").value;
			  var password = document.getElementById("password").value;
			  var content = document.getElementById("content").value;
			  
			  if (writer_id=="" || password=="" || title=="" || content=="") {
				  e.preventDefault();
			      alert("빈칸을 채워주세요!");
			  }
			  
			}
	
		//첨부파일 폼 추가 스크립트
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
		<div class="row">
			<form method="post" enctype="multipart/form-data">
				<table class="table" style="text-align: center; width: 1000px; border: 1px solid white;">
					<thead>
						<tr>
							<th class="bg-dark" colspan="3" style="text-align: center; color: white;">게시물 등록</th>
						</tr>
					</thead>
					<tbody style="background-color:#eeeeee;">
						<!-- 비회원의 임시 닉네임, 패스워드 입력 폼 --> 
						<c:if test="${sessionScope.mem_id==null}">
						<tr>
							<td/>
							<td style = "width: 15%" ><input type="text" class="form-control" placeholder="작성자" name="writer_id" id="writer_id"></td>
							<td style = "width: 15%" ><input type="password" class="form-control" placeholder="비밀번호" name="password" id="password"></td>
						</tr>
						</c:if>
						<!-- 회원 로그인시 -->
						<c:if test="${sessionScope.mem_id!=null}">
							<input type="hidden" name="writer_id" value="${sessionScope.mem_nickname}" />
							<input type="hidden" name="password"  value="" />
						</c:if>						
						<tr>
							<td colspan="3"><input type="text" class="form-control" placeholder="글 제목" name="title" id="title" maxlength="50"></td>
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
							<td colspan="3"><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" id="content" style="height: 400px;"></textarea></td>
						</tr>
						<tr>
							<td/>
							<td/>
							<td style="width:10%">
								<button type="submit" onClick="validation(event)" class="btn bg-dark" value="글쓰기" style="color: white">작성</button>
							</td>
						</tr>
						
					</tbody>
				</table>

			</form>
		</div>
	</div>