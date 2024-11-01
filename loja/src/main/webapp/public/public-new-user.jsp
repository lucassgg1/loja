<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DLEI - Cadastro de Cliente/ Usuário</title>

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
	<!-- <jsp:include page="/public/public-nav.jsp" /> -->

	<div class="container div-container">
		<div class="row">
			<div class="col">
				<h2>Cadastro de Cliente/ Usuário</h2>

				<!-- Mensagem de retorno - USO DO JSTL-->
				<c:if test="${mensagem != null}">
					<!-- Alert do Boostrap -->
					<div class="alert alert-success alert-dismissible fade show">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<span><c:out value="${mensagem}" /></span>
					</div>
				</c:if>


				<form
					action="${pageContext.request.contextPath}/public?action=insert"
					method="post">

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Nome</label>
						<div class="col-sm-4">
							<input id="nome" class="form-control" type="text" name="nome"
								placeholder="Seu Nome" required>
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">CPF</label>
						<div class="col-sm-2">
							<input id="cpf" class="form-control" type="text" name="cpf"
								placeholder="Seu CPF">
						</div>
						<label class="col-sm-1 col-form-label">Dt. Nasc.</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="nascimento"
								placeholder="Dt. de Nascimento" required>
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">E-mail</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="email"
								placeholder="Seu E-mail" required>
						</div>
						<label class="col-sm-1 col-form-label">Telefone</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="telefone"
								placeholder="Seu Telefone" data-mask="(00) 00000-0000" required>
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Login</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="login"
								placeholder="Seu Usuário">
						</div>
						<label class="col-sm-1 col-form-label">Senha</label>
						<div class="col-sm-2">
							<input class="form-control" type="password"
								name="password" placeholder="Sua Senha" required>
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Endereço</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" name="endereco"
								placeholder="Seu Endereço" required>
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Número</label>
						<div class="col-sm-1">
							<input class="form-control" type="text" name="numEndereco"
								placeholder="Nº" required>
						</div>
						<label class="col-sm-1 col-form-label">Complem.</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="complEndereco"
								placeholder="Complemento">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Bairro</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="bairro"
								placeholder="Seu Bairro" required>
						</div>
						<label class="col-sm-1 col-form-label">Cidade</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="cidade"
								placeholder="Sua Cidade" required>
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Estado</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="estado"
								placeholder="Seu Estado" required>
						</div>
						<label class="col-sm-1 col-form-label">UF</label>
						<div class="col-sm-1">
							<input class="form-control" type="text" name="uf"
								placeholder="UF" required>
						</div>
						<label class="col-sm-1 col-form-label">CEP</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="cep"
								placeholder="CEP" required>
						</div>
					</div>

					<div class="button-margin-top">
						<input class="btn btn-primary" type="submit" value="Cadastrar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>

<footer>
 <jsp:include page="/public/public-footer.jsp"/>
</footer>
</html>