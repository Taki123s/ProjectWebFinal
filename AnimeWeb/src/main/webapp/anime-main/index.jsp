<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<fmt:setLocale value="${sessionScope.LANG}" />
<fmt:setBundle basename="anime.web.resources.app" />
</head>

<body>
	<c:url var="index" value="/anime-main/index.jsp" />
	<c:url var="login" value="/anime-main/login.jsp" />
	<c:url var="signup" value="/anime-main/signup.jsp" />
	<fmt:setLocale value="${sessionScope.LANG}" />

	<c:url var="categories" value="/anime-main/categories.jsp" />
	<c:url var="blogdetails" value="/anime-main/blog-details.jsp" />
	<c:url var="blog" value="/anime-main/blog.jsp" />
	<c:url var="anime-watching" value="/anime-main/anime-watching.jsp" />
	<c:url var="anime-details" value="/anime-main/anime-details.jsp" />
	<c:url var="anime_details" value="anime_details" />
	<c:url var="loginServlet" value="login" />
	<c:url var="urlAvatar"
		value="${request.rervletContext.realPath}/anime-main/storage/avatarUser/${sessionScope.user.avatar}" />
	<c:url var="profileServlet" value="/anime-main/profile.jsp" />
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<c:import url="/anime-main/header.jsp" />
	<!-- Header End -->

	<!-- Hero Section Begin -->
	<section class="hero">
		<div class="container">
			<div class="hero__slider owl-carousel">
				<div class="hero__items set-bg" data-setbg="img/hero/thangtu.jpg">
					<div class="row">
						<div class="col-lg-6">
							<div class="hero__text">
								<div class="label">Romance</div>
								<h2>Shigatsu wa Kimi no Uso</h2>
								<p>Nội dung Câu chuyện kể về Arima Kousei, một thần đồng
									piano. Nhưng kể từ sau chấn động tâm lí do cái sự qua đời của
									mẹ cậu, Kosei đã không thể nghe thấy bất kì âm thanh nào....</p>

							</div>
						</div>
					</div>
				</div>
				<div class="hero__items set-bg" data-setbg="img/hero/overlord.jpg">
					<div class="row">
						<div class="col-lg-6">
							<div class="hero__text">
								<div class="label">Adventure</div>
								<h2>Overlord</h2>
								<p>Cốt truyện anime sẽ đưa khán giả đến năm 2138 trong tương
									lai, khi khoa học công nghệ phát triển vượt bậc và ngành game
									thực tế ảo đang nở rộ hơn bao giờ hết...</p>

							</div>
						</div>
					</div>
				</div>
				<div class="hero__items set-bg" data-setbg="img/hero/dptk.jpg">
					<div class="row">
						<div class="col-lg-6">
							<div class="hero__text">
								<div class="label">Adventure</div>
								<h2>Fights Break Sphere Season 5</h2>
								<p>Đấu Phá Thương Khung kể về một thế giới thuộc về Đấu Khí,
									không hề có ma pháp hoa tiếu diễm lệ, chỉ có đấu khí cương mãnh
									phồn thịnh! ....</p>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->

	<!-- Product Section Begin -->
	<section class="product spad">
		<div class="container">

			<div class="row">
				<div class="col-lg-8">
					<div class="trending__product">
						<div class="row">
							<div class="col-lg-8 col-md-8 col-sm-8">
								<div class="section-title">

									<h4>

										<fmt:message>content.listnewanime</fmt:message>
									</h4>
								</div>
							</div>

						</div>
						<div class="row">
							<c:if test="${empty offset}">
								<c:set var="offset" scope="page" value="1" />
							</c:if>
							<c:forEach var="movie"
								items="${listMovie.getpageMovie(offset,listMovie.listMovie)}">
								<div class="col-lg-4 col-md-6 col-sm-6">
									<div class="product__item">
										<c:url var="urlAvatarMovie"
											value="/anime-main/storage/avatarMovie/${movie.avatar}" />
										<div class="product__item__pic set-bg"
											data-setbg="${urlAvatarMovie}">
											<div class="ep">${movie.currentEpisode}/
												${movie.totalEpisodes}</div>
											<div class="rate">
												${movie.getAvgScore()} <i class='fa fa-star'
													style='color: #f3da35'></i>
											</div>
											<div class="comment">
												<i class="fa fa-comments"></i> ${movie.listComment.size()}
											</div>
											<div class="view">
												<i class="fa fa-eye"></i> ${movie.view}
											</div>
										</div>
										<div class="product__item__text">
											<ul>

												<li><fmt:message>${movie.genre}</fmt:message></li>
											</ul>
											<h5>
												<a href="${anime_details}?idMv=${movie.idMovie}">${movie.nameMovie}</a>
											</h5>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
					<div>
						<c:url var="changePage" value="changePage"></c:url>
						<c:forEach var="i" begin="1"
							end="${listMovie.totalPage(listMovie.listMovie)}">
							<form action="${changePage}?type=index&&number=${i}"
								method="post" style="display: inline-block;">




								<c:if test="${i==offset}">
									<button name="pageNumber" type="submit" value="${i}"
										style="background-color: red;">${i}</button>
								</c:if>

								<c:if test="${i!=offset}">
									<button name="pageNumber" type="submit" value="${i}">${i}</button>
								</c:if>
							</form>

						</c:forEach>
					</div>

				</div>
				<c:import url="/anime-main/topview.jsp" />




			</div>
		</div>
	</section>
	<!-- Product Section End -->

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