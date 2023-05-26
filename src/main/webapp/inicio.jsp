<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>

<form action="Inicio" method="POST">
<div class="input-group">
  <input type="number" class="form-control rounded" placeholder="min" aria-label="min" name="min"/>
  <input type="number" class="form-control rounded" placeholder="max" aria-label="max" name="max"/>
  <button type="submit" class="btn btn-outline-primary">Filtrar</button>
</div>
</form>
<form action="Inicio" method="POST">
<div class="input-group">
  <input type="search" class="form-control rounded" placeholder="Buscar" aria-label="Buscar" name="busqueda" aria-describedby="search-addon" />
  <button type="submit" class="btn btn-outline-primary">Buscar</button>
</div>
</form>

<h1>Lista de productos:</h1>

<c:set var="cant" value="0" />
<form action="Inicio" method="POST">
<table class="table">
  <thead class="table-light">
    <tr>
      <th scope="col">Eliminado Multiple</th>
      <th scope="col">ID</th>
      <c:if test="${orden eq 'asc'}">
        	<th scope="col"><a style="text-decoration: none;color: black" href="Inicio?codOrden=desc">Codigo ⬆</a></th>
      </c:if>
      <c:if test="${orden eq 'desc'}">
        	<th scope="col"><a style="text-decoration: none;color: black" href="Inicio?codOrden=asc">Codigo ⬇</a></th>
      </c:if>
      <c:if test="${(orden ne 'desc' && tipoLogin ne 'asc') || orden eq null}">
        	<th scope="col"><a style="text-decoration: none;color: black" href="Inicio?codOrden=desc">Codigo</a></th>
      </c:if>
      <th scope="col">Nombre</th>
      <th scope="col">Cantidad</th>
      <th scope="col">Precio</th>
      <th scope="col">Caducidad</th>
      <th scope="col">Seccion</th>
      <th scope="col">Modificar</th>
      <th scope="col">Eliminar</th>
    </tr>
  </thead>
  <tbody>
  
   <c:forEach var="producto" items="${productos}">
   <c:set var="cant" value="${cant + 1}" />
	    <tr>
	      <td><input class="form-check-input" type="checkbox" value="${producto.id}" name="mdc-${cant}"></td>
	      <td>${producto.id}</td>
	      <td>${producto.codigo}</td>
	      <td>${producto.nombre}</td>
	      <td>${producto.cantidad}</td>
	      <td>${producto.precio}</td>
	      <td>${producto.caducidad}</td>
	      <td>${producto.seccion.nombre}</td>
	      <td><a href="Modificar?id=${producto.id}" class="btn btn-primary">Editar</a></td>
	      <td><a href="Eliminar?id=${producto.id}" class="btn btn-danger">Eliminar</a></td>
	    </tr>
    </c:forEach>
  </tbody>
</table>
  <button type="submit" class="btn btn-warning">Eliminar multiple</button>
  <a href="Insertar" class="btn btn-primary">Insertar</a>
</form>


<form style="margin-top: 10px" action="Inicio" method="POST">
<div class="input-group">
  <input type="text" class="form-control rounded" name="multidelete" placeholder="Ej: 14, 15, 33"/>
  <button type="submit" class="btn btn-outline-primary">Eliminar multiple</button>
</div>
</form>

</body>
</html>