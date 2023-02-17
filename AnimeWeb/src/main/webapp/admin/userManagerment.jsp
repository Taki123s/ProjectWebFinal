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
	<c:url var="adm" value="Admin"></c:url>
	<c:import url="header.jsp"></c:import>

	<fmt:setLocale value="vi_VN" />
	<fmt:setBundle basename="anime.web.resources.app" />
	<div class="container">
		<div class="row">

			<div class="inUser">
				<div class="userman">
					<p>
						<fmt:message>content.accountlist</fmt:message>
					</p>
				</div>
				<br>

				<div class="col-lg-12"
					style="overflow: auto; height: 600px !important; width: 100% !important">
					<c:forEach var="user" items="${listUser.list}">
						<form
							action="${adm}?type=settingAccount&&userName=${user.userName}"
							method="post">
							<img alt=""
								src="../anime-main/storage/avatarUser/${user.avatar}?${now}"
								id="avtUser">
							<div class="userF">
								<p>User Name : ${user.userName}</p>
								<div class="input-group input-group-sm mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm">Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<input required="required" type="email" class="form-control" aria-label="Small"
											required value="${user.email}" name="email">
									</div>
									<br>
									<div class="input-group input-group-sm mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm">Password</span>
											<input  required="required"type="text" class="form-control" aria-label="Small"
												required value="" name="password">
										</div>
									</div>
								</div>
								<div class="userIn">
									<button type="submit" name="update" value="update">
										<fmt:message>button.edit</fmt:message>
									</button>
									<button type="submit" name="delete" value="delete">
										<fmt:message>button.delete</fmt:message>
									</button>
								</div>
							</div>
						</form>


					</c:forEach>
				</div>
			</div>

		</div>
	</div>
	<br>
	<div>
		<br>
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