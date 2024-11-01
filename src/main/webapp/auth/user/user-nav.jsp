<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<img class="navbar-brand"
		src="${pageContext.request.contextPath}/image/logo_50X50.png"
		class="img-fluid" alt="Imagem responsiva">
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
		data-bs-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">

		<ul class="nav navbar-nav">

			<li class="nav-item dropdown"><a href="#"
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Gereciamento
					User</a>
				<div class="dropdown-menu">
					<a href="#" class="dropdown-item dropdown-menu-nav">Home
						Usuário</a>
				</div></li>
		</ul>

		<jsp:include page="/auth/auth-generica-dropdown-conta.jsp" />
	</div>
</nav>
