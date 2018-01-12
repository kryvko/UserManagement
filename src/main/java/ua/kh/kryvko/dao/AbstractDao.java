package ua.kh.kryvko.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kh.kryvko.name.ResourceName;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.Properties;

public abstract class AbstractDao<T, PK extends Serializable> implements GenericDao<T, PK> {

    private final static ComboPooledDataSource DATA_SOURCE;
    private static final Logger LOGGER = Logger.getLogger("error");

    //Configuring connection pool
    static {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();

        DATA_SOURCE = new ComboPooledDataSource();
        try (InputStream resourceStream = classLoader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);

            DATA_SOURCE.setDriverClass(properties.getProperty("db.driverClassName"));
            DATA_SOURCE.setJdbcUrl(properties.getProperty("db.url"));
            DATA_SOURCE.setUser(properties.getProperty("db.user"));
            DATA_SOURCE.setPassword("db.password");

            try(InputStream databasePropertiesStream = classLoader.getResourceAsStream("database.properties")) {
                Properties databaseProperties = new Properties();
                databaseProperties.load(databasePropertiesStream);
                DATA_SOURCE.setProperties(databaseProperties);
            }


            DATA_SOURCE.setMaxStatements(Integer.parseInt(properties.getProperty("dbcp.maxStatements")));
            DATA_SOURCE.setMaxStatementsPerConnection(Integer.parseInt(properties.getProperty("dbcp.maxStatementsPerConnection")));
            DATA_SOURCE.setMinPoolSize(Integer.parseInt(properties.getProperty("dbcp.minPoolSize")));
            DATA_SOURCE.setAcquireIncrement(Integer.parseInt(properties.getProperty("dbcp.acquireIncrement")));
            DATA_SOURCE.setMaxPoolSize(Integer.parseInt(properties.getProperty("dbcp.maxPoolSize")));
            DATA_SOURCE.setMaxIdleTime(Integer.parseInt(properties.getProperty("dbcp.maxIdleTime")));
        } catch (PropertyVetoException | IOException e) {
            LOGGER.log(Level.ERROR, null, e);
        }

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
//            InitialContext initialContext = new InitialContext();
//            DataSource dataSource = (DataSource) initialContext.lookup(ResourceName.JNDI);
            connection = DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, null, e);
        }
        return connection;
    }
}
