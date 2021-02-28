<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>

<jsp:useBean id="postSvc"  scope="page" class="com.post.model.PostService" />
<html>

<head>
    <title>Hairtopia</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Prata&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/aos.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/jquery.timepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/tagify/tagify.css">
    <script src="<%=request.getContextPath()%>/dist/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/jquery.easing.1.3.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/jquery.waypoints.min.js"></script><!-- << -->
	<script src="<%=request.getContextPath()%>/dist/js/jquery.stellar.min.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/jquery.magnific-popup.min.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/jquery.animateNumber.min.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/jquery.timepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/bootstrap-datepicker.js"></script>
	<script src="<%=request.getContextPath()%>/dist/js/popper.min.js"></script>
	

    
</head>
<style>

</style>

<body>

   
<!-- carousel -->
    <div id="carouselExampleSlidesOnly" class="carousel slide mb-5" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://picsum.photos/1300/300?random=2" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="https://picsum.photos/1300/300?random=3" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="https://picsum.photos/1300/300?random=4" class="d-block w-100">
            </div>
        </div>
    </div>
    <!-- csrousel end -->
    <h1></h1>
	<div class="container post">
		<div class="card-columns ">
		<c:forEach  var="postVO" items="${postSvc.all}">
			<a href="<%=request.getContextPath()%>/post/post.do?postNo=${postVO.postNo}&action=Display_fromListAll" >
				<div class="card">
					<img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=post&column=postPic1&idname=postNo&id=${postVO.postNo}"
					 class="card-img-top post-img" data-toggle="modal" data-target="#post${postVO.postNo}Modal" />
				</div>
			</a>
		</c:forEach>
		</div>
	</div>


    <c:if test="${openModal != null}" >
    <!-- Post Modal -->
            <div class="modal fade" id="postModal" tabindex="-1"  aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable listOne">
                    <div class="modal-content">
                            <button type="button" class="postClose text-right" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        <div class="modal-body p-0 m-0">
                        	<div class="includePage">
                            	<jsp:include page="/front-end/Comment/listPostWithComments_front.jsp" />
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
 			$("#postModal").modal({show: true},'handleUpdate');
            </script>
	<!-- Post END -->
	</c:if>
	
	
	
</body>

</html>