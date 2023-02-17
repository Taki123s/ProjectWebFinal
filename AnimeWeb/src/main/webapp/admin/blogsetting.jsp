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
</head>
<body>
<c:if test="${empty sessionScope.user}">
		<c:redirect url ="/anime-main/login.jsp"/>
		</c:if>
		<c:if test="${(sessionScope.user.isAdmin!=1)}">
			<% request.getSession().invalidate(); %>
		<c:redirect url ="/anime-main/login.jsp"/>
		</c:if>
	<c:url var="admin" value="Admin"></c:url>
	<c:import url="header.jsp"></c:import>
	<fmt:setLocale value="vi_VN" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<div class="container">

		<div class="row">
			<div class="inUser">
				<div class="userman">
					<p>
						<fmt:message>admin.editblog</fmt:message>
					</p>
				</div>
				<div class="col-lg-12">
					<form
						action="${adm}?type=settingBlog&&idBlog=${blogBeUpdate.idBlog}"
						method="post" enctype='multipart/form-data' id="postBlog">
						<div class="postUpload">
							<fmt:message>content.file</fmt:message>
							<input required="required" value="${blogBeUpdate.title}" name="title" />
							<div>
								<fmt:message>content.pic</fmt:message>
								<label class="renderAvatar" for="avt" id="renderAvatar">
									<img
									src="../anime-main/storage/blogSave/${blogBeUpdate.folder}/${blogBeUpdate.avt}?${now}"
									id="avtrd">
								</label>
							</div>
							<br>
							<fmt:message>admin.daterelease</fmt:message>
							<input required="required" type="text" name="dayDebut" id="dayDebut"
								value="${blogBeUpdate.dayDebut}" /> <br>
							<fmt:message>content.upfile</fmt:message>
							<input  type="file" name="files" id="files" /><input type="file"
								name="avt" id="avt" accept="png,jpg" onchange="demoImage()"
								hidden />
							<button type="submit" name="confirm" value="confirm">
								<fmt:message>button.edit</fmt:message>
							</button>
						</div>

					</form>
				</div>
			</div>
		</div>
		<br>
	</div>
	<br>

	<c:import url="footer.jsp"></c:import>

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