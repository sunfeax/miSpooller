package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioDao {

    private final Connection oConnection;

    public UsuarioDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    // маппинг результата, чтобы не дублировать код
    private UsuarioBean mapRow(ResultSet oResultSet) throws SQLException {
        UsuarioBean user = new UsuarioBean();
        user.setId(oResultSet.getLong("id"));
        user.setUsername(oResultSet.getString("username"));
        user.setNombre(oResultSet.getString("nombre"));
        user.setApellido1(oResultSet.getString("apellido1"));
        user.setApellido2(oResultSet.getString("apellido2"));
        return user;
    }

    public UsuarioBean getUserById(Long id) throws SQLException, ResourceNotFoundException {
        String strSQL = "SELECT * FROM usuario WHERE id = ?";
        UsuarioBean oUsuarioBean = null;

        try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL)) {
            oPreparedStatement.setLong(1, id);

            try (ResultSet oResultSet = oPreparedStatement.executeQuery()) {
                if (oResultSet.next()) {
                    oUsuarioBean = mapRow(oResultSet);
                } else {
                    throw new ResourceNotFoundException("User with id " + id + " not found.");
                }
            }
        }
        return oUsuarioBean;
    }

    public List<UsuarioBean> getAllUsers() throws SQLException {
        String strSQL = "SELECT * FROM usuario";
        List<UsuarioBean> list = new ArrayList<>();

        try (PreparedStatement ps = this.oConnection.prepareStatement(strSQL);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    public Long addUser(UsuarioBean newUser) throws SQLException {
        String strSQL = "INSERT INTO usuario (username, password, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?, ?)";
        Long userId = null;

        try (PreparedStatement stmt = this.oConnection.prepareStatement(strSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, newUser.getUsername());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getNombre());
            stmt.setString(4, newUser.getApellido1());
            stmt.setString(5, newUser.getApellido2());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userId = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return userId;
    }

    public void deleteUser(Long id) throws SQLException, ResourceNotFoundException {
        String strSQL = "DELETE FROM usuario WHERE id = ?";
        
        try (PreparedStatement stmt = this.oConnection.prepareStatement(strSQL)) {
            stmt.setLong(1, id);
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new ResourceNotFoundException("User with id " + id + " not found for deletion.");
            }
        }
    }

    public void updateUser(Long id, UsuarioBean userData) throws SQLException, ResourceNotFoundException {
        String strSQL = "UPDATE usuario SET username=?, nombre=?, apellido1=?, apellido2=? WHERE id=?";

        try (PreparedStatement stmt = this.oConnection.prepareStatement(strSQL)) {
            stmt.setString(1, userData.getUsername());
            stmt.setString(2, userData.getNombre());
            stmt.setString(3, userData.getApellido1());
            stmt.setString(4, userData.getApellido2());
            stmt.setLong(5, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new ResourceNotFoundException("User with id " + id + " not found for update.");
            }
        }
    }
}
