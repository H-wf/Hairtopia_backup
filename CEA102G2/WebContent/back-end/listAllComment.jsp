<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.comment.model.*"%>
<%
    CommentService commentSvc = new CommentService();
    List<CommentVO> list = commentSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List All Comment</title>
</head>
<body>
<table>
	<tr>
		<th>留言編號</th>
		<th>貼文編號</th>
		<th>會員編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
		<th>留言狀態</th>
	</tr>
	<c:forEach var="commentVo" items="${list}">
		
		<tr>
			<td>${commentVo.comNo}</td>
			<td>${commentVo.postNo}</td>
			<td>${commentVo.memNo}</td>
			<td>${commentVo.comCon}</td>
			<td>${commentVo.comTime}</td>
			<td>${commentVo.comStatus}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>