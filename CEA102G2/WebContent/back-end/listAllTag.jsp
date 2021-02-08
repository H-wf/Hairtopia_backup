<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tag.model.*"%>
<%
    TagService tagSvc = new TagService();
    List<TagVO> list = tagSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>List All Tag</title>
</head>
<body>
<table>
	<tr>
		<th>標籤編號</th>
		<th>標籤名稱</th>
	</tr>
	<c:forEach var="tagVo" items="${list}">
		
		<tr>
			<td>${tagVo.tagNo}</td>
			<td>${tagVo.tagName}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>