<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%
    PostService postSvc = new PostService();
    List<PostVO> list = postSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>所有貼文-List All Post</title>
</head>
<body>
<table>
	<tr>
		<th>貼文編號aa</th>
		<th>設計師編號</th>
		<th>貼文內容</th>
		<th>照片</th>
		<th>發文時間</th>
		<th>貼文狀態</th>
		<th>貼文優先度</th>
	</tr>
	<c:forEach var="postVo" items="${list}">
		
		<tr>
			<td>${postVo.postNo}</td>
			<td>${postVo.desNo}</td>
			<td>${postVo.postCon}</td>
			<td>${postVo.postPic1}</td>
			<td><fmt:formatDate value="${postVo.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${postVo.postStatus}</td> 
			<td>${postVo.postPror}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>