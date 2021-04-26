<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- delCnt 값을 토대로 삭제 성공 여부 확인 알림 -->
    
	<c:if test="${delCnt == 1}">
		<script type="text/javascript">	
			alert("삭제가 완료되었습니다.");
			location.href='general';
		</script>
	</c:if>		
	<c:if test="${delCnt == 0}">
		<script type="text/javascript">	
			alert("비밀번호가 일치하지 않습니다.");
			location.href='general';
		</script>
	</c:if>	