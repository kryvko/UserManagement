package ua.kh.kryvko.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kh.kryvko.entity.Role;
import ua.kh.kryvko.name.RoleName;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends AbstractDao<Role, Long> implements Closeable {

    private static final Logger LOGGER = Logger.getLogger("error");

    private static final String createString = "INSERT INTO " + RoleName.TABLE + " (" + RoleName.NAME + ") VALUES (?, ?);";
    private static final String readString = "SELECT * FROM " + RoleName.TABLE + " WHERE " + RoleName.ID + "=?;";
    private static final String updateString = "UPDATE " + RoleName.TABLE + " SET " + RoleName.NAME + "=? WHERE " + RoleName.ID + "=?;";
    private static final String deleteString = "DELETE FROM " + RoleName.TABLE + " WHERE " + RoleName.ID + "=?;";
    private static final String findAllString = "SELECT * FROM " + RoleName.TABLE + ";";

    private final Connection connection;

    public RoleDao() throws SQLException {
        this.connection = getConnetction();
        connection.setAutoCommit(false);
    }

    @Override
    public void create(Role newInstance) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(createString)) {
            preparedStatement.setString(1, newInstance.getName());
            if (preparedStatement.executeUpdate() > 0)
                connection.commit();
        }
    }

    @Override
    public Role read(Long id) throws SQLException {
        Role role = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(readString)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                role = new Role();
                role.setId(resultSet.getLong(RoleName.ID));
                role.setName(resultSet.getString(RoleName.NAME));
            }
        }
        return role;
    }

    @Override
    public void update(Role transientObject) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateString)) {
            preparedStatement.setLong(2, transientObject.getId());
            preparedStatement.setString(1, transientObject.getName());
            if(preparedStatement.executeUpdate() > 0)
                connection.commit();
        }
    }

    @Override
    public void delete(Role persistentObject) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteString)) {
            preparedStatement.setLong(1, persistentObject.getId());
            if(preparedStatement.executeUpdate() > 0)
                connection.commit();
        }
    }

    @Override
    public List<Role> findAll() throws SQLException {
        List<Role> roles = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(findAllString)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    Role role = new Role();
                    role.setId(resultSet.getLong(RoleName.ID));
                    role.setName(resultSet.getString(RoleName.NAME));
                    roles.add(role);
                }
            }
        }
        return roles;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, null, e);
        }
    }
}
