<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito de la compra</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"rel="stylesheet"/>
</head>
<body>


<h1>Elementos en el carrito: </h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nombre</th>
      <th scope="col">Precio</th>
      <th scope="col">Eliminar</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="elementoCarrito" items="${carrito}">
    <tr>
      <th>${elementoCarrito.id}</th>
      <td>${elementoCarrito.nombre}</td>
      <td>${elementoCarrito.precio}</td>
      <td><a href="EliminarCarrito?id=${elementoCarrito.id}" class="btn btn-danger"><i class="fa-solid fa-trash"></a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
<a href="Eliminar?id=-1" class="btn btn-primary">Finalizar la Compra</a>

</body>
</html>