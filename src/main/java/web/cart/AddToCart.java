package web.cart;

import controller.CartController;
import model.CartItems;
import model.Cupcake;
import persistance.Database;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AddToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bottom = request.getParameter("bottom");
        String topping = request.getParameter("topping");
        int amount = Integer.parseInt(request.getParameter("amount"));

        HttpSession session = request.getSession();
        String sessionID = session.getId();

        Cupcake cupcake = new Cupcake(bottom, topping);

        CartItems cartItems = new CartItems(cupcake.getBottom(), cupcake.getTopping(), amount);
        CartController cartController = new CartController(new Database());
        cartController.addToCart(cartItems, sessionID);

        response.sendRedirect("/shop");

    }
}