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
	<c:url var="profileServlet" value="profile" />
	<c:url var="categories" value="/anime-main/categories.jsp" />
	<c:url var="blog-details" value="/anime-main/blog-details.jsp" />
	<c:url var="blog" value="/anime-main/blog.jsp" />
	<c:url var="anime-watching" value="/anime-main/anime-watching.jsp" />
	<c:url var="anime-details" value="/anime-main/anime-deails.jsp" />
	<c:url var="profile" value="/anime-main/profile.jsp" />
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
	<section class="product spad">
		<div class="container">
			<form action="${profileServlet}" method="post"
				enctype='multipart/form-data' id="editProfile">
				<div class="changeAvatar">
					<fmt:message>content.pic</fmt:message>
					<label id="renderAvatar" for="files"> <img alt=""
						src="${urlAvatar}" id="avtUser">
						<div class="upload">
							<fmt:message>content.upload</fmt:message>
						</div>
					</label> <input type="file" name="files" id="files" hidden
						onchange="demoImage()" />
				</div>
				<div>User name : ${user.userName}</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">Password :</span>
					</div>
					<input type="text" value="" name="pass">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">Email &emsp;&nbsp;&nbsp; :</span>
					</div>
					<input type="text" value="${user.email}" name="email">
				</div>
				<div>
					<button class="btn btn-danger" type="submit">
						<fmt:message>button.edit</fmt:message>
					</button>
				</div>
			</form>
		</div>
	</section>
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
		function demoImage() {

			var fileSelected = document.getElementById("files").files;
			if (fileSelected.length > 0) {
				var fileToLoad = fileSelected[0];
				var fileReader = new FileReader();
				fileReader.onload = function(fileLoaderEvent) {
					var srcData = fileLoaderEvent.target.result;
					var newImage = document.createElement('img');
					newImage.src = srcData;
					document.getElementById("renderAvatar").innerHTML = newImage.outerHTML;
				}
				fileReader.readAsDataURL(fileToLoad);
			}
		}
	</script>

</body>

</html>