<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Internacionalizar - VER DEPOIS -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DLEI - Admin - Papéis</title>

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

	<!-- <jsp:include page="/public/public-nav.jsp" /> -->
	<jsp:include page="/auth/admin/admin-nav.jsp" />


	<div class="container div-container">
		<div class="row">
			<div class="col">
				<h2>Admin - Papéis</h2>
				<form
					action="${pageContext.request.contextPath}/auth/admin?action=editarPapel"
					method="post">
					<input type="hidden" name="id"
						value="<c:out value='${usuario.id}' />" />
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">Atribuir papéis ao
							usuário</label>
						<div class="col-sm-5">
							<input class="form-control" type="text" name="nome"
								value="<c:out value='${usuario.nome}' />" disabled />
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">Status: </label>
						<div class="col-sm-4">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="ativo"
									value="true" ${usuario.ativo=='true'?'checked':''} /> <label
									class="form-check-label">Ativo</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="ativo"
									value="false" ${usuario.ativo=='false'?'checked':''} /> <label
									class="form-check-label">Inativo </label>
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">Papéis</label>
						<div class="col-sm-4">
							<c:forEach var="pp" items="${listaPapeis}">
								<c:set var="temPapel" value="false" />
								<c:forEach var="papel" items="${usuario.papeis}">
									<c:if test="${papel.tipoPapel == pp.tipoPapel}">
										<c:set var="temPapel" value="true" />
									</c:if>
								</c:forEach>

								<c:choose>
									<c:when test="${temPapel == true}">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" name="pps"
												value="${pp.id}" checked /> <label class="form-check-label">
												<c:out value="${pp.tipoPapel}" />
											</label>
										</div>
									</c:when>
									<c:otherwise>
										<div class="form-check">
											<input class="form-check-input" type="checkbox" name="pps"
												value="${pp.id}" /> <label class="form-check-label">
												<c:out value="${pp.tipoPapel}" />
											</label>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</div>

					<div class="button-margin-top">
						<input class="btn btn-primary" type="submit" value="Editar" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<footer>
	<jsp:include page="/public/public-footer.jsp" />
</footer>
</html>