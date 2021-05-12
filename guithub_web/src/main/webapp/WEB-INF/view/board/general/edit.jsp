<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<script>
		//get 방식으로파라미터를 받는 함수
		function getParameter(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
	
		var id = getParameter("id");
		
		//첨부파일란 추가 함수
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
        
        function editSuccess(){
        	alert("수정하였습니다.");
        }
    </script>
    <c:if test="${check==false}">
    <script>
    	alert("비밀번호가 일치하지 않습니다!");
    	location.href="checkpwd?id="+id;
    </script>
    </c:if>
     <c:if test="${nopwd==true}">
    <script>
    	alert("비밀번호를 입력해주세요!");
    	location.href="checkpwd?id="+id;
    </script>
    </c:if>
	<c:if test="${warning==true}">
		<script type="text/javascript">	
			alert("권한이 없으며 잘못된 접근입니다!");
			location.href="list";
		</script>
	</c:if>
	
	<!-- 글 작성 양식 -->
	<div class="container">
		<div class="row">
			<form method="post" enctype="multipart/form-data" action="editPost">
				<input type="hidden" name="id" value="${param.id}"/>
				<input type="hidden" name="password" value="${param.password}" />	
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
								<button type="submit" class="btn bg-dark" onClick="editSuccess()" style="color: white">작성</button>
							</td>
						</tr>
						
					</tbody>
				</table>

			</form>
		</div>
	</div>