package ua.kh.kryvko.util;

import com.google.gson.Gson;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtil {

    public static ServletResponse toJson(ServletResponse responce, Object o) throws IOException {
        responce.setContentType("application/json");
        responce.setCharacterEncoding("UTF-8");
        try(PrintWriter writer = responce.getWriter()) {
            writer.write(new Gson().toJson(o));
            writer.flush();
        }
        return responce;
    }

    private JsonUtil() {
    }
}
