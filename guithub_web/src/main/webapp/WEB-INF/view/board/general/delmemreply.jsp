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
	
		var rid = getParameter("rid");
		var pid = getParameter("pid");
		var writer = getParameter("writer");
	</script>
	
	<!-- 댓글 삭제 성공 -->
	<c:if test="${success==true}">
	<script>
		alert("삭제되었습니다.");
		location.href="detail?id="+pid;
	</script>
	</c:if>
	
	<!-- 권한 없는 유저의 잘못된 url 접근 경고-->
	<c:if test="${warning==true}">
	<script>
		alert("접근 권한이 없습니다.");
		location.href="detail?id="+pid;
	</script>
	</c:if>
		
	<script>
		if(confirm("정말로 삭제하시겠습니까?")){
			location.href="delmemreply?rid="+rid+"&pid="+pid+"&writer="+writer+"&confirm=true";
		}else{
			location.href="detail?id="+pid;
		}
	
	</script>
