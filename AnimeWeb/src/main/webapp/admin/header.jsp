<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="index" value="/anime-main/index.jsp" />

<c:url var="loginServlet" value="/anime-main/login" />
<jsp:useBean id="now" class="java.util.Date" scope="request" />
		
<c:url var="urlAvatar"
	value="${request.servletContext.realPath}/anime-main/storage/avatarUser/${sessionScope.user.avatar}?${now}" />
<header class="header">
	
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<div class="header__logo">
					<a href="${index}"> <img src="../anime-main/img/logo.png"
						alt="">
					</a>
				</div>
			</div>
			<div class="col-lg-8">
				<div class="header__nav">
					<nav class="header__menu mobile-menu">
						<ul>
							<c:url var="movieManager" value="/admin/movieManagerment.jsp" />
							<c:url var="userManager" value="/admin/userManagerment.jsp" />
							<c:url var="blogManager" value="/admin/blogManagerment.jsp" />
							<li class="managerButton"><a href="${movieManager}">Quản
									lý phim</a></li>
							<li class="managerButton"><a href="${userManager}">Quản
									lý người dùng</a></li>
							<li class="managerButton"><a href="${blogManager}">Quản
									lý Blog</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="col-lg-2">
				<div class="header__right">

					<div>
						<img alt="" src="${urlAvatar}" id="avtUser">
						<ul class="profile">

							<li><a href="${profileServlet}"><button class="">Quản
										lý tài khoản</button></a></li>
							<li><a href="${loginServlet}"><button
										class="fas fa-sign-out-alt"></button></a></li>

						</ul>
					</div>
					<input type="text" id="idSession" value="${pageContext.session.id}"
						style="display: none;">
				</div>
			</div>
		</div>
		<div id="mobile-menu-wrap"></div>
	</div>
</header>