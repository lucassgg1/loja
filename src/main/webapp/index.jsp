<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DLEI - Bem Vindo</title>

<link
	href="${pageContext.request.contextPath}/resources/css-js-jq/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css-js-jq/myCss/style.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css-js-jq/myCss/form-login.css"
	rel="stylesheet" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/css-js-jq/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/css-js-jq/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
</head>
<body class="text-center">
	<main class="form-signin">
		<form action="${pageContext.request.contextPath}/login?action=login"
			method="post">
			<div>
				<img class="mb-4"
					src="${pageContext.request.contextPath}/image/logo_100X100.png"
					class="img-fluid" alt="Imagem responsiva">

				<h3 class="h2-form-login">Bem Vindo a DLEI Store</h3>

				<c:if test="${mensagem != null}">
					<div class="alert alert-danger alert-dismissible fade show">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<span><c:out value="${mensagem}" /></span>
					</div>
				</c:if>


				<div>
					<label class="label-login" for="floatingInput">Usuário</label> <input
						class="form-control" type="text" name="login">
				</div>
				<div class="margin-top-login ">
					<label class="label-login" for="floatingPassword">Senha</label> <input
						class="form-control" type="text" name="senha">
				</div>
				<div class="button-margin-top-login">
					<button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
				</div>
			</div>
		</form>
	</main>
</body>
</html>