<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<nav class="navbar header-navbar pcoded-header">
	<div class="navbar-wrapper">
		<div class="navbar-logo">
			<div class="mobile-menu waves-effect waves-light" id="mobile-collapse">
				<i class="ti-menu"></i>
			</div>
			
				<a class="mobile-menu waves-effect waves-light" id="mobile-collapse"
					href="#!"> <i class="ti-menu"></i>
				</a>
			<div class="mobile-search waves-effect waves-light">
				<div class="header-search">
					<div class="main-search morphsearch-search">
						<div class="input-group">
							<span class="input-group-addon search-close"><i
								class="ti-close"></i></span> <input type="text" class="form-control"
								placeholder="Enter Keyword"> <span
								class="input-group-addon search-btn"><i class="ti-search"></i></span>
						</div>
					</div>
				</div>
			</div>
			<a href="<%= request.getContextPath() %>/principal/principal.jsp"> <img class="img-fluid" src="<%= request.getContextPath() %>/assets/images/logo.png"
				alt="Logo" /></a>
			<a class="mobile-options waves-effect waves-light"> <i class="ti-more"></i> </a>
		</div>

		<div class="navbar-container container-fluid">
			<ul class="nav-left">
				<li>
					<div class="sidebar_toggle">
						<a href=""><i class="ti-menu"></i></a>
					</div>
				</li>				
			</ul>
			
			<ul class="nav-right">
				<li class="nav-bar-right">
					<div><i class="fa fa-user"></i></div>			
				</li>
				<li class="user-profile header-notification"><a href="#!"
					class="waves-effect waves-light"> <!-- Busca nome do usuário logado -->
						<span><%=request.getSession().getAttribute("usuario")%></span> <i class="ti-angle-down"></i></a>
						
					<ul class="show-notification profile-notification">				   
						<li class="waves-effect waves-light"><a
							href="<%= request.getContextPath() %>/ServletLogin?acao=logout"> <i
								class="ti-layout-sidebar-left"></i> Sair
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>