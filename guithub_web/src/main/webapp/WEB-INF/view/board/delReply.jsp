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
	</script>

	<c:if test="${delCnt == 1}">
		<script type="text/javascript">	
			alert("삭제가 완료되었습니다.");
 			var id = getParameter("pid");
			location.href="detail?id="+id;
		</script>
	</c:if>		
	<c:if test="${delCnt == 0}">
		<script type="text/javascript">	
			alert("비밀번호가 일치하지 않습니다.");
		</script>
	</c:if>	
	
	<div class="container">
		<!-- 댓글 삭제 패스워드 확인 페이지 -->	
		<form method="post" action="delReply?rid=${param.rid}&pid=${param.pid}">	
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd; width: 1000px;">
				<thead class="bg-dark">
					<tr>
						<th colspan="3" style="text-align: center; color: white;">댓글 삭제를 위해 비밀번호를 입력해주세요.</th>
					</tr>
				</thead>
				<tbody style="background-color: #eeeeee;">
				<!-- 글 내용 -->
					<tr> 
						<td style="width: 33%"></td>
						<td style="width: 33%">
						<input type="password" class="form-control" placeholder="비밀번호" name="password" style="width: 320px;">
						</td>
						<td><input type="submit" class="btn bg-dark" style="color: white" value="삭제"></td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
	</div>