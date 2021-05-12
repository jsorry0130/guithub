<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:if test="${delCnt == 1}">
		<script type="text/javascript">	
			alert("삭제가 완료되었습니다.");
			location.href="list";
		</script>
	</c:if>
	<c:if test="${warning==true}">
		<script type="text/javascript">	
			alert("권한이 없으며 잘못된 접근입니다!");
			location.href="list";
		</script>
	</c:if>