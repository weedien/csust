<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cart" scope="session" class="cn.weedien.csust.medium.shop.domain.Cart"/>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>J2EE商城购物车</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
	</head>
	
	<body>
		<jsp:include page="header.jsp"/>
		
		<c:if test="${empty cart || empty cart.cartItems}">
			<span class="m-15">您还没有购买任何商品</span>
		</c:if>
		
		<c:if test="${not (empty cart || empty cart.cartItems)}">
			<div class="container">
				<div class="row">
					<div style="margin: 10px auto 0; width:950px;">
						<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
						商品金额:<strong style="color: #ff6600;">￥${cart.total}元</strong>
						<table class="table table-bordered">
							<tbody>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${cart.cartItems}" var="cartItem">
									<tr class="active">
										<td class="td-width-20">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${cartItem.product.pimage}"
											     width="70"
											     height="60" alt="img">
										</td>
										<td class="td-width-20">
											<a target="_blank">${cartItem.product.pname}</a>
										</td>
										<td class="td-width-15">
											￥${cartItem.product.shop_price}
										</td>
										<td class="td-width-10">
												${cartItem.count}
										</td>
										<td class="td-width-15">
											<span class="subtotal">￥${cartItem.subtotal}</span>
										</td>
										<td class="td-width-10">
												<span>
													<a
														href="${pageContext.request.contextPath }/cartServlet?method=cartAdd&pid=${cartItem.product.pid}&count=1"
														class="add">添加</a>
												</span>
											<span>
												<a
													href="${pageContext.request.contextPath }/cartServlet?method=removeItem&pid=${cartItem.product.pid}"
													class="delete">删除</a>
											</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				<div style="margin-right:130px;">
					<div style="text-align:right;">
						赠送积分: <em style="color:#ff6600;">${cart.total }</em>&nbsp; 商品金额: <strong
						style="color:#ff6600;">￥${cart.total }元</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<a href="${pageContext.request.contextPath }/cartServlet?method=cartClear" id="clear"
						   class="clear">清空购物车</a>
						<a href="${pageContext.request.contextPath }/orderServlet?method=addOrder">
							<input type="button" width="100" value="提交订单" name="submit" class="submit-order">
						</a>
					</div>
				</div>
			
			</div>
		</c:if>
		<c:if test="${empty cart.cartItems }">
			<div class="m-15">
				<img alt="" src="${pageContext.request.contextPath }/images/cart-empty.png">
				<a href="${pageContext.request.contextPath }/">返回首页</a>
			</div>
		</c:if>
		
		<jsp:include page="footer.jsp"/>
	
	</body>

</html>