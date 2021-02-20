<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.post.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.comment.model.*"%>

<%
	PostVO postVO = (PostVO) request.getAttribute("postVO"); 
%>
<%
    CommentService commentSvc = new CommentService();
    List<CommentVO> list = commentSvc.getComsByPostNo(postVO.getPostNo());
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>List Comment Of A Post Front</title>
</head>
<body>
<table id="table-1">
		<tr><td><h3>Hairtopia Post-Comment: Front-end</h3><h4>( MVC )</h4></td></tr>
	</table>
	<table>
		<tr>
			<th>設計師編號</th>
			<th>貼文內容</th>
			<th>上傳時間</th>
			<th>照片1</th>
			<c:if test='${not empty postVO.postPic2}'>
			<th>照片2</th>
			</c:if>
			<c:if test='${not empty postVO.postPic3}'>
			<th>照片3</th>
			</c:if>	
			<th>新增留言</th>
			
		</tr>
		<tr> 
			<td>${postVO.desNo}</td>
			<td>${postVO.postCon}</td>
			<td><fmt:formatDate value="${postVO.postTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			
		<td><img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=post&column=postPic1&idname=postNo&id=${postVO.postNo}" alt='沒有圖片' /></td>
	 	<c:if test='${not empty postVO.postPic2}'>
			<td>
		  		<img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=post&column=postPic2&idname=postNo&id=${postVO.postNo}" alt='沒有圖片' />
			</td>
		</c:if>
		
		<c:if test='${not empty postVO.postPic3}'>
			<td>
		   		<img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=post&column=postPic3&idname=postNo&id=${postVO.postNo}" alt='沒有圖片' />
			</td>
		</c:if>
		
			<td>
		<form method="post" action="<%=request.getContextPath()%>/comment/comment.do">
		
		<input type="text" name="comCon">
		<input type="hidden" name="postNo" value="${postVO.postNo}">
		<input type="hidden" name="memNo" value="1">
		<input type="hidden" name="action" value="insert_Front">
		<input type="submit" value="送出">
		</form>
		</td>			
		</tr>
	</table>
	
<table>
	<tr>
		<th>會員編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
	</tr>
	<c:forEach var="commentVo" items="${list}">
	<c:if test= '${commentVo.comStatus != false}'>
		<tr ${(commentVo.comNo==param.comNo) ? 'bgcolor=#CCCCFF':''}>
			
				<td>${commentVo.memNo}</td>
				<td>${commentVo.comCon}</td>
				<td>${commentVo.comTime}</td>
				
				<td>
				<FORM method="post" action="<%=request.getContextPath()%>/comment/comment.do">
			     <input type="text" name="comCon" value="${commentVo.comCon}">
			     <input type="submit" value="修改">
			     <input type="hidden" name="postNo" value= "${postVO.postNo}">
			     <input type="hidden" name="comNo" value= "${commentVo.comNo}">
			     <input type="hidden" name="action"	value="update_Comment_Front"></FORM>
				</td>
				
				<td>
				<FORM method="post" action="<%=request.getContextPath()%>/comment/comment.do">
			     <input type="submit" value="${commentVo.comStatus eq true?"刪除":""}">
			     <input type="hidden" name="postNo" value= "${postVO.postNo}">
			     <input type="hidden" name="comNo" value= "${commentVo.comNo}">
			     <input type="hidden" name="comStatus" value= "${commentVo.comStatus eq true?"false":"true"}"">
			     <input type="hidden" name="action"	value="delete_Comment_Front"></FORM>
				</td>
				
		</tr>
	</c:if>
	</c:forEach>
</table>
</body>
</html>