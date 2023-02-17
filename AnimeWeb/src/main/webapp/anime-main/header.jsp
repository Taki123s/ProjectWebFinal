<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<c:url var="index" value="/anime-main/index.jsp" />
<c:url var="login" value="/anime-main/login.jsp" />
<c:url var="signup" value="/anime-main/signup.jsp" />

<c:url var="categories" value="/anime-main/categories.jsp" />
<c:url var="blogdetails" value="/anime-main/blog-details.jsp" />
<c:url var="blog" value="/anime-main/blog.jsp" />
<c:url var="animewatching" value="/anime-main/anime-watching.jsp" />
<c:url var="animedetails" value="/anime-main/anime-details.jsp" />
<c:url var="anime_details" value="anime_details" />
<c:url var="loginServlet" value="login" />
<jsp:useBean id="now" class="java.util.Date" scope="request" />
<c:url var="follow" value="follow" />
<c:url var="urlAvatar"
	value="${request.servletContext.realPath}/anime-main/storage/avatarUser/${user.avatar}?${now}" />


<fmt:setBundle basename="anime.web.resources.app" />
<c:url var="profileServlet" value="/anime-main/profile.jsp" />
<c:url var="GenreSl" value="genre" />
<c:url var="goBlog" value="/anime-main/blog.jsp" />
<header class="header">

	<fmt:setBundle basename="anime.web.resources.app" />
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<div class="header__logo">
					<a href="${index}"> <img src="img/logo.png" alt="">
					</a>
				</div>
			</div>
			<div class="col-lg-8">
				<fmt:setLocale value="${sessionScope.LANG}" />
				<fmt:setBundle basename="anime.web.resources.app" />
				<div class="header__nav">
					<nav class="header__menu mobile-menu">
						<ul>
							<li class="active"><a href="${index}"><fmt:message>menu.hompage</fmt:message></a></li>
							<li><a href="#"><fmt:message>menu.categories</fmt:message><span
									class="arrow_carrot-down"></span></a>
								<div class="dropdown">
									<ul>
										<c:forEach var="genre" items="${listMovie.listGenre}">
											<li><a href="${GenreSl}?genre=${genre}"><fmt:message>${genre}</fmt:message>
											</a></li>
										</c:forEach>
									</ul>
								</div></li>

							<li><a href="${goBlog}"><fmt:message>menu.ourblog</fmt:message></a></li>
							<li><a href="https://www.facebook.com/profile.php?id=100012214729084"><fmt:message>menu.contracts</fmt:message></a></li>
							<li><a href="#"><fmt:message>content.langue</fmt:message></a>
								<div class="dropdown">
									<c:set var="query" value="${pageContext.request.queryString}"/>
							
									<ul>
									<c:if test="${param.lang== null}">
										<li style="color: black;"><a href="?${query}&&lang=vi_VN"><fmt:message>content.vn</fmt:message></a></li>
										<li style="color: black;"><a href="?${query}&&lang=en_US"><fmt:message>content.en</fmt:message></a></li>
									</c:if>
									<c:if test="${param.lang!= null}">
										
										<li style="color: black;"><a href="?${fn:substring(query, 0, query.length()-12)}&&lang=vi_VN"><fmt:message>content.vn</fmt:message></a></li>
										<li style="color: black;"><a href="?${fn:substring(query, 0, query.length()-12)}&&lang=en_US"><fmt:message>content.en</fmt:message></a></li>
									</c:if>
									</ul>
								</div></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="col-lg-2">
				<div class="header__right">
					<form class="searchTag" action="search" method="post">
						<input id="search-input" placeholder="Search here....."
							oninput="searchByName(this)" name="searchBox" type="text">
						<p class="search-switch">
							<span class="icon_search"></span>
						</p>
					</form>
					<table id="renderSearch"></table>
					<c:choose>
						<c:when test="${not empty sessionScope.user}">
							<c:if test="${sessionScope.user.isAdmin==0}">
								<div>
									<img alt="" src="${urlAvatar}" id="avtUser" onclick="vision()">
									<ul class="profile" id="profile">

										<li><a href="${profileServlet}"><button class="">
													<fmt:message>header.account</fmt:message>
												</button></a></li>
										<li><a href="${follow}?type=listFollow"><fmt:message>header.follow</fmt:message></a>
										<li><a href="${loginServlet}"><button
													class="fas fa-sign-out-alt"></button></a></li>

									</ul>
								</div>
							</c:if>
							<c:if test="${sessionScope.user.isAdmin==1}">
								<div>
									<fmt:setLocale value="${sessionScope.LANG}" />
									<fmt:setBundle basename="anime.web.resources.app" />
									<img alt="" src="${urlAvatar}" id="avtUser" onclick="vision()">
									<ul class="profile" id="profile">

										<li><a href="${profileServlet}"><button class="">
													<fmt:message>header.account</fmt:message>
												</button></a></li>
										<li><a href="${follow}?type=listFollow"><fmt:message>header.follow</fmt:message></a>
											<c:url var="adm" value="../admin/featureAdmin" />
										<li><a href="${adm}"><button class="fa fa-cog"></button></a></li>
										<li><a href="${loginServlet}"><button
													class="fas fa-sign-out-alt"></button></a></li>

									</ul>
								</div>
							</c:if>

						</c:when>
						<c:when test="${empty sessionScope.user}">
							<a href="${login}"><fmt:message>menu.login</fmt:message> </a>
							<font color="#e53637"> / </font>
							<a href="${signup}"><fmt:message>menu.signup</fmt:message></a>

						</c:when>
					</c:choose>
					<input type="text" id="idSession" value="${pageContext.session.id}"
						style="display: none;">
				</div>
			</div>
		</div>
		<div id="mobile-menu-wrap"></div>
	</div>
</header>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/player.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>
<script>
	function searchByName(input) {
		var value = input.value;
		var ssid = document.getElementById("idSession").value;
		$.ajax({
			url : "search",
			type : "get",
			data : {
				searchBox : value,
				id : ssid
			},
			success : function(data) {
				var target = document.getElementById("renderSearch");
				target.innerHTML = data;
			},
			error : function(data) {

			}
		});

	}
</script>
<script>
	function vision() {
		var isVisible = document.getElementById("profile").style.display;
		if (isVisible == "") {
			document.getElementById("profile").style.display = 'block';
		} else if (isVisible == "block") {
			document.getElementById("profile").style.display = 'none';
		} else {
			document.getElementById("profile").style.display = 'block';
		}
	}
</script>
<script>
	$(function() {
		$('#nav ul li a').each(function() {
			var path = window.location.href;
			var current = path.substring(path.lastIndexOf('/') + 1);
			var url = $(this).attr('href');
			if (url == current) {
				$(this).addClass('active');
			}
			;
		});
	});
</script>
