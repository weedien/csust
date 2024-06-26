<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		
		<style>
        font {
            color: #666;
            font-size: 22px;
            font-weight: normal;
            padding-right: 17px;
        }
		</style>
		
		
		<script>
			/**
			 * AJAX异步校验验证码
			 */
			$(function () {
				$("#confirmimg").blur(function () {
					var value = $(this).val();
					if (value != null) {
						$.post(
							"${pageContext.request.contextPath}/userServlet?method=checkValidateCode",
							{"validateCode": value},
							function (data) {
								var boole = data.boole;
								if (boole) {
									ValidateCodeInfo = "验证码正确";
									$("#ValidateCodeInfo").css("color", "green");
//                            $("#regBut").attr("disabled",true);
									$("#regBut").disabled = true;
								} else {
									ValidateCodeInfo = "验证码错误";
									$("#ValidateCodeInfo").css("color", "red");
//                            $("#regBut").attr("disabled",false);
									$("#regBut").disabled = false;
								}
								$("#ValidateCodeInfo").html(ValidateCodeInfo);
							},
							"json"
						);
					}
				})
			});

			/**
			 * 更换图片验证码
			 */
			function changeimg(obj) {
				obj.src = "${pageContext.request.contextPath}/ValidateCode?time=" + new Date().getTime();
			}
		
		</script>
	</head>
	<body>
		<jsp:include page="/jsp/header.jsp"/>
		
		<div class="container"
		     style="width: 100%; height: 460px; background: #FF2C4C url('${pageContext.request.contextPath}/images/loginbg.jpg') no-repeat;">
			<div class="row">
				<div class="col-md-7">
				</div>
				
				<div class="col-md-5">
					<div
						style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
						<span>会员登录</span>USER LOGIN
						<div>&nbsp;</div>
						<form class="form-horizontal"
						      action="${pageContext.request.contextPath}/userServlet?method=login"
						      method="post">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="username" name="username"
									       placeholder="请输入用户名">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-6">
									<input type="password" class="form-control" id="inputPassword" name="password"
									       placeholder="请输入密码">
								</div>
							</div>
							<div class="form-group">
								<label for="confirmimg" class="col-sm-2 control-label">验证码</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="confirmimg" name="ValidateCode"
									       placeholder="请输入验证码">
									<span id="ValidateCodeInfo"></span>
								</div>
								<div class="col-sm-3">
									<img src="${pageContext.request.contextPath}/jsp/validatecode.jsp"
									     onclick="changeimg(this)"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="checkbox">
										<label> <input type="checkbox" name="autoLogin" value="1"> 自动登录</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label> <input type="checkbox"> 记住用户名
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" width="100" value="登录" name="submit"
									       style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<jsp:include page="footer.jsp"/>
	
	</body>
</html>