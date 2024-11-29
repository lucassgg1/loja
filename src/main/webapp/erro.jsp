<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/bootstrap/css/bootstrap.min.css">

<link
	href="<%=request.getContextPath()%>/assets/css/bootstrap/css/bootstrap.min_5.3.1.css"
	rel="stylesheet" />

<link href="<%=request.getContextPath()%>/assets/css/myCss/style.css"
	rel="stylesheet" />

<title>DLEI - Seu Store da Construção</title>

</head>


<body>
	<jsp:include page="navbar-home.jsp"></jsp:include>
	<div class="container div-container">
		<div class="row">
			<h3>Mensagem de Erro!</h3>
			<h5>Entre em contato com a equipe de suporte do sistema e
				informe o erro abaixo</h5>
			<div>
				<%
				out.print(request.getAttribute("msg"));
				%>
			</div>

		</div>
	</div>
</body>
</body>
</html>