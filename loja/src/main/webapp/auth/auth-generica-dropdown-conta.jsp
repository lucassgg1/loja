<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul class="nav navbar-nav ms-auto">
		<li class="nav-item dropdown">
			<a href="${pageContext.request.contextPath}"
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown"> 
				<span><c:out value="${sessionScope.usuarioLogado.usuario.nome}" /></span>
			</a>
			<div class="dropdown-menu dropdown-menu-end">				
				<div class="dropdown-divider"></div>
					<a href="${pageContext.request.contextPath}/login?action=logout" class="dropdown-item dropdown-menu-nav">Sair</a>
			</div>
		</li>
</ul>
