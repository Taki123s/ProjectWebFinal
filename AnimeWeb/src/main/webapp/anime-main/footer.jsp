<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<footer class="footer">
	<fmt:setLocale value="${sessionScope.LANG}" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<div class="page-up">
		<a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="footer__logo">
					<a href="./index.jsp"><img src="img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="footer__nav">
					<ul>
						<li class="active"><a href="${index}"><fmt:message>menu.hompage</fmt:message></a></li>
						<li><a href="./categories.jsp"><fmt:message>menu.categories</fmt:message></a></li>
						<li><a href="./blog.jsp"><fmt:message>menu.ourblog</fmt:message></a></li>
						<li><a href="https://www.facebook.com/profile.php?id=100012214729084"><fmt:message>menu.contracts</fmt:message></a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-3">
				<p>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;
					<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | 20130012 & 20130305
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</p>

			</div>
		</div>
	</div>
</footer>