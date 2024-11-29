<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set scope="session" var="perfil" value='<%= request.getSession().getAttribute("perfil") %>'></c:set>

<nav class="pcoded-navbar">
	<div class="sidebar_toggle">
		<a href="#!"><i class="icon-close icons"></i></a>
	</div>
	<div class="pcoded-inner-navbar main-menu">
		<div class="">
			<div class="main-menu-header">
				<!-- Pega imagem do usuário logado na ServletLogin -->
				<c:if test="${imagemUser != '' && imagemUser != null }">
				<img class="img-80 img-radius" src="${imagemUser}" alt="User-Profile-Image">
				</c:if>
				<!-- Se o usuário logado não tiver imagem, coloca uma imagem default -->
				<c:if test="${imagemUser == '' || imagemUser == null }">
				<img class="img-80 img-radius" src="${pageContext.request.contextPath}/assets/images/avatar-blank.jpg" alt="User-Profile-Image">
				</c:if>
				
				<div class="user-details">
					<!-- Busca nome do usuário logado -->
					<span id="more-details"><%=request.getSession().getAttribute("usuario")%><i class="fa fa-caret-down"></i></span>
				</div>
			</div>

			<div class="main-menu-content">
				<ul>
					<li class="more-details">
					   <!-- <a href="user-profile.html"><i class="ti-user"></i>Ver perfil</a> -->
					   <!--	<a href="#!"><i class="ti-settings"></i>Configurações</a> -->
							<a href="${pageContext.request.contextPath}/ServletLogin?acao=logout"><i
							class="ti-layout-sidebar-left"></i>Sair</a></li>
				</ul>
			</div>
		</div>
		<ul class="pcoded-item pcoded-left-item">
			<li class="active"><a href="${pageContext.request.contextPath}/principal/principal.jsp"
				class="waves-effect waves-dark">
				<span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
				<span class="pcoded-mtext pcoded-mtext-nav" data-i18n="nav.dash.main">Home</span>
				<span class="pcoded-mcaret"></span>
				</a>
			</li>			
		
		
		<c:if test="${perfil == 'ADMIN'}">
			<div class="pcoded-navigation-label" data-i18n="nav.category.forms"><i class="ti-layout-grid2-alt"></i> Cadastro</div>
				<ul class="pcoded-item pcoded-left-item">
					<!-- Só será visível para o Administrador -->
						
					<li>
						<a href="${pageContext.request.contextPath}/ServletUsuarioController?acao=listarUser"
						class="waves-effect waves-dark">
							<span class="pcoded-micon"><i class="ti-layers"></i></span>
							<span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Usuário</span>
							<span class="pcoded-mcaret"></span></a>
					</li>					
					
					<li>
						<a href="${pageContext.request.contextPath}/ServletProdutoController?acao=listarProd"
						class="waves-effect waves-dark">
							<span class="pcoded-micon"><i class="ti-layers"></i></span>
							<span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Produto</span>
							<span class="pcoded-mcaret"></span></a>
					</li>
					
				</ul>
		</c:if>
		
		<c:if test="${perfil == 'ADMIN'}">
		<div class="pcoded-navigation-label" data-i18n="nav.category.forms"><i class="ti-layout-grid2-alt"></i> Consulta</div>
			<ul class="pcoded-item pcoded-left-item">
				<li>
					<a href="${pageContext.request.contextPath}/ServletUsuarioController?acao=queryUser" class="waves-effect waves-dark">
						<span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
						<span class="pcoded-mtext" data-i18n="nav.form-components.main">Usuário</span>
						<span class="pcoded-mcaret"></span>
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/ServletProdutoController?acao=queryProd" class="waves-effect waves-dark">
						<span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
						<span class="pcoded-mtext" data-i18n="nav.form-components.main">Produto</span>
						<span class="pcoded-mcaret"></span>
					</a>
				</li>				
			</ul>
		</c:if>
		
		<c:if test="${perfil == 'ADMIN'}">
		<div class="pcoded-navigation-label" data-i18n="nav.category.forms"><i class="ti-layout-grid2-alt"></i> Relatório</div>
			<ul class="pcoded-item pcoded-left-item">
				<li>
					<a href="${pageContext.request.contextPath}/ServletUsuarioController?acao=imprimirRelatorioUser" class="waves-effect waves-dark">
						<span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
						<span class="pcoded-mtext" data-i18n="nav.form-components.main">Usuário</span>
						<span class="pcoded-mcaret"></span>
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/ServletProdutoController?acao=imprimirRelatorioProd" class="waves-effect waves-dark">
						<span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
						<span class="pcoded-mtext" data-i18n="nav.form-components.main">Produto</span>
						<span class="pcoded-mcaret"></span>
					</a>
				</li>				
			</ul>
		</c:if>		
		
		
		<c:if test="${perfil == 'ADMIN'}">	
		<div class="pcoded-navigation-label" data-i18n="nav.category.forms"><i class="ti-layout-grid2-alt"></i> Orçamentos</div>
			<ul class="pcoded-item pcoded-left-item">
				<li>
					<a href="${pageContext.request.contextPath}/ServletOrcamentoController?acao=imprimirRelatorioProd" class="waves-effect waves-dark">
						<span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
						<span class="pcoded-mtext" data-i18n="nav.form-components.main">Orçamentos Solicitados</span>
						<span class="pcoded-mcaret"></span>
					</a>
				</li>				
			</ul>
		</c:if>
		
		<c:if test="${perfil != 'ADMIN'}">	
		<div class="pcoded-navigation-label" data-i18n="nav.category.forms"><i class="ti-layout-grid2-alt"></i> Orçamentos</div>
			<ul class="pcoded-item pcoded-left-item">
				<li>
					<a href="#!" class="waves-effect waves-dark">
						<span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
						<span class="pcoded-mtext" data-i18n="nav.form-components.main">Meus Orçamentos</span>
						<span class="pcoded-mcaret"></span>
					</a>
				</li>				
			</ul>
		</c:if>
	</ul>
	</div>
</nav>