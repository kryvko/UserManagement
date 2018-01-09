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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDao<T, PK extends Serializable> implements GenericDao<T, PK> {

    private final static ComboPooledDataSource DATA_SOURCE;
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

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

            DATA_SOURCE.setProperties(properties);

            DATA_SOURCE.setMaxStatements(Integer.getInteger(properties.getProperty("dbcp.maxStatements")));
            DATA_SOURCE.setMaxStatementsPerConnection(Integer.getInteger(properties.getProperty("dbcp.maxStatementsPerConnection")));
            DATA_SOURCE.setMinPoolSize(Integer.getInteger(properties.getProperty("dbcp.maxPoolSize")));
            DATA_SOURCE.setAcquireIncrement(Integer.getInteger(properties.getProperty("dbcp.acquireIncrement")));
            DATA_SOURCE.setMaxPoolSize(Integer.getInteger(properties.getProperty("dbcp.maxPoolSize")));
            DATA_SOURCE.setMaxIdleTime(Integer.getInteger(properties.getProperty("dbcp.maxIdleTime")));
        } catch (PropertyVetoException | IOException e) {
            LOGGER.log(Level.ERROR, null, e);
        }
    }

    public PK create(T newInstance) {
        return null;
    }

    public T read(PK id) {
        return null;
    }

    public void update(T transientObject) {

    }

    public void delete(T persistentObject) {

    }

    public List<T> findAll() {
        return null;
    }

    private Connection getConnetction() {
        Connection connection = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(ResourceName.JNDI);
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.log(Level.ERROR, null, e);
        }
        return connection;
    }
}
