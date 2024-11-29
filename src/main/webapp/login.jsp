<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->

<link href="${pageContext.request.contextPath}/assets/css/bootstrap/css/bootstrap.min_5.3.1.css"
	rel="stylesheet" />

<link href="${pageContext.request.contextPath}/assets/css/myCss/style.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/myCss/form-login.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js"></script>

<title>DLEI Store - Login</title>

</head>
<body>

	<!-- "${msg} captura a mensagem do [setAttribute]"
	Uso do JSP - Dependência: javax.servlet.jsp -->

	<!-- Passando parâmetro para outra página: "receber-nome.jsp" -->
	<!-- Usado"${pageContext.request.contextPath}" para resolver o erro: HTTP Status 404 – Não Encontrado -->
	<main class="form-signin">
		<form autocomplete="off" action="${pageContext.request.contextPath}/ServletLogin"
			method="post" class="needs-validation" novalidate>

			<div>
				<div class="text-center">
					<img class="mb-4"
						src="${pageContext.request.contextPath}/image/logo_100X100.png"
						class="img-fluid" alt="Imagem responsiva">

					<h2 class="h2-form-login">DLEI Store - Login</h2>
				</div>

				<input type="hidden" value="<%=request.getParameter("url")%>"
					name="url">
				<div>
					<label class="label-login">Usu�rio</label> <input
						class="form-control" type="text" name="login"
						placeholder="Digite o Nome do usu�rio" required="required" autocomplete="off">
				</div>
				<div class="margin-top-login ">
					<label class="label-login">Senha</label> <input
						class="form-control" type="password" name="senha"
						placeholder="Digite a Senha" required="required" autocomplete="off">
				</div>
				<div class="button-margin-top-login">
					<button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
				</div>
				
				<div class="button-margin-top-login">
				<a href="${pageContext.request.contextPath}" class="w-100 btn btn-lg btn-secondary" role="button" >Home</a>
				</div>

				<!--  <div>${msg}</div> -->
				<c:if test="${msg != null}">
					<div
						class="alert alert-danger alert-dismissible fade show margin-top-login">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<span><c:out value="${msg}" /></span>
					</div>
				</c:if>
			</div>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/assets/js/js-5.1.3/bootstrap.min.js"></script>
		</form>
	</main>
	<script type="text/javascript">
		
		/* Destiva o autocomplete*/
		document.addEventListener("DOMContentLoaded", function(e) {
		   let input = document.querySelector('input[autocomplete="off"]');

		   input.setAttribute('disabled', 'disabled');

		   setTimeout(function(){
		      input.removeAttribute('disabled');
		   }, 1000)
		});
	
	</script>
</body>
</html>