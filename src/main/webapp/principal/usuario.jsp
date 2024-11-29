<%@page import="model.ModelLogin"%>
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
										
											<h3>Cadastro de Usuário</h3>										

										<!-- Retorna o valor para o form value="${modelLogin.nome}" -->
										<!-- "multipart/form-data" para permitir upload -->
										<form enctype="multipart/form-data"	action="<%=request.getContextPath()%>/ServletUsuarioController"
											method="post" id="formUser">

											<input type="hidden" name="acao" id="acao" value="">
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">ID</label>
												<div class="col-sm-2">
													<input id="id" class="form-control" type="text" name="id"
														readonly value="${modelLogin.id}">
												</div>
											</div>
											
											<div class="row mb-3 input-group">
										    
										    <div class="col-sm-1 input-group-prepend">
											    <c:choose>
											        <c:when test="${modelLogin.fotouser != '' && modelLogin.fotouser != null}">
											            <img class="imagemUser" alt="Imagem User" id="fotoembase64" src="${modelLogin.fotouser}">
											        </c:when>
											        <c:otherwise>
											            <img class="imagemUser" alt="Imagem User" id="fotoembase64" src="<%= request.getContextPath() %>/image/user4_64X64.png">
											        </c:otherwise>
											    </c:choose>
											</div>

										    
										    <!-- aceitará apenas imagem -->
										    <input type="file" alt="Imagem User" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="buttonImagemUser form-control-file">
											</div>											
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Nome</label>
												<div class="col-sm-4">
													<input id="nome" class="form-control" type="text"
														name="nome" required="required"
														value="${modelLogin.nome}">
												</div>
											</div>
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Dat. nascimento</label>
												<div class="col-sm-4">
													<input id="dataNascimento" class="form-control" type="test"
														name="dataNascimento" required="required" value="${modelLogin.dataNascimento}">
												</div>
											</div>

											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">E-mail</label>
												<div class="col-sm-4">
													<input id="email" class="form-control" type="email"
														name="email" required="required"
														value="${modelLogin.email}">
												</div>
											</div>
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Perfil</label>
												<div class="col-sm-4">
												<select class="form-control" aria-label="Default select example" name="perfil" required="required">
												  <option disabled="disabled">[Selecione o Perfil]</option>
												  
												  
												  <option value="ADMIN" <%												  
												  ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												  
												  if(modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Admin</option>
												 
												  
												  <option value="VENDEDOR" <% 
												  modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												  
												  if(modelLogin != null && modelLogin.getPerfil().equals("VENDEDOR")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >Vendedor</option>
												   
												  <option value="USER" <% 
												  modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												  
												  if(modelLogin != null && modelLogin.getPerfil().equals("USER")){
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
												  } %> >User</option>
												</select>
												</div>
											</div>

											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Cep</label>
												<div class="col-sm-2">
													<input onblur="pesquisaCep();" id="cep" class="form-control" type="text"
														name="cep" placeholder="Digite e press tab" required="required"
														maxlength="8" value="${modelLogin.cep}">
												</div>
												<label class="col-sm-1 col-form-label">Rua</label>
												<div class="col-sm-4">
													<input id="logradouro" class="form-control" type="text"
														name="logradouro"
														value="${modelLogin.logradouro}">
												</div>												
											</div>
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Nº</label>
												<div class="col-sm-2">
													<input id="numero" class="form-control" type="text"
														name="numero" required="required"
														value="${modelLogin.numero}">
												</div>
												<label class="col-sm-1 col-form-label">Complem.</label>
												<div class="col-sm-3">
													<input id="complemento" class="form-control" type="text"
														name="complemento"
														value="${modelLogin.complemento}">
												</div>
											</div>
											
											<div class="row mb-3">												
												<label class="col-sm-1 col-form-label">Bairro</label>
												<div class="col-sm-3">
													<input id="bairro" class="form-control" type="text"
														name="bairro"
														value="${modelLogin.bairro}">
												</div>
												<label class="col-sm-1 col-form-label">Cidade</label>
												<div class="col-sm-4">
													<input id="localidade" class="form-control" type="text"
														name="localidade"
														value="${modelLogin.localidade}">
												</div>
											</div>
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Estado</label>
												<div class="col-sm-2">
													<input id="uf" class="form-control" type="text" name="uf"
														maxlength="2" value="${modelLogin.uf}">
												</div>
											</div>
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Login</label>
												<div class="col-sm-2">
													<input id="login" class="form-control" type="text"
														name="login" required="required" autocomplete="off"
														value="${modelLogin.login}">
												</div>
												<label class="col-sm-1 col-form-label">Senha</label>
												<div class="col-sm-2">
													<input id="senha" class="form-control" type="password"
														name="senha" required="required" autocomplete="off"
														value="${modelLogin.senha}">
												</div>
											</div>
											
											<div class="row mb-3">												
												<label class="col-sm-1 col-form-label">Sexo:</label>												
												<div class="form-check form-check-inline col-sm-2">
												  <input class="form-check-input" type="radio" name="sexo" value="MASCULINO" checked="checked" 
												  <% modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												  
												  if(modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
														out.print(" ");
														out.print("checked=\"checked\"");
														out.print(" ");
												  }
												  %> >
												  <label class="form-check-label">Masculino</label>
												</div>
												<div class="form-check form-check-inline col-sm-2">
												  <input class="form-check-input" type="radio" name="sexo" value="FEMININO" 
												  <% modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												  
												  if(modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
														out.print(" ");
														out.print("checked=\"checked\"");
														out.print(" ");
												  } %> />
													<label class="form-check-label">Feminino</label>
												</div>																								
											</div>
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">CPF</label>
												<div class="col-sm-4">
													<input oninput="mascara(this)" type="text" name="cpf" class="form-control"
													required="required" maxlength="14" 
													value="${modelLogin.cpf}">
												</div>
											</div>
											
											<div class="row mb-3">
												<label class="col-sm-1 col-form-label">Telefone</label>
												<div class="col-sm-4">
													<input type="tel" name="telefone" class="form-control"
													required="required" maxlength="15" onkeyup="handlePhone(event)"
													value="${modelLogin.telefone}">
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
													<th scope="col">Nome</th>
													<th scope="col">Ver</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items='${modelLogins}' var='ml'>
												<tr>
												<td><c:out value="${ml.id}"> </c:out></td>
												<td><c:out value="${ml.nome}"> </c:out></td>
												<td><a class="btn btn-success" href="<%=request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}" >Ver</a></td>
												</tr>
											</c:forEach>			
											</tbody>
										</table>					
										</div>										
										<!-- Paginação - implantação futura -->										
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
		  
		<jsp:include page="javaScriptFile.jsp"></jsp:include>			
		
		
		<script type="text/javascript">
		/*Máscara para CPF*/
		function mascara(i){   
		   var v = i.value;
		   
		   if(isNaN(v[v.length-1])){ // impede entrar outro caractere que não seja número
		      i.value = v.substring(0, v.length-1);
		      return;
		   }
		   
		   i.setAttribute("maxlength", "14");
		   if (v.length == 3 || v.length == 7) i.value += ".";
		   if (v.length == 11) i.value += "-";
		
		}
		
		
		/* Máscara telefone - telefone*/
		const handlePhone = (event) => {
		  let input = event.target
		  input.value = phoneMask(input.value)
		}
		
		const phoneMask = (value) => {
		  if (!value) return ""
		  value = value.replace(/\D/g,'')
		  value = value.replace(/(\d{2})(\d)/,"($1) $2")
		  value = value.replace(/(\d)(\d{4})$/,"$1-$2")
		  return value
		}		
		
		
		/* permite apenas número*/
		$("#numero").keypress(function (event){
			return /\d/.test(String.fromCharCode(event.keyCode));
		});
		
		/* permite apenas número*/
		$("#cep").keypress(function (event){
			return /\d/.test(String.fromCharCode(event.keyCode));
		});
		
		
		$(function() {
		    $("#dataNascimento").datepicker({
		        dateFormat: 'dd/mm/yy',
		        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		        nextText: 'Próximo',
		        prevText: 'Anterior'
		    });

		    let dataNascimento = $("#dataNascimento").val();

		    if (dataNascimento) {
		        let dateFormat = new Date(dataNascimento);
		        if (!isNaN(dateFormat)) { // Verifica se a data é válida
		            $("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', { timeZone: 'UTC' }));
		        } else {
		            console.error("Data de nascimento inválida.");
		        }
		    }

		    $("#nome").focus();
		});
		
		
		/*API de cep*/
		function pesquisaCep(){
			var cep = $("#cep").val();
			
			 $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
				
				 if (!("erro" in dados)) {
                     //Atualiza os campos com os valores da consulta.
                     $("#logradouro").val(dados.logradouro);
                     $("#bairro").val(dados.bairro);
                     $("#localidade").val(dados.localidade);
                     $("#uf").val(dados.uf);
                 } //end if.
                 else {
                     //CEP pesquisado não foi encontrado.
                     limpa_formulário_cep();
                     alert("CEP não encontrado.");
                 }
			
			});
		}
		
		/*Para foto*/
		function visualizarImg(fotoembase64, fileFoto) {
		    var preview = document.getElementById(fotoembase64); // Campo IMG HTML
		    var fileInput = document.getElementById(fileFoto);
		    
		    if (!preview) {
		        console.error("Elemento de visualização não encontrado: " + fotoembase64);
		        return;
		    }
		    
		    if (!fileInput) {
		        console.error("Elemento de entrada de arquivo não encontrado: " + fileFoto);
		        return;
		    }
		    
		    var fileUser = fileInput.files[0];
		    var reader = new FileReader();
		    
		    reader.onloadend = function() {
		        preview.src = reader.result; // Carrega a foto na tela
		    };
		    
		    reader.onerror = function() {
		        console.error("Erro ao ler o arquivo.");
		        preview.src = ''; // Limpa a imagem em caso de erro
		    };
		    
		    if (fileUser) {
		        reader.readAsDataURL(fileUser); // Preview da imagem
		    } else {
		        preview.src = '';
		    }
		}
		
	
		/*Delete com JS*/
		function criarDelete() {
		    const formUser = document.getElementById("formUser");
		    const campos = formUser.querySelectorAll('input, select, option, textarea, radio, file');
		    let camposVazios = false;
		
		    campos.forEach(campo => {
		        // Permitir que apenas o campo 'complemento' possa ser vazio
		        if (campo.type !== 'hidden' && campo.value.trim() === '' && campo.name !== 'complemento' && campo.name !== 'fileFoto') {
		            camposVazios = true;
		        }
		    });
		
		    if (!camposVazios && confirm('Deseja realmente excluir os dados?')) {
		        formUser.method = 'get';
		        document.getElementById("acao").value = 'deletar';
		        formUser.submit();
		    } else if (camposVazios) {
		        alert("Existem campos vazios que precisam ser preenchidos!");
		    }
		
		    limparForm();
		}


		/* Limpa o form */
        function limparForm() {
            var elementos = document.getElementById("formUser").elements; // Retorna os elementos HTML dentro do form

            for (var p = 0; p < elementos.length; p++) {
                elementos[p].value = '';
            }

            // Limpa a imagem de visualização
            var preview = document.getElementById('fotoembase64');
            if (preview) {
                preview.src = '<%= request.getContextPath() %>/image/user4_64X64.png';
            }
        }
		</script>
	</div>
</body>
</html>
