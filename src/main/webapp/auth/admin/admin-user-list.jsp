<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuários Cadastrados - Admin</title>

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
	<jsp:include page="/auth/admin/admin-nav.jsp" />

	<div class="container div-container">
		<div class="row">

			<div class="col">
				<h2>Usuários Cadastrados - Admin</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>Cpf</th>
							<th>Nascimento</th>
							<th>E-mail</th>
							<th>Status</th>
							<th>Papel</th>
							<th>Remover</th>
							<th>Editar Papéis</th>							
						</tr>
					</thead>
					<tbody>
						<!-- vem da class AdminControl/userList -->
						<c:forEach var="usuario" items="${usersList}">
							<tr>
								<!-- [out] equivale ao "Sytem.out.print" -->
								<td><c:out value="${usuario.id}" /></td>
								<td><c:out value="${usuario.nome}" /></td>
								<td><c:out value="${usuario.cpf}" /></td>
								<td><fmt:formatDate value='${usuario.dataNascimento}'
										type='date' pattern='dd/MM/yyyy' /></td>
								<td><c:out value="${usuario.email}" /></td>

																
								<td>									
									<c:choose>
									  <c:when test="${usuario.ativo=='true'}">
									    <span> 
									    	Ativo
									    </span>
									  </c:when>									  
									  <c:otherwise>
									    <span> 
									    	Inativo
									    </span>
									  </c:otherwise>
									</c:choose>	
								</td>
																
								
								<td>
									<c:forEach var="papel" items="${usuario.papeis}">
										<span><c:out value="${papel.tipoPapel}"/></span><br/>
									</c:forEach>
								</td>	
								<td> 																			
									<a class="btn btn-outline-danger btn-sm"
									onclick="return confirm('Você deseja apagar?');"
									href="${pageContext.request.contextPath}/auth/admin?action=remove&id=<c:out value="${usuario.id}" />">
									Apagar</a>
								</td>
								
								<td> 																			
									<a class="btn btn-outline-primary btn-sm"
									   href="${pageContext.request.contextPath}/auth/admin?action=iniciarEditarPapel&id=<c:out value="${usuario.id}" />">
									Editar</a>
								</td>																						

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
</body>
<footer>
 <jsp:include page="/public/public-footer.jsp"/>
</footer>
</html>
