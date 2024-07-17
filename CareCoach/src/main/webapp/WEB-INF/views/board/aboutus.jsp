<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />
<div class="page-contents">
        <div style="display: flex; align-items: center; justify-content: space-between;">
            <h1 style="text-align:left; padding-left: 100px; font-size: 2em; flex-grow: 1;">${list[0].title}</h1>
            <img src="${pageContext.request.contextPath}/resources/images/CareCoach-logo.png" alt="CareCoach" style="width: 200px; height: auto; margin-left: 20px;"/>
        </div>
    <br>
    <hr style="margin-left:100px; width: 600px;">
    <br>
    <div style="padding-left: 100px; padding-right: 100px;width 1000px; font-size: 2em;">${list[0].content}</div>
</div>

<%@ include file="../footer.jsp" %>  