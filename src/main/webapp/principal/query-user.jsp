<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-8">
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
    <!-- Pre-loader start -->
    <jsp:include page="theme-loader.jsp"></jsp:include>
    <!-- Pre-loader end -->

    <!-- class pcoded start -->
    <div id="pcoded" class="pcoded">
        <div class="pcoded-overlay-box"></div>
        <!-- class pcoded-container navbar-wrapper start -->
        <div class="pcoded-container navbar-wrapper">
            <jsp:include page="navbar.jsp"></jsp:include>

            <!-- class pcoded-main-container start -->
            <div class="pcoded-main-container">
                <div class="pcoded-wrapper">
                    <jsp:include page="navbar-main-menu.jsp"></jsp:include>

                    <!-- class pcoded-content start -->
                    <div class="pcoded-content">
                        <!-- Page-header start -->
                        <jsp:include page="page-header.jsp"></jsp:include>
                        <!-- Page-header end -->

                        <!-- class pcoded-inner-content start -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <!-- class page-wrapper start -->
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <!-- class row start -->
                                        <div class="row">                                        
                                            <!-- class col-sm-12 start -->
                                            <div class="col-sm-12">                                            
                                                 <!-- class card start -->
                                                <div class="card">
                                                    <!-- class card-block start -->
                                                    <div class="card-block">
                                                        <h3>Consulta de Usuבrio</h3>
                                                        
                                                        <form id="formUser" action="${pageContext.request.contextPath}/ServletUsuarioController">
														   
														   <div class="input-group mb-3">
															<input type="text" class="form-control"
																placeholder="Busca por nome" aria-label="nome" id="nomeBusca" name="nomeBusca"
																aria-describedby="basic-addon2">
															<div class="input-group-append">
																<button class="btn btn-success waves-effect waves-light"
																	type="button" onclick="buscarUsuario();">Buscar</button>
															</div>
															</div>
														</form>
														
														<p id="totalResultados"></p>
														
														<table class="table table-bordered" id="tabelaResultados">
														    <thead>
														        <tr>
														            <th>ID</th>
														            <th>Nome</th>
														            <th>Editar</th>
														        </tr>
														    </thead>
														    <tbody>
														        <!-- Resultados serדo inseridos aqui -->
														    </tbody>
														</table>
                                                    <!-- class card-block end -->    
                                                    </div>
                                                <!-- class card end -->    
                                                </div>
                                             <!-- class col-sm-12 end -->
                                            </div>
                                        <!-- class row end -->
                                        </div>
                                    <!-- Page-body end -->
                                    </div>                                    
                                <!-- class page-wrapper start -->
                                </div>  
                            <!-- Main-body end -->                              
                            </div> 
                        <!-- class pcoded-inner-content end -->                           
                        </div>
                    <!-- class pcoded-content end -->
                    </div>
                </div>
            <!-- class pcoded-main-container start -->
            </div>
        <!-- class pcoded-container navbar-wrapper end -->
        </div>
    <!-- class pcoded end -->
    </div>

    <jsp:include page="javaScriptFile.jsp"></jsp:include> 
    <script>    
    function buscarUsuario() {
        var nomeBusca = document.getElementById('nomeBusca').value;

        /* Validando se o campo tem dado */
        if (nomeBusca.trim() !== '') {
            var urlAction = document.getElementById('formUser').action;
            var request = new XMLHttpRequest();
            request.open('GET', urlAction + '?nomeBusca=' + encodeURIComponent(nomeBusca) + '&acao=buscarUserAjax', true);
            request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=ISO-8859-1');

            request.onreadystatechange = function () {
                if (request.readyState === 4 && request.status === 200) {
                    var json = JSON.parse(request.responseText);

                    $('#tabelaResultados > tbody > tr').remove();

                    for (var p = 0; p < json.length; p++) {
                        $('#tabelaResultados > tbody').append('<tr> <td>' + json[p].id + '</td> <td>' + json[p].nome + '</td> <td><button onclick="verEditar(' + json[p].id + ')" class="btn btn-info">Ver</button></td> </tr>');
                    }

                    document.getElementById('totalResultados').textContent = 'Resultados encontrados: ' + json.length; /* Gera quantidade de registros */
                }
            };

            request.onerror = function () {
                alert('Erro ao buscar usuבrio por nome: ' + request.responseText);
            };

            request.send();
        }
    }


    function verEditar(id) {
        var urlAction = $('#formUser').attr('action');
        window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
    }

    </script>
    
</body>
</html>