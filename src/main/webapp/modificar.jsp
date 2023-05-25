<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar producto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

</head>
<body>

<h1>Modificar productos:</h1>

<form action="Modificar" method="POST">
  <div class="mb-3">
    <label for="id" class="form-label">ID</label>
    <input type="number" class="form-control" required="required" value="${producto.id}" readonly="readonly" name="id">
  </div>
  <div class="mb-3">
    <label for="codigo" class="form-label">Codigo</label>
    <input type="number" class="form-control" required="required" value="${producto.codigo}" name="codigo">
  </div>
  <div class="mb-3">
    <label for="nombre" class="form-label">Nombre</label>
    <input type="text" class="form-control" required="required" value="${producto.nombre}" name="nombre">
  </div>
  <div class="mb-3">
    <label for="cantidad" class="form-label">Cantidad</label>
    <input type="number" class="form-control" required="required" value="${producto.cantidad}" name="cantidad">
  </div>
  <div class="mb-3">
    <label for="precio" class="form-label">Precio</label>
    <input type="number" step="any" class="form-control" required="required" value="${producto.precio}" name="precio">
  </div>
  <div class="mb-3">
    <label for="caducidad" class="form-label">Caducidad</label>
    <input type="date" class="form-control" required="required" value="${producto.caducidad}" name="caducidad">
  </div>
  <div class="mb-3">
   <label for="seccion" class="form-label">Seccion</label>
	  <select required="required" class="form-select" name="seccion">
	  <c:forEach var="seccion" items="${secciones}">
	    <c:if test="${seccion.id eq producto.seccion.id}">
	 	 <option selected="selected" value="${seccion.id}">${seccion.nombre}</option>
	 	</c:if>
	 	<c:if test="${seccion.id ne producto.seccion.id}">
	 	 <option value="${seccion.id}">${seccion.nombre}</option>
	 	</c:if>
	  </c:forEach>
	 </select>
  </div>
  <button type="submit" class="btn btn-primary">Modificar</button>
</form>


</body>
</html>