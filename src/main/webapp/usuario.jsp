<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Usuario</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card shadow">
          <div class="card-header bg-info text-white">
            <h2 class="mb-0">Usuario</h2>
          </div>
          <div class="card-body">
            <table class="table table-bordered table-striped">
              <tbody>
                <c:choose>
                  <c:when test="${not empty usuario}">
                    <tr>
                      <th scope="row">ID</th>
                      <td>${usuario.id}</td>
                    </tr>
                    <tr>
                      <th scope="row">Username</th>
                      <td>${usuario.username}</td>
                    </tr>
                    <tr>
                      <th scope="row">Nombre</th>
                      <td>${usuario.nombre}</td>
                    </tr>
                    <tr>
                      <th scope="row">Primer Apellido</th>
                      <td>${usuario.apellido1}</td>
                    </tr>
                    <tr>
                      <th scope="row">Segundo Apellido</th>
                      <td>${usuario.apellido2}</td>
                    </tr>
                  </c:when>
                  <c:otherwise>
                    <tr>
                      <td colspan="2" class="text-center text-danger">No se encontró usuario con este ID.</td>
                    </tr>
                  </c:otherwise>
                </c:choose>
              </tbody>
            </table>
            <a href="usuario?operation=all" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Volver</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
