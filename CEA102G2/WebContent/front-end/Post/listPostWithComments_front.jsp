<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.post.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.comment.model.*"%>

<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");
%>
<%
	CommentService commentSvc = new CommentService();
	List<CommentVO> list = commentSvc.getComsByPostNo(postVO.getPostNo());
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>List Comment Of A Post Front</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<style>
	* {
		box-sizing: border-box;
	}
	.input-group{
		max-width: 100%;
	}
	img {
		max-width: 100%;
	}
	
	.card-body {
		padding: 0;
	}
	
	.comTime {
		margin-left: 80%;
	}
	
	.comCon {
		font-size: 14px;
		color: black;
	}
	
	@media screen and (max-width: 576px) {
		width
		:
		100%;
		
	
	}
	
	@media ( min-width :576px) and (max-width:768px) {
		div.my_div1 {
			width: 540px;
		}
	}
	
	@media ( min-width :768px) and (max-width:992px) {
		div.my_div1 {
			width: 720px;
		}
	}
	
	@media ( min-width :992px) and (max-width:1200px) {
		div.my_div1 {
			width: 960px;
		}
	}
	
	@media ( min-width :1200px) {
		div.my_div1 {
			width: 1140px;
		}
	}
</style>
<body>
	<!-- 參考原版檔案在oldFile裡 -->
		<div class="row g-0">
			<div class="col-md-6">
				<img
					src="<%=request.getContextPath()%>/PicFinder?pic=1&table=post&column=postPic1&idname=postNo&id=${postVO.postNo}"
					class="card-img" />
			</div>
			<div class="col-md-6 p-0">
					<div class="card-title">
						<h2>${postVO.desNo}</h2>
						<h5 class="m-0">${postVO.postCon}</h5>
						<small class="text-muted"><fmt:formatDate
								value="${postVO.postTime}" pattern="yyyy-MM-dd HH:mm:ss" /></small>
					</div>
					<p class="card-text">

						<ul class="list-unstyled">
							<c:forEach var="commentVo" items="${list}">
							<c:if test='${commentVo.comStatus != false}'>
								<li class="media">
									<!--            會員頭貼                  <img src="..." class="mr-3" alt="..."> -->
									<div class="media-body">
										<h5 class="mt-0 mb-1">${commentVo.memNo}</h5>
										<p class="comCon">${commentVo.comCon}</p>
										<small class="text-muted comTime">${commentVo.comTime}</small>
									</div>
								</li>
							</c:if>
							</c:forEach>
						</ul>
						<!--  有會員之後要判斷出刪除、修改按鈕 -->
					<form method="post"
								action="<%=request.getContextPath()%>/comment/comment.do">
                    <div class="input-group">
                        <input type="text" class="form-control"
										placeholder="輸入留言" name="comCon">
						<input type="hidden" name="postNo" value="${postVO.postNo}">
						<input type="hidden" name="memNo" value="1">
						<input type="hidden" name="action" value="insert">
                        <button class="btn btn-outline-secondary"
										type="submit">留言</button>
                    </div>
						</form>
                
				</div>
            </div>
</body>
</html>