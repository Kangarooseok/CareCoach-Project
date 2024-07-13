<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />
<div class="page-contents">
<h1 style="padding-left: 100px; font-size: 2em;">${list[0].title}</h1>
<hr style="width: 500px;margin-left: 100px;">
<div style="padding-left: 100px; padding-right: 100px;width 1000px; font-size: 2em;">${list[0].content}</div>
</div>

<%@ include file="../footer.jsp" %>  