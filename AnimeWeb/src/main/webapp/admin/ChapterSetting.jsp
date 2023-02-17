<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
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
<link rel="stylesheet" type="text/css"
	href="../anime-main/css/admin.css" />
<script src="https://kit.fontawesome.com/9847adceef.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
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
			<div class="col-lg-6">
				<p>${movieBeUpdate.nameMovie}</p>
				<img src="../anime-main/storage/avatarMovie/${movieBeUpdate.avatar}"
					id="avtrd">
			</div>
			<div class="col-lg-6">
				<div>
					<p>
						<fmt:message>admin.listchap</fmt:message>
					</p>
				</div>
				<div
					style="overflow: auto; height: 500px !important; width: 500px !important">
					<c:forEach var="chapter" items="${movieBeUpdate.listchapter}">

						<form
							action="${ChapterAdmin}?type=setting&&idMovie=${chapter.idMovie}&&index=${chapter.index}"
							method="post">
							<div class="listChap">
								<fmt:message>button.episodes</fmt:message>${chapter.index}
								<button type="submit" name="update" value="update">
									<fmt:message>button.edit</fmt:message>
								</button>
								<button type="submit" name="delete" value="delete">
									<fmt:message>button.delete</fmt:message>
								</button>

							</div>

						</form>


					</c:forEach>

				</div>
			</div>
			<div class="editChapter">
				<form
					action="${ChapterAdmin}?type=add&&idMovie=${movieBeUpdate.idMovie}&&index=${movieBeUpdate.listchapter.size()+1}"
					method="post" enctype='multipart/form-data'>
					<fmt:message>admin.addnewep</fmt:message>
					${movieBeUpdate.listchapter.size()+1} <br>
					<fmt:message>admin.file</fmt:message>
					<input  type="file" accept="video/mp4" name="video"><br>
					<fmt:message>admin.opening</fmt:message>
					<input required="required" type="number" name="opening"><br>
					<button type="submit" name="" value="">
						<fmt:message>button.add</fmt:message>
					</button>

				</form>
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