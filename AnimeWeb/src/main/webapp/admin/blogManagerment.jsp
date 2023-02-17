<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
	<c:url var="adm" value="Admin"></c:url>
	<c:import url="header.jsp"></c:import>

	<fmt:setLocale value="vi_VN" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<div class="container">

		<div class="row">
			<div class="col-lg-12">

				<div class="editBlog">
					<div class="title_blog">
						<p>
							<fmt:message>content.post</fmt:message>
						</p>
					</div>
					<form action="${adm}?type=upBlog" method="post"
						enctype='multipart/form-data' id="postBlog">

						<p>
							<fmt:message>content.file</fmt:message>
						</p>
						<input type="text" required="required" class="w3-input w3-border w3-round-large" value=""
							placeholder="text" name="title"></input>
						<div>
							<br>
							<div class="upload_avatar">
								<label class="renderAvatar" for="avt" id="renderAvatar">
									<fmt:message>content.upload</fmt:message>
								</label>
							</div>
						</div>
						<p>
							<fmt:message>content.postdate</fmt:message>
						</p>
						<input required="required" class="w3-input w3-border w3-round-large" type="text"
							name="dayDebut" id="dayDebut" placeholder="yyyy-mm-dd" /><br>
						<p>
							<fmt:message>content.upfile</fmt:message>
						</p>
						<input  type="file" name="files" id="files" /> <input  type="file"
							name="avt" id="avt" accept="png,jpg" onchange="demoImage()"
							hidden /><br>


						<button type="submit">
							<fmt:message>content.up</fmt:message>
						</button>
					</form>
				</div>
			</div>

			<div class="title_p2">
				<p>
					<fmt:message>content.listpost</fmt:message>
				</p>
			</div>
			<div class="col-lg-12"
				style="overflow: auto; height: 300px !important;">
				<c:forEach var="blog" items="${listBlog.list}">
					<div class="blog_edit">
						<form action="${adm}?type=settingBlog&&idBlog=${blog.idBlog}"
							method="post">
							<img alt=""
								src="../anime-main/storage/blogSave/${blog.folder}/${blog.avt}?${now}"
								id="avtUser">
							<p>
								<fmt:message>content.file</fmt:message>
								${blog.title} -
								<fmt:message>content.postdate</fmt:message>
								${blog.datePost}
							</p>
							<button type="submit" name="update" value="update">
								<fmt:message>button.edit </fmt:message>
							</button>
							<button type="submit" name="delete" value="delete">
								<fmt:message>button.delete</fmt:message>
							</button>
						</form>

					</div>

				</c:forEach>
			</div>
		</div>
	</div>

	<br>

	<div>
		<br> <br>
		<c:import url="footer.jsp"></c:import>
	</div>
	<script src="../anime-main/js/jquery-3.3.1.min.js"></script>
	<script src="../anime-main/js/bootstrap.min.js"></script>
	<script src="../anime-main/js/player.js"></script>
	<script src="../anime-main/js/jquery.nice-select.min.js"></script>
	<script src="../anime-main/js/mixitup.min.js"></script>
	<script src="../anime-main/js/jquery.slicknav.js"></script>
	<script src="../anime-main/js/owl.carousel.min.js"></script>
	<script src="../anime-main/js/main.js"></script>
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