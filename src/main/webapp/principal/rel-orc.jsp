<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="card">
                                                    <div class="card-block">
                                                        <h3>Orçamentos Solicitados</h3>
                                                        
                                                        <div style="height: 300px; overflow: scroll;">
                                                            <table class="table" id="tabelaResultadosView">
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col">ID</th>
                                                                        <th scope="col">Produto</th>
                                                                        <th scope="col">E-mail</th>
                                                                        <th scope="col">Telefone</th>
                                                                        <th scope="col">Orçamento</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                <c:forEach var="orcamento" items="${listaOrc}">
                                                                    <c:if test="${not empty orcamento}">
                                                                        <tr>
                                                                            <td>${orcamento.id}</td>
                                                                            <td>${orcamento.nome}</td>
                                                                            <td>${orcamento.email}</td>                                                                            
                                                                            <td>${orcamento.telefone}</td>
                                                                            <td>${orcamento.orcamento}</td>
                                                                        </tr>
                                                                    </c:if>
                                                                </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--  project and team member end -->
                                </div>
                            </div>
                            <!-- Page-body end -->
                        </div>
                        <div id="styleSelector"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="javaScriptFile.jsp"></jsp:include>   
    
</body>
</html>
