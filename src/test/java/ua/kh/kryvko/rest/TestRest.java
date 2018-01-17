package ua.kh.kryvko.rest;

import org.jmock.Mockery;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

public class TestRest {

//    @BeforeClass
//    public static void createTables() {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        try(InputStream inputStream = classLoader.getResourceAsStream("application.properties")) {
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            try {
//                Class.forName(properties.getProperty("db.driverClassName")).newInstance();
//            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            try(Connection connection = DriverManager.getConnection(properties.getProperty("db.url"))) {
//                try(Statement statement = connection.createStatement()) {
//                    try(BufferedReader sqlReader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("src/test/sql/h2Initialize.sql")))) {
//                        while(sqlReader.ready()) {
//                            statement.execute(sqlReader.readLine());
//                        }
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testRoleRest() {
        Mockery context = new Mockery();
        HttpServletRequest request = context.mock( HttpServletRequest.class );
        request.getParameter("ID");
    }
}
