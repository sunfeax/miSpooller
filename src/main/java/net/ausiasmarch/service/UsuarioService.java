package net.ausiasmarch.service;

import java.sql.Connection;
import java.sql.SQLException;

import net.ausiasmarch.connection.HikariPool;
import net.ausiasmarch.dao.UsuarioDao;
import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;

import java.util.List;

public class UsuarioService {

    public UsuarioBean findUser(Long id) throws SQLException, ResourceNotFoundException {
        // Es mejor crear la conexion a nivel de servicio y pasarla al DAO
        try (Connection oConnection = HikariPool.getPool().getConnection()) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            UsuarioBean oUsuarioBean = oUsuarioDao.getUserById(id);
            return oUsuarioBean;
        }
    }

    public List<UsuarioBean> getAllUsers() throws SQLException { 
        try (Connection oConnection = HikariPool.getPool().getConnection()) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            return oUsuarioDao.getAllUsers();
        }
    }

    public void createUser(UsuarioBean usuario) throws SQLException {
        try (Connection oConnection = HikariPool.getPool().getConnection()) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            oUsuarioDao.addUser(usuario);
        }
    }

    public void deleteUser(Long id) throws SQLException, ResourceNotFoundException {
        try (Connection oConnection = HikariPool.getPool().getConnection()) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            oUsuarioDao.deleteUser(id);
        }
    }

    public void updateUser(Long id, UsuarioBean userData) throws SQLException, ResourceNotFoundException {
        try (Connection oConnection = HikariPool.getPool().getConnection()) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            oUsuarioDao.updateUser(id, userData);
        }
    }
}
