package ua.kh.kryvko.servlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kh.kryvko.dao.RoleDao;
import ua.kh.kryvko.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Roles extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("error");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(RoleDao roleDao = new RoleDao()) {
            JsonUtil.toJson(response, roleDao.findAll());
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, null, e);
        }
    }
}
