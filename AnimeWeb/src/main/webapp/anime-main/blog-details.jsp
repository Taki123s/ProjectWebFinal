<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<c:url var="index" value="/anime-main/index.jsp" />
	<c:url var="login" value="/anime-main/login.jsp" />
	<c:url var="signup" value="/anime-main/signup.jsp" />
	<c:url var="loginServlet" value="login" />
	<c:url var="urlAvatar"
		value="${request.rervletContext.realPath}/anime-main/storage/avatarUser/${sessionScope.user.avatar}" />
	<c:url var="categories" value="/anime-main/categories.jsp" />
	<c:url var="blog-details" value="/anime-main/blog-details.jsp" />
	<c:url var="blog" value="/anime-main/blog.jsp" />
	<c:url var="anime-watching" value="/anime-main/anime-watching.jsp" />
	<c:url var="anime-details" value="/anime-main/anime-deails.jsp" />
	<c:url var="commenttoBlog" value="commentBlog" />
	
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<c:import url="/anime-main/header.jsp" />
	<!-- Header End -->

	<!-- Blog Details Section Begin -->
	<section class="blog-details spad">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-lg-8">
					<div class="blog__details__title">
						<h6>
							<span>- ${currentBlog.getDatePost()} -</span>
						</h6>
						<h2>${currentBlog.getTitle()}</h2>
						<div class="blog__details__social">
							<a href="#" class="facebook"><i class="fa fa-facebook-square"></i>
								Facebook</a> <a href="#" class="pinterest"><i
								class="fa fa-pinterest"></i> Pinterest</a> <a href="#"
								class="linkedin"><i class="fa fa-linkedin-square"></i>
								Linkedin</a> <a href="#" class="twitter"><i
								class="fa fa-twitter-square"></i> Twitter</a>
						</div>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="blog__details__pic">
						<img
							src="storage/blogSave/${currentBlog.getFolder()}/${currentBlog.getAvt()}"
							alt="">
					</div>
				</div>
				<div class="col-lg-8">
					<div class="blog__details__content">





						<div class="blog__details__text custom">${contentBlog}</div>








						<div class="blog__details__tags">
							<a href="#">Healthfood</a> <a href="#">Sport</a> <a href="#">Game</a>
						</div>
						<div class="blog__details__btns">
							<div class="row">
								<div class="col-lg-6">
									<div class="blog__details__btns__item">
										<h5>
											<a href="#"><span class="arrow_left"></span> Building a
												Better LiA...</a>
										</h5>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="blog__details__btns__item next__btn">
										<h5>
											<a href="#">Mugen no Juunin: Immortal – 21 <span
												class="arrow_right"></span></a>
										</h5>
									</div>
								</div>
							</div>
						</div>
						<div class="blog__details__comment">
							<h4>${currentBlog.getTotalComment()}Comments</h4>

							<c:forEach var="bigComment" items="${currentBlog.getListCmt()}">
								<div class="commentContainer">
									<div class="blog__details__comment__item">
										<div class="blog__details__comment__item__pic">
											<img
												src="/AnimeWeb/anime-main/storage/avatarUser/${bigComment.getAvtUserName(listUser)}?${now}"
												style="width: 50px; height: 50px; border-radius: 50%;"
												alt="">
										</div>
										<div class="blog__details__comment__item__text">
											<span>${bigComment.getDayCmt()}</span>
											<h5>${bigComment.getUserName()}</h5>
											<p>${bigComment.getMess()}</p>
										
											<button
												value="userCmt=${bigComment.getUserName()}&&idCmt=${bigComment.getIdCmt()}"
												class="setValue">Reply</button>
										</div>
									</div>
									<c:forEach var="cmtReply" items="${bigComment.getList()}">

										<div
											class="blog__details__comment__item blog__details__comment__item--reply">
											<div class="blog__details__comment__item__pic">
												<img
													src="/AnimeWeb/anime-main/storage/avatarUser/${cmtReply.getAvtUserName(listUser)}?${now}"
													style="width: 50px; height: 50px; border-radius: 50%;"
													alt="">
											</div>
											<div class="blog__details__comment__item__text">
												<span>${cmtReply.getDayCmt()}</span>
												<h5>${cmtReply.getUserNameReply()}-->
													${cmtReply.getUserNameCmt()}</h5>
												<p>${cmtReply.getMessage()}</p>
												
												<button
													value="userCmt=${cmtReply.getUserNameReply()}&&idCmt=${bigComment.getIdCmt()}"
													class="setValue">Reply</button>
											</div>

										</div>


									</c:forEach>
									<form
										action="${commenttoBlog}?type=repcmt&&idblog=${currentBlog.idBlog}"
										style="display: none;" method="post" class="formComment">

										<textarea placeholder="Your Comment" name="message"
											style="display: block;"></textarea>
										<button type="submit" value="" name="" class="site-btn">
											<i class="fa fa-location-arrow"></i> Submit
										</button>
									</form>
								</div>

							</c:forEach>








						</div>








						<div class="blog__details__form">
							<h4>Leave A Commnet</h4>
							<form
								action="${commenttoBlog}?type=bigcmt&&idblog=${currentBlog.idBlog}"
								method="post">
								<div class="row">
									<div class="col-lg-12">
										<textarea placeholder="Message" name="message"></textarea>
										<button type="submit" class="site-btn">Send Message</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Details Section End -->

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
	<script>
			
		
			
			$(".setValue").click(function(){
				 var closest = $(this).parent().closest('.commentContainer'); 
				 closest.find(".formComment").css("display","block");
				 var valueForm =closest.find(".formComment").attr('action');
				 closest.find(".formComment").attr("action",valueForm+"&&"+$(this).val())
			
			})
		
		
		</script>
</body>

</html>