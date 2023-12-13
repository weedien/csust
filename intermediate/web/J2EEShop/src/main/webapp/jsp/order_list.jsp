<%@ page contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
	</head>

	<body>

		<jsp:include page="/jsp/header.jsp"/>

		<div class="container">
			<div class="row">
				<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach var="item" items="${orderList.data}">
							<tbody>
								<tr class="success">
									<th colspan="5">
										订单编号:${item.oid},&nbsp;&nbsp;订单状态:${item.state},&nbsp;&nbsp;
										<c:if test="${item.state == 1}">
											<a href="${pageContext.request.contextPath}/orderServlet?method=orderInfo&oid=${item.oid}">
												付款</a>
										</c:if>
										<c:if test="${item.state == 2}">
											等待发货
										</c:if>
										<c:if test="${item.state == 3}">
											确认收货
										</c:if>
										<c:if test="${item.state == 2}">
											订单结束
										</c:if>&nbsp;&nbsp;
										<span style="color: #b00; ">总金额:${item.total}</span>
										<span style="margin-left: 10px"><a style="color: red;"
																											 href="${pageContext.request.contextPath}/orderServlet?method=deleteOrder&oid=${item.oid}">取消订单</a></span>
									</th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<tr class="active">
									<td width="60" width="40%"><input type="hidden" name="id"
																										value="22"> <img
											src="${pageContext.request.contextPath}/${item.orderItems[0].product.pimage}"
											width="70"
											height="60"></td>
									<td width="30%"><a target="_blank">${item.orderItems[0].product.pname}</a></td>
									<td width="20%">￥${item.orderItems[0].product.shop_price}</td>
									<td width="10%">${item.orderItems[0].count}</td>
									<td width="15%"><span class="subtotal">￥${item.orderItems[0].subtotal}</span></td>
								</tr>
								<tr class="active">
									<td>.................</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
			<div style="text-align: center;">
				<ul class="pagination">
					<li>
						<c:if test="${orderList.pageNumber gt 1}">
							<a href="${pageContext.request.contextPath}/orderServlet?method=findByUid&pageNumber=${orderList.pageNumber - 1}"
								 aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
						</c:if>
					</li>

					<c:forEach var="i" begin="1" end="${orderList.totalPage}">
						<c:if test="${orderList.pageNumber eq i}">
							<li class="active"><a
									href="${pageContext.request.contextPath}/orderServlet?method=findByUid&pageNumber=${i}">${i}</a>
							</li>
						</c:if>
						<c:if test="${orderList.pageNumber != i}">
							<li>
								<a href="${pageContext.request.contextPath}/orderServlet?method=findByUid&pageNumber=${i}">${i}</a>
							</li>
						</c:if>
					</c:forEach>

					<li>
						<c:if test="${orderList.pageNumber lt orderList.totalPage}">
							<a href="${pageContext.request.contextPath}/orderServlet?method=findByUid&pageNumber=${orderList.pageNumber + 1}"
								 aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
						</c:if>
					</li>
				</ul>
			</div>
		</div>
		
		<jsp:include page="footer.jsp"/>

	</body>

</html>