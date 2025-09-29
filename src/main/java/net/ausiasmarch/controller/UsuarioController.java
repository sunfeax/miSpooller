package net.ausiasmarch.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;
import net.ausiasmarch.service.UsuarioService;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

    // GET http://localhost:8089/spooller/usuario?operation=all
    // GET http://localhost:8089/spooller/usuario?operation=user&id=2
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if ("all".equalsIgnoreCase(operation)) {
            getAllUsers(request, response);
        } else if ("user".equalsIgnoreCase(operation)) {
            getUserById(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid operation name.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void getAllUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<UsuarioBean> usuarios = new UsuarioService().getAllUsers();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            UsuarioBean usuario = new UsuarioService().findUser(id);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        } catch (ResourceNotFoundException e) {
            request.setAttribute("errorMessage", "User not found: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // POST http://localhost:8089/spooller/usuario?operation=add
    // POST http://localhost:8089/spooller/usuario?operation=delete&id=3
    // POST http://localhost:8089/spooller/usuario?operation=update&id=3
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if ("add".equalsIgnoreCase(operation)) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String nombre = request.getParameter("nombre");
                String apellido1 = request.getParameter("apellido1");
                String apellido2 = request.getParameter("apellido2");

                UsuarioBean newUser = new UsuarioBean();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setNombre(nombre);
                newUser.setApellido1(apellido1);
                newUser.setApellido2(apellido2);

                new UsuarioService().createUser(newUser);

                response.sendRedirect("usuario?operation=all");

            } catch (Exception e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } else if ("delete".equalsIgnoreCase(operation)) {
            try {
                Long id = Long.valueOf(request.getParameter("id"));
                new UsuarioService().deleteUser(id);
                response.sendRedirect("usuario?operation=all");
            } catch (ResourceNotFoundException e) {
            request.setAttribute("errorMessage", "User not found: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if ("update".equalsIgnoreCase(operation)) {
            try {
                Long id = Long.valueOf(request.getParameter("id"));

                String username = request.getParameter("username");
                String nombre = request.getParameter("nombre");
                String apellido1 = request.getParameter("apellido1");
                String apellido2 = request.getParameter("apellido2");

                UsuarioBean newUserData = new UsuarioBean();
                newUserData.setUsername(username);
                newUserData.setNombre(nombre);
                newUserData.setApellido1(apellido1);
                newUserData.setApellido2(apellido2);

                new UsuarioService().updateUser(id, newUserData);

                response.sendRedirect("usuario?operation=all");

            } catch (ResourceNotFoundException e) {
            request.setAttribute("errorMessage", "User not found: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);

            } catch (Exception e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
