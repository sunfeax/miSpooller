package net.ausiasmarch.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;
import net.ausiasmarch.service.UsuarioService;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {

        //sacar el id de usuario de la request
        String idParam = request.getParameter("id");
        Long id = Long.valueOf(idParam);
        //crear un servicio de usuario para que me entregue el usuario
        UsuarioService oUsuarioService = new UsuarioService();

        try {
            UsuarioBean oUsuarioBean = oUsuarioService.get(id);
            request.setAttribute("usuario", oUsuarioBean);
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        } catch (java.sql.SQLException | ResourceNotFoundException e) {
            request.setAttribute("errorMessage", "Error al obtener el usuario: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

}
