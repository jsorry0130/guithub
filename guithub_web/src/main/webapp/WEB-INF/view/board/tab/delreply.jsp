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
	
	var pid = getParameter("pid");
	var rid = getParameter("rid");
	</script>
	
	<!-- 현재 멤버의 닉네임과 댓글 작성자가 다르다면 -->
	<c:if test="${sessionScope.mem_nickname!=param.rwriter}">
	<script>
		alert("삭제 권한이 없습니다.");
		location.href='detail?id='+pid;
	</script>
	</c:if>
	
	<!-- 현재 멤버 닉네임과 댓글 작성자가 같다면 -->
	<c:if test="${sessionScope.mem_nickname==param.rwriter}">
	<script>
	if(confirm("정말로 삭제하시겠습니까")==true){
				alert("삭제되었습니다.");
				location.href="delreply?rid="+rid+"&confirm=true&pid="+pid;
			}else{
				location.href="detail?id="+pid;
			}
	
	</script>
	</c:if>