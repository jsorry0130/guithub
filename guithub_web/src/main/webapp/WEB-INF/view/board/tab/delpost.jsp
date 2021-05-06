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
	</script>
	
	<c:if test="${sessionScope.mem_nickname!=nickname}">
	<script>
		alert("삭제 권한이 없습니다.");
		location.href="detail?id="+id;
	</script>
	</c:if>
	
	<c:if test="${sessionScope.mem_nickname==nickname}">
	<script>
	if(confirm("정말로 삭제하시겠습니까")==true){
				alert("삭제되었습니다.");
				location.href="delpost?id="+id+"&delcheck=1";
			}else{
				location.href="detail?id="+id;
			}
	
	</script>
	</c:if>

	
	