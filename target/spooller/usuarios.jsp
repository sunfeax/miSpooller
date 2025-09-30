<%@ page contentType="text/html;charset=UTF-8" language="java"
isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lista de Usuarios</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>
  <body class="bg-light">
    <div class="container py-4">
      <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="mb-0">Lista de Usuarios</h1>
        <button
          class="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#addUserModal"
        >
          <i class="bi bi-person-plus"></i> Add new user
        </button>
      </div>
      <div class="table-responsive">
        <table class="table table-hover align-middle shadow-sm rounded">
          <thead class="table-dark">
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Username</th>
              <th scope="col">Nombre</th>
              <th scope="col">Primer Apellido</th>
              <th scope="col">Segundo Apellido</th>
              <th scope="col" class="text-center">Actions</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="usuario" items="${usuarios}">
              <tr class="user-row" data-href="usuario?operation=user&id=${usuario.id}" style="cursor:pointer;">
                <td>${usuario.id}</td>
                <td>${usuario.username}</td>
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido1}</td>
                <td>${usuario.apellido2}</td>
                <td class="text-center">
                  <button type="button" class="btn btn-outline-primary btn-sm edit-user-btn me-2"
                    data-bs-toggle="modal"
                    data-bs-target="#editUserModal"
                    data-id="${usuario.id}"
                    data-username="${usuario.username}"
                    data-nombre="${usuario.nombre}"
                    data-apellido1="${usuario.apellido1}"
                    data-apellido2="${usuario.apellido2}">
                    <i class="bi bi-pencil"></i>
                  </button>
                  <button type="button" class="btn btn-outline-danger btn-sm delete-user-btn" 
                    data-bs-toggle="modal" 
                    data-bs-target="#deleteUserModal"
                    data-id="${usuario.id}"
                    data-username="${usuario.username}"
                    data-nombre="${usuario.nombre}"
                    data-apellido1="${usuario.apellido1}"
                    data-apellido2="${usuario.apellido2}">
                    <i class="bi bi-trash"></i>
                  </button>
                </td>
    <!-- Modal для редактирования пользователя -->
    <div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="usuario" method="post">
            <div class="modal-header">
              <h5 class="modal-title" id="editUserModalLabel">Edit user</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <input type="hidden" name="operation" value="update" />
              <input type="hidden" name="id" id="editUserId" />
              <div class="mb-3">
                <label for="editUsername" class="form-label">Username</label>
                <input type="text" class="form-control" id="editUsername" name="username" required />
              </div>
              <div class="mb-3">
                <label for="editNombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="editNombre" name="nombre" required />
              </div>
              <div class="mb-3">
                <label for="editApellido1" class="form-label">Primer Apellido</label>
                <input type="text" class="form-control" id="editApellido1" name="apellido1" required />
              </div>
              <div class="mb-3">
                <label for="editApellido2" class="form-label">Segundo Apellido</label>
                <input type="text" class="form-control" id="editApellido2" name="apellido2" />
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
              <button type="submit" class="btn btn-primary">Update</button>
            </div>
          </form>
        </div>
      </div>
    </div>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

    <div class="mt-4">
  <a href="index.jsp" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Volver</a>
    </div>
    <!-- Modal для подтверждения удаления пользователя -->
    <div class="modal fade" id="deleteUserModal" tabindex="-1" aria-labelledby="deleteUserModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="usuario" method="post">
            <div class="modal-header">
              <h5 class="modal-title" id="deleteUserModalLabel">Delete user</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p>Are you sure you want to delete this user?</p>
              <ul class="list-group mb-3">
                <li class="list-group-item"><strong>ID:</strong> <span id="modalUserId"></span></li>
                <li class="list-group-item"><strong>Username:</strong> <span id="modalUserUsername"></span></li>
                <li class="list-group-item"><strong>Nombre:</strong> <span id="modalUserNombre"></span></li>
                <li class="list-group-item"><strong>Primer Apellido:</strong> <span id="modalUserApellido1"></span></li>
                <li class="list-group-item"><strong>Segundo Apellido:</strong> <span id="modalUserApellido2"></span></li>
              </ul>
              <input type="hidden" name="operation" value="delete" />
              <input type="hidden" name="id" id="deleteUserId" />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
              <button type="submit" class="btn btn-danger">Delete</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- Modal для добавления пользователя -->
    <div
      class="modal fade"
      id="addUserModal"
      tabindex="-1"
      aria-labelledby="addUserModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="usuario" method="post">
            <div class="modal-header">
              <h5 class="modal-title" id="addUserModalLabel">Add new user</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input
                  type="text"
                  class="form-control"
                  id="username"
                  name="username"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  name="password"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input
                  type="text"
                  class="form-control"
                  id="nombre"
                  name="nombre"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="apellido1" class="form-label"
                  >Primer Apellido</label
                >
                <input
                  type="text"
                  class="form-control"
                  id="apellido1"
                  name="apellido1"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="apellido2" class="form-label"
                  >Segundo Apellido</label
                >
                <input
                  type="text"
                  class="form-control"
                  id="apellido2"
                  name="apellido2"
                />
              </div>
              <input type="hidden" name="operation" value="add" />
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Cancel
              </button>
              <button type="submit" class="btn btn-primary">Add</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
      // Заполняем модалку данными пользователя при нажатии на delete
      document.addEventListener('DOMContentLoaded', function() {
        var deleteUserModal = document.getElementById('deleteUserModal');
        deleteUserModal.addEventListener('show.bs.modal', function (event) {
          var button = event.relatedTarget;
          document.getElementById('modalUserId').textContent = button.getAttribute('data-id');
          document.getElementById('modalUserUsername').textContent = button.getAttribute('data-username');
          document.getElementById('modalUserNombre').textContent = button.getAttribute('data-nombre');
          document.getElementById('modalUserApellido1').textContent = button.getAttribute('data-apellido1');
          document.getElementById('modalUserApellido2').textContent = button.getAttribute('data-apellido2');
          document.getElementById('deleteUserId').value = button.getAttribute('data-id');
        });

        // Заполняем модалку редактирования пользователя
        var editUserModal = document.getElementById('editUserModal');
        editUserModal.addEventListener('show.bs.modal', function (event) {
          var button = event.relatedTarget;
          document.getElementById('editUserId').value = button.getAttribute('data-id');
          document.getElementById('editUsername').value = button.getAttribute('data-username');
          document.getElementById('editNombre').value = button.getAttribute('data-nombre');
          document.getElementById('editApellido1').value = button.getAttribute('data-apellido1');
          document.getElementById('editApellido2').value = button.getAttribute('data-apellido2');
        });

        // Сделать всю строку таблицы кликабельной
        document.querySelectorAll('.user-row').forEach(function(row) {
          row.addEventListener('click', function(e) {
            // Не переходить по ссылке, если клик по кнопке
            if (e.target.closest('button')) return;
            window.location.href = row.getAttribute('data-href');
          });
        });
      });
    </script>
  </body>
</html>
