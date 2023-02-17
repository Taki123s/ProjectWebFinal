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
	<fmt:setLocale value="${sessionScope.LANG}" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<c:url var="index" value="/anime-main/index.jsp" />
	<c:url var="login" value="/anime-main/login.jsp" />
	<c:url var="signup" value="/anime-main/signup.jsp" />
	<c:url var="loginServlet" value="login" />
	<c:url var="urlAvatar"
		value="${request.rervletContext.realPath}/anime-main/storage/avatarUser/${sessionScope.user.avatar}" />
	<c:url var="categories" value="/anime-main/categories.jsp" />
	<c:url var="blog-details" value="/anime-main/blog-details.jsp" />
	<c:url var="blog" value="/anime-main/blog.jsp" />
	<c:url var="anime-details" value="/anime-main/anime-details.jsp" />
	<c:url var="urlAvatarFilm"
		value="${request.rervletContext.realPath}/anime-main/storage/avatarMovie/${viewFilm.avatar}" />
	<c:url var="chapterBegin"
		value="${request.rervletContext.realPath}/anime-main/storage/chapter/${linkChap}" />
	<c:url var="goWatch" value="watching" />
	<c:url var="userComment" value="comment" />
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<c:import url="/anime-main/header.jsp" />
	<!-- Header End -->

	<!-- Breadcrumb Begin -->
	<div class="breadcrumb-option">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb__links">
						<a href="${index}"><i class="fa fa-home"></i> <fmt:message>menu.hompage</fmt:message></a>
						<span>${viewFilm.nameMovie}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Anime Section Begin -->
	<section class="anime-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="anime__video__player">
						<video id="player" controls data-poster="${urlAvatarFilm}">
							<source src="${chapterBegin}?${cr}" type="video/mp4">
							<!-- Captions are optional -->

						</video>
						<input type="text" id="openingValue" value="${chapter.opening}"
							hidden>
					</div>
					<div class="anime__details__episodes">
						<div class="section-title">
							<h5>
								<fmt:message>button.episodes</fmt:message>
							</h5>
						</div>
						<c:forEach var="chapter" items="${viewFilm.listchapter}">

							<a href="${goWatch}?idM=${viewFilm.idMovie}&&chapter=${chapter.index}">Ep
								${chapter.index}</a>

						</c:forEach>

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="anime__details__review">
						<div class="section-title">
							<h5>
								<fmt:message>content.reviews</fmt:message>
							</h5>
						</div>



						<c:forEach var="comment" items="${viewFilm.listComment}">
							<div class="anime__review__item">
								<div class="anime__review__item__pic">
									<c:url var="avt"
										value="/anime-main/storage/avatarUser/${comment.account.avatar}?${now}" />
									<img src="${avt}" alt="">
								</div>
								<div class="anime__review__item__text">
									<h6>
										${comment.account.userName} - <span>${comment.getDifferentTime(nowTime)}</span>
									</h6>
									<p>${comment.comment}</p>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="anime__details__form">
						<div class="section-title">
							<h5>
								<fmt:message>conten.comment</fmt:message>
							</h5>
						</div>
						<form action="${userComment}">
							<textarea placeholder="Your Comment" name="message"></textarea>
							<button type="submit" value="${viewFilm.idMovie}" name="saveID">
								<i class="fa fa-location-arrow"></i>
								<fmt:message>content.review</fmt:message>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Anime Section End -->

	<!-- Footer Section Begin -->
	<c:import url="/anime-main/footer.jsp" />
	<!-- Footer Section End -->

	<!-- Search model Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">
				<i class="icon_close"></i>
			</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
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
	<script type="text/javascript">
		function skip() {
			var vd = document.getElementById("player");
			vd.currentTime = $("#openingValue").val();
		}
	</script>
	<script type="text/javascript">
		window.onload = function() {
			$(".plyr__controls")
					.append(
							"<i id=\"\skipOpening\"\ class=\"\plyr__controls__item plyr__volume fa fa-fast-forward\"\ onclick=\"\skip()\"\ style=\"\left:110px;bottom: 14px;\"\ aria-hidden=\"\true\"\></i>")
		};
	</script>

</body>

</html>