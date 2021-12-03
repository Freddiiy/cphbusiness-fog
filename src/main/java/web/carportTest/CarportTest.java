package web.carportTest;

import controller.CartController;
import controller.MaterialsController;
import controller.UserController;
import model.CartItems;
import model.Cupcake;
import model.Material;
import model.User;
import persistance.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Carport", urlPatterns = {"/Carport"})
public class CarportTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        MaterialsController materialsController = new MaterialsController(new Database());
        List materials = materialsController.getMaterials();

        materialsController.calcPriceFromMaterials(materials);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {



    }
}
