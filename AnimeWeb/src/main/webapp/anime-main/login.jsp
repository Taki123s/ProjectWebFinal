<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Anime</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/plyr.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/ds/style.css" />
<script src="https://kit.fontawesome.com/9847adceef.js"></script>
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<c:url var="index" value="/anime-main/index.jsp" />
	<c:url var="login" value="/anime-main/login.jsp" />
	<c:url var="signup" value="/anime-main/signup.jsp" />
	<c:url var="urlAvatar"
		value="${request.rervletContext.realPath}/anime-main/storage/avatarUser/${sessionScope.user.avatar}" />
	<fmt:setLocale value="${sessionScope.LANG}" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<c:url var="categories" value="/anime-main/categories.jsp" />
	<c:url var="blog-details" value="/anime-main/blog-details.jsp" />
	<c:url var="blog" value="/anime-main/blog.jsp" />
	<c:url var="anime-watching" value="/anime-main/anime-watching.jsp" />
	<c:url var="anime-details" value="/anime-main/anime-deails.jsp" />
	<c:url var="loginServlet" value="login" />
	<!-- Header Section Begin -->
	<fmt:setLocale value="vi_VN" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<c:import url="/anime-main/header.jsp" />
	<!-- Header End -->

	<!-- Normal Breadcrumb Begin -->
	<section class="normal-breadcrumb set-bg"
		data-setbg="img/normal-breadcrumb.jpg">

		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="normal__breadcrumb__text">
						<h2>
							<fmt:message>menu.login</fmt:message>
						</h2>
						<p>
							<fmt:message>content.welcome</fmt:message>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Normal Breadcrumb End -->

	<!-- Login Section Begin -->
	<section class="login spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="login__form">
						<h3>
							<fmt:message>menu.login</fmt:message>
						</h3>
						<form action="${loginServlet}" method="get">
							<div class="input__item">
								<input required="required" type="text" placeholder="User Name"
									name="loginName"> <span class="icon_mail"></span>
							</div>
							<div class="input__item">
								<input type="password" required="required"
									placeholder="Password" name="loginPassword"> <span
									class="icon_lock"></span>
							</div>

							<button type="submit" class="site-btn" value="login"
								name="accountBtn">
								<fmt:message>button.login</fmt:message>
							</button>
						</form>
						<br>
						<div style="color: red">${errorLogin}</div>

					</div>
				</div>
				<div class="col-lg-6">
					<div class="login__register">
						<h3>
							<fmt:message>signup.message</fmt:message>
						</h3>
						<a href="${signup}" class="primary-btn"><fmt:message>button.signup</fmt:message></a>
					</div>
				</div>
			</div>
			<div class="login__social">
				<div class="row d-flex justify-content-center">
					<div class="col-lg-6">
						<div class="login__social__links"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Login Section End -->

	<!-- Footer Section Begin -->
	<c:import url="/anime-main/footer.jsp" />
	<!-- Footer Section End -->

	<!-- Search model Begin -->
	<!-- Search model end -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/player.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>


</body>

</html>