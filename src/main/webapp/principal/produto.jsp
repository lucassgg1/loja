<%@page import="model.ModelProduto"%>
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
		<div class=""></div>
		<div class="pcoded-container ">
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navbar-main-menu.jsp"></jsp:include>

					<!-- [pcoded-content] css do header da Page-header -->
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->

						<div class="">
							<!-- Main-body start -->
							<!-- [main-body] css do body da Page-header -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">									
										
											<h3>Cadastro de Produto</h3>										
																			
										<form action="<%=request.getContextPath()%>/ServletProdutoController"
											method="post" id="formProd">

											<input type="hidden" name="acao" id="acao" value="">
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">ID</label>
												<div class="col-sm-2">
													<input id="id" class="form-control" type="text" name="id"
														readonly value="${modelProduto.id}">
												</div>
											</div>
											
																		    										
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Produto</label>
												<div class="col-sm-4">
													<input id="produto" class="form-control" type="text"
														name="produto" required="required"
														value="${modelProduto.produto}">
												</div>
											</div>
											
											<div class="row mb-3">											
												<label class="col-sm-1 col-form-label">Tipo</label>
												<div class="col-sm-2">
												<select class="form-control" aria-label="Default select example" name="tipo" required="required">
												  <option disabled="disabled">[Selecione o Tipo]</option>
												  
												  
												  <option value="UN" <%												  
												  ModelProduto modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getTipo().equals("UN")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Un</option>
												 
												  
												  <option value="KG" <% 
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getTipo().equals("KG")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Kg</option>
												   
												  <option value="MT" <% 
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getTipo().equals("MT")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Mt</option>
												  
												  <option value="SC" <% 
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getTipo().equals("SC")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Sc</option>
												  
												  <option value="MILHEIRO" <% 
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getTipo().equals("MILHEIRO")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Milheiro</option>
												</select>
												</div>										
											
												<label class="col-sm-1 col-form-label">Categoria</label>
												<div class="col-sm-2">
												<select class="form-control" aria-label="Default select example" name="categoria" required="required">
												  <option disabled="disabled">[Selecione a Categoria]</option>
												  
												  
												  <option value="ALVENARIA" <%												  
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getCategoria().equals("ALVENARIA")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Alvenaria</option>
												 
												  
												  <option value="ACABAMENTO" <% 
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getCategoria().equals("ACABAMENTO")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Acabamento</option>
												   
												  <option value="HIDRÁULICA" <% 
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getCategoria().equals("HIDRÁULICA")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Hidráulica</option>
												  
												  <option value="ELÉTRICA" <% 
												  modelProduto = (ModelProduto) request.getAttribute("modelProduto");
												  
												  if(modelProduto != null && modelProduto.getCategoria().equals("ELÉTRICA")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Elétrica</option>
												</select>
												</div>											
											</div>


											
											    <div class="row mb-3">
											        <label class="col-sm-1 col-form-label">Vl Compra</label>
											        <div class="col-sm-2">
											            <input id="vlcompra" class="form-control" type="text" name="vlcompra" required="required" oninput="formatarNumero(event)" value="${modelProduto.vlcompra}">
											        </div>
											    
											        <label class="col-sm-1 col-form-label">ICMS</label>
											        <div class="col-sm-2">
											            <input id="icms" class="form-control" type="text" name="icms" required="required" oninput="formatarNumero(event)" value="${modelProduto.icms}">
											        </div>
											   
											    
											    
											        <label class="col-sm-1 col-form-label">Vl Venda</label>
											        <div class="col-sm-2">
											            <input id="vlvenda" class="form-control" type="text" name="vlvenda" required="required" oninput="formatarNumero(event)" value="${modelProduto.vlvenda}">
											        </div>
											    </div>
											
											
											
											<div class="card-block">												
												<button type="button"
													class="btn btn-primary waves-effect waves-light"
													onclick="limparForm();">Novo</button>												
												<button type="submit"
													class="btn btn-success waves-effect waves-light">Salvar</button>												
												<button type="button"
													class="btn btn-danger waves-effect waves-light"
													onclick="criarDelete();">Excluir</button>																							
											</div>											
										</form>
										<div class="form-cad-user-msg">
												<span id="msg">${msg}</span>
										</div>
										
										<div style="height: 300px; overflow: scroll;">										
										<table class="table" id="tabelaResultadosView">
											<thead>
												<tr>
													<th scope="col">ID</th>
													<th scope="col">produto</th>
													<th scope="col">Ver</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items='${modelProdutos}' var='ml'>
												<tr>
												<td><c:out value="${ml.id}"> </c:out></td>
												<td><c:out value="${ml.produto}"> </c:out></td>
												<td><a class="btn btn-success" href="<%=request.getContextPath()%>/ServletProdutoController?acao=buscarEditar&id=${ml.id}" >Ver</a></td>
												</tr>
											</c:forEach>			
											</tbody>
										</table>					
										</div>																			
									</div>
								</div>
							</div>
						</div>
						<div id="styleSelector"></div>
					</div>
				</div>
			</div>
		</div>
		  
		<jsp:include page="javaScriptFile.jsp"></jsp:include>			 
			
		
		<script type="text/javascript">	
		/*Converte , em .*/
        function formatarNumero(event) {
            const input = event.target;
            input.value = input.value.replace(',', '.');
        }
	
		/*Delete com JS*/
		function criarDelete() {
		    const formProd = document.getElementById("formProd");
		    const campos = formProd.querySelectorAll('input, select, textarea');
		    let camposVazios = false;
		
		    campos.forEach(campo => {
		        if (campo.type !== 'hidden' && campo.value.trim() === '') {
		            camposVazios = true;
		        }
		    });
		
		    if (!camposVazios && confirm('Deseja realmente excluir os dados?')) {
		        formProd.method = 'get';
		        document.getElementById("acao").value = 'deletar';
		        formProd.submit();
		    } else if (camposVazios) {
		        alert("Existem campos vazios que precisam ser preenchidos.");
		    }
		
		    limparForm();
		}



		/* Limpa o form */
		function limparForm() {
			var elementos = document.getElementById("formProd").elements; // Retorna os elementos HTML dentro do form

			for (var p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
		</script>
	</div>
</body>
</html>
