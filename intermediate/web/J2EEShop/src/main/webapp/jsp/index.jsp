<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>OneMall商城首页</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	
	<body>
		<div class="container-fluid">
			<jsp:include page="header.jsp"/>
		</div>
		<span>${el}</span>
		
		<!-- 轮播图 -->
		<div class="container-fluid">
			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="margin: 0 15px;">
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/images/banner/1.jpg">
						<div class="carousel-caption">
						</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/images/banner/2.jpg">
						<div class="carousel-caption">
						</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/images/banner/3.jpg">
						<div class="carousel-caption">
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		
		<div class="container-fluid m-15" style="padding: 10px 0;">
			<div class="col-md-12 bg-info">
				<h3>热门商品&nbsp;&nbsp;<small>为您推荐</small></h3>
			</div>
			<div class="col-md-12">
				<c:forEach var="item" items="${hotList}">
					<div class="col-md-2" style="text-align:center;height: 200px;padding: 10px 0;">
						<a href="${pageContext.request.contextPath}/productServlet?method=productInfo&pid=${item.pid}">
							<img src="${pageContext.request.contextPath}/${item.pimage}" width="130" height="130"
							     style="display: inline-block;">
						</a>
						<p>
							<a href="${pageContext.request.contextPath}/productServlet?method=productInfo&pid=${item.pid}"
							   style='color:#666'>${item.pname}</a></p>
						<p><span style="font-size: 16px color: #8E8E8E; ">&yen;${item.shop_price}</span></p>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<jsp:include page="footer.jsp"/>
	
	</body>
</html>