<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var = "detalheUsuario" value = "${usuarioLogado}"/>

<c:choose>
  <c:when test="${detalheUsuario.papeis.contains('ADMIN')}">
    <jsp:include page="/auth/admin/admin-nav.jsp" />
  </c:when>
  
  <c:when test="${detalheUsuario.papeis.contains('USER')}">
    <jsp:include page="/auth/user/user-nav.jsp" />
  </c:when>
  <c:otherwise>
     <jsp:include page="/public/public-nav.jsp" />
  </c:otherwise>  
</c:choose>
