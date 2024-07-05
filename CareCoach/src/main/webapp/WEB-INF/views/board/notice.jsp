<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />

<div class="page-contents">
    <form id="boardForm" name="boardForm" method="post">
 		
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="result" items="${list}" varStatus="status">
                    <tr>
                        <td><c:out value="${result.id}"/></td>
                        <td><a href='#' onClick='fn_view(${result.id})'><c:out value="${result.title}"/></a></td>
                        <td><c:out value="${result.user_id}"/></td>           
                        <td><c:out value="${result.created_dt}"/></td>
                        <td><c:out value="${result.view_cnt}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        

    </form>
<script>

//글조회
function fn_view(id){
    
    var form = document.getElementById("boardForm");
    var url = "<c:url value='/board/viewContent.do'/>";
    url = url + "?id=" + id;
    
    form.action = url;    
    form.submit(); 
}
</script>
</div>
<%@ include file="../footer.jsp" %>  