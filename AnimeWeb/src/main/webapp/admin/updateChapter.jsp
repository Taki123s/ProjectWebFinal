<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../anime-main/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="../anime-main/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../anime-main/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet" href="../anime-main/css/plyr.css" type="text/css">
<link rel="stylesheet" href="../anime-main/css/nice-select.css"
	type="text/css">
<link rel="stylesheet" href="../anime-main/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../anime-main/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="../anime-main/css/ds/style.css" />
<script src="https://kit.fontawesome.com/9847adceef.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty sessionScope.user}">
		<c:redirect url ="/anime-main/login.jsp"/>
		</c:if>
		<c:if test="${(sessionScope.user.isAdmin!=1)}">
			<% request.getSession().invalidate(); %>
		<c:redirect url ="/anime-main/login.jsp"/>
		</c:if>
	<c:url var="ChapterAdmin" value="ChapterAdmin"></c:url>
	<c:import url="header.jsp"></c:import>
	<fmt:setLocale value="vi_VN" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<div class="container">
		<div class="row">
			<div class="editChapter">
				<div class="col-lg-12">
					<form
						action="${ChapterAdmin}?type=setting&&idMovie=${movieBeUpdate.idMovie}&&index=${index}"
						method="post" enctype='multipart/form-data'>
						<p>
							<fmt:message>button.episodes</fmt:message>${index}</p>
						<fmt:message>admin.chagevideo</fmt:message>
						<input  type="file" accept="video/mp4" name="video"><br>
						<fmt:message>admin.opening</fmt:message>
						<input required="required" type="number" name="opening"><br>
						<button type="submit" name="confirm" value="confirm">
							<fmt:message>admin.edit</fmt:message>
						</button>
					</form>


				</div>
			</div>
		</div>
	</div>


	<c:import url="footer.jsp"></c:import>


	<script src="../anime-main/js/jquery-3.3.1.min.js"></script>
	<script src="../anime-main/js/bootstrap.min.js"></script>
	<script src="../anime-main/js/player.js"></script>
	<script src="../anime-main/js/jquery.nice-select.min.js"></script>
	<script src="../anime-main/js/mixitup.min.js"></script>
	<script src="../anime-main/js/jquery.slicknav.js"></script>
	<script src="../anime-main/js/owl.carousel.min.js"></script>
	<script src="../anime-main/js/main.js"></script>
</body>
</html>