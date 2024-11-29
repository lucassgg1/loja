<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set scope="session" var="perfil" value='<%= request.getSession().getAttribute("perfil") %>'></c:set>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navbar-main-menu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->

						
						<div class="pcoded-inner-content">
						
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									
									<div class="page-body">
										<!-- task, page, download counter  start -->
										<div class="row">
											
											<c:if test="${perfil == 'ADMIN'}">
											<div class="col-xl-3 col-md-6">
												<div class="card">
													<div class="card-block">
														<div class="row align-items-center">
															<div class="col-12">
																<h3 class="cards-principal">Cadastro</h3>
																<div >
																<a href="${pageContext.request.contextPath}/ServletUsuarioController?acao=listarUser"
																class="btn btn-primary btn-lg btn-block">Usuário</a>
																</div>
																<div class="button-margin-top-principal">
																<a href="${pageContext.request.contextPath}/ServletProdutoController?acao=listarProd"
																class="btn btn-primary btn-lg btn-block">Produto</a>
																</div>
															</div>															
														</div>
													</div>													
												</div>
											</div>
											</c:if>
											<c:if test="${perfil == 'ADMIN'}">
											<div class="col-xl-3 col-md-6">
												<div class="card">
													<div class="card-block">
														<div class="row align-items-center">
															<div class="col-12">
																<h3 class="cards-principal">Consulta</h3>
																<div >
																<a href="${pageContext.request.contextPath}/ServletUsuarioController?acao=queryUser"
																class="btn btn-primary btn-lg btn-block">Usuário</a>
																</div>
																<div class="button-margin-top-principal">
																<a href="${pageContext.request.contextPath}/ServletProdutoController?acao=queryProd"
																class="btn btn-primary btn-lg btn-block">Produto</a>
																</div>
															</div>															
														</div>
													</div>													
												</div>
											</div>
											</c:if>
											<c:if test="${perfil == 'ADMIN'}">
											<div class="col-xl-3 col-md-6">
												<div class="card">
													<div class="card-block">
														<div class="row align-items-center">
															<div class="col-12">
																<h3 class="cards-principal">Relatório</h3>
																<div >
																<a href="${pageContext.request.contextPath}/ServletUsuarioController?acao=imprimirRelatorioUser"
																class="btn btn-primary btn-lg btn-block">Usuário</a>
																</div>
																<div class="button-margin-top-principal">
																<a href="${pageContext.request.contextPath}/ServletProdutoController?acao=imprimirRelatorioProd"
																class="btn btn-primary btn-lg btn-block">Produto</a>
																</div>
															</div>															
														</div>
													</div>													
												</div>
											</div>
											</c:if>
											<c:if test="${perfil == 'ADMIN'}">
											<div class="col-xl-3 col-md-6">
												<div class="card">
													<div class="card-block">
														<div class="row align-items-center">
															<div class="col-12">
																<h3 class="cards-principal">Orçamentos</h3>
																<div >
																<a href="${pageContext.request.contextPath}/ServletOrcamentoController?acao=imprimirRelatorioProd"
																class="btn btn-primary btn-lg btn-block">Orçamentos</a>
																</div>
															</div>															
														</div>
													</div>													
												</div>
											</div>
											</c:if>
											<c:if test="${perfil != 'ADMIN'}">
											<div class="col-xl-3 col-md-6">
												<div class="card">
													<div class="card-block">
														<div class="row align-items-center">
															<div class="col-12">
																<h3 class="cards-principal">Orçamentos</h3>
																<div >
																<a href="#!"
																class="btn btn-primary btn-lg btn-block">Meus Orçamentos</a>
																</div>
															</div>															
														</div>
													</div>													
												</div>
											</div>
											</c:if>									
											<!-- task, page, download counter  end -->
											</div>
										<!-- Page-body end -->
										</div>
									</div>
								<!-- Main-body end -->	
								</div>
								
							</div>						
							
						</div>
						<div id="styleSelector"></div>
					</div>
				</div>
			</div>
		</div>

	<jsp:include page="javaScriptFile.jsp"></jsp:include>
</body>
</html>
