
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card border-danger shadow">
                    <div class="card-header bg-danger text-white">
                        <h3 class="mb-0">Error</h3>
                    </div>
                    <div class="card-body">
                        <p class="fw-bold">Se ha producido un error en la aplicación:</p>
                        <div class="alert alert-danger" role="alert">
                            ${errorMessage}
                        </div>
                        <p>Por favor, inténtelo de nuevo más tarde.</p>
                    </div>
                    <a href="usuario?operation=all" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Volver</a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>