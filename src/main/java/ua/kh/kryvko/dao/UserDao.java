package ua.kh.kryvko.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kh.kryvko.entity.User;
import ua.kh.kryvko.name.UserName;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User, Long> implements Closeable {

    private static final Logger LOGGER = Logger.getLogger("error");

    private static final String createString = "INSERT INTO " + UserName.TABLE
            + " (" + UserName.LOGIN + ", " + UserName.PASSWORD + ", " + UserName.EMAIL + ", "
            + UserName.FIRST_NAME + ", " + UserName.LAST_NAME + ", " + UserName.ROLE_ID+ ") VALUES (?, ?, ?, ?, ?, ?);";
    private static final String readString = "SELECT * FROM " + UserName.TABLE + " WHERE " + UserName.ID + "=?;";
    private static final String updateString = "UPDATE " + UserName.TABLE + " SET " + UserName.LOGIN + "=?, "
            + UserName.PASSWORD + "=?, " + UserName.EMAIL + "=?, " + UserName.FIRST_NAME + "=?, " + UserName.LAST_NAME
            + "=?, " + UserName.ROLE_ID + "=? WHERE " + UserName.ID + "=?;";
    private static final String deleteString = "DELETE FROM " + UserName.TABLE + " WHERE " + UserName.ID + "=?";
    private static final String findAllString = "SELECT * FROM " + UserName.TABLE;

    private final Connection connection;

    public UserDao() throws SQLException {
        this.connection = getConnection();
        connection.setAutoCommit(false);
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, null, e);
        }
    }

    @Override
    public void create(User newInstance) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(createString)) {
            preparedStatement.setString(1, newInstance.getLogin());
            preparedStatement.setString(2, newInstance.getPassword());
            preparedStatement.setString(3, newInstance.getEmail());
            preparedStatement.setString(4, newInstance.getFirstName());
            preparedStatement.setString(5, newInstance.getLastName());
            preparedStatement.setLong(6, newInstance.getRole().getId());
            if(preparedStatement.executeUpdate() > 0)
                connection.commit();
        }
    }

    @Override
    public User read(Long id) throws SQLException {
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(readString)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong(UserName.ID));
                    user.setLogin(resultSet.getString(UserName.LOGIN));
                    user.setPassword(resultSet.getString(UserName.PASSWORD));
                    user.setEmail(resultSet.getString(UserName.EMAIL));
                    user.setFirstName(resultSet.getString(UserName.FIRST_NAME));
                    user.setLastName(resultSet.getString(UserName.LAST_NAME));
                    user.setRole(new RoleDao().read(resultSet.getLong(UserName.ROLE_ID)));
                }
            }
        }
        return user;
    }

    @Override
    public void update(User transientObject) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateString)) {
            preparedStatement.setString(1, transientObject.getLogin());
            preparedStatement.setString(2, transientObject.getPassword());
            preparedStatement.setString(3, transientObject.getEmail());
            preparedStatement.setString(4, transientObject.getFirstName());
            preparedStatement.setString(5, transientObject.getLastName());
            preparedStatement.setLong(6, transientObject.getRole().getId());
            preparedStatement.setLong(7, transientObject.getId());
            if(preparedStatement.executeUpdate() > 0)
                connection.commit();
        }

    }

    @Override
    public void delete(Long id) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteString)) {
            preparedStatement.setLong(1, id);
            if(preparedStatement.executeUpdate() > 0)
                connection.commit();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(findAllString)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(UserName.ID));
                    user.setLogin(resultSet.getString(UserName.LOGIN));
                    user.setPassword(resultSet.getString(UserName.PASSWORD));
                    user.setEmail(resultSet.getString(UserName.EMAIL));
                    user.setFirstName(resultSet.getString(UserName.FIRST_NAME));
                    user.setLastName(resultSet.getString(UserName.LAST_NAME));
                    user.setRole(new RoleDao().read(resultSet.getLong(UserName.ROLE_ID)));
                    users.add(user);
                }
            }
        }
        return users;
    }
}
