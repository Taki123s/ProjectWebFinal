<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting Movie</title>
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
</head>
<body>
<c:if test="${empty sessionScope.user}">
		<c:redirect url ="/anime-main/login.jsp"/>
		</c:if>
		<c:if test="${(sessionScope.user.isAdmin!=1)}">
			<% request.getSession().invalidate(); %>
		<c:redirect url ="/anime-main/login.jsp"/>
		</c:if>
	<fmt:setLocale value="vi_VN" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<c:url var="admin" value="Admin"></c:url>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<div class="row">
			<div class="title_p1">
				<p>
					<fmt:message>admin.edit</fmt:message>
				</p>
			</div>
			<div class="col-lg-12">
				<div class="thongke">
					<form
						action="${adm}?type=settingMovie&&idMovie=${movieBeUpdate.idMovie}"
						method="post" enctype='multipart/form-data' id="postBlog">
						<div class="postUpload">
							<br>
							<fmt:message>content.namemoive</fmt:message>
							<br> <input required="required" value="${movieBeUpdate.nameMovie}" name="nameMv" />
							<div>
								<br>
								<fmt:message>content.pic</fmt:message>
								<label class="renderAvatar" for="avt" id="renderAvatar">
									<img
									src="../anime-main/storage/avatarMovie/${movieBeUpdate.avatar}?${now}"
									id="avtrd">
								</label> <br>
								<fmt:message>menu.categories</fmt:message>
								<br> <input required="required" type="text" value="${movieBeUpdate.genre}"
									name="genre" /> <br>
								<fmt:message>content.chapter</fmt:message>
								<br> <input required="required" type="number"
									value="${movieBeUpdate.totalEpisodes}" name="totalEpi" /><br>
								<fmt:message>content.description</fmt:message>
								<br>
								<textarea id="w3review" name="des" rows="5" cols="50"> ${movieBeUpdate.description}</textarea>
							</div>
							<input  type="file" name="avt" id="avt" accept="png,jpg"
								onchange="demoImage()" hidden />
							<button class="bt" type="submit" name="confirm" value="confirm">
								<fmt:message>button.edit</fmt:message>
							</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="../anime-main/js/jquery-3.3.1.min.js"></script>
	<script src="../anime-main/js/bootstrap.min.js"></script>
	<script src="../anime-main/js/player.js"></script>
	<script src="../anime-main/js/jquery.nice-select.min.js"></script>
	<script src="../anime-main/js/mixitup.min.js"></script>
	<script src="../anime-main/js/jquery.slicknav.js"></script>
	<script src="../anime-main/js/owl.carousel.min.js"></script>
	<script type="text/javascript">
		function demoImage() {
			var fileSelected = document.getElementById("avt").files;
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