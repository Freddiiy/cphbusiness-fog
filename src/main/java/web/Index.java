package web;

import io.github.cdimascio.dotenv.Dotenv;
import persistance.Database;
import util.Carport.CarportCalc;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Index", urlPatterns = {""})
public class Index extends HttpServlet {

    Dotenv dotenv = Dotenv.configure().load();

    public final String USER = dotenv.get("USER");
    public final String PASSWORD = dotenv.get("PASSWORD");
    public final String URL = dotenv.get("URL");

    public static Database database;

    @Override
    public void init() {
        if (database == null) {
            database = new Database(USER, PASSWORD, URL);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
    }
}