<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Post</title>
</head>
<body>
<a href='listAllPost.jsp'>List</a>
	<form method="post" action="/CEA102G2/post/post.do">
		<h2>設計師編號</h2>
		<input type="text" name="desNo"><br>
		<h2>貼文內容</h2>
		<input type="text" name="postCon"><br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出">
	</form>
</body>
</html>