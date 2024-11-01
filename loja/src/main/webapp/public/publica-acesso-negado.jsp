<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DLEI - USER - Sem Permissão</title>

<link
	href="${pageContext.request.contextPath}/resources/css-js-jq/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css-js-jq/myCss/style.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/css-js-jq/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/css-js-jq/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/auth/auth-generica-nav.jsp" />

	<div class="container div-container">
		<div class="row">
			<div class="col">
				<h2>Você não tem permisão ao recurso solicitado</h2>
				<h4>Fale com o Administrador</h4>
			</div>
		</div>
	</div>

</body>
<footer>
 <jsp:include page="/public/public-footer.jsp"/>
</footer>
</html>