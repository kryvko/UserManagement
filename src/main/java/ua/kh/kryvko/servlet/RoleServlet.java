package ua.kh.kryvko.servlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kh.kryvko.dao.RoleDao;
import ua.kh.kryvko.entity.Role;
import ua.kh.kryvko.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RoleServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("error");
    private static final String CONTENT_TYPE = "application/json";
    private static final int SC_UNPROCESSABLE_ENTITY = 422;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getContentType().equals(CONTENT_TYPE)) {
            try(RoleDao roleDao = new RoleDao()) {
                roleDao.create(JsonUtil.fromJson(request, Role.class));
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, null, e);
                response.setStatus(SC_UNPROCESSABLE_ENTITY);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(RoleDao roleDao = new RoleDao()) {
            String idParam = request.getParameter("ID");
            if( idParam != null) {
                JsonUtil.toJson(response, roleDao.read(Long.parseLong(idParam)));
            } else {
                JsonUtil.toJson(response, roleDao.findAll());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, null, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType().equals(CONTENT_TYPE)) {
            try(RoleDao roleDao = new RoleDao()) {
                roleDao.update(JsonUtil.fromJson(req, Role.class));
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, null, e);
                resp.setStatus(SC_UNPROCESSABLE_ENTITY);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType().equals(CONTENT_TYPE)) {
            try(RoleDao roleDao = new RoleDao()) {
                String idParam = req.getParameter("ID");
                if(idParam != null) {
                    roleDao.delete(Long.parseLong(idParam));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, null, e);
                resp.setStatus(SC_UNPROCESSABLE_ENTITY);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
    }
}
