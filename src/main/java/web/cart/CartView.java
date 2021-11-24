package web.cart;

import controller.CartController;
import persistance.Database;
import controller.UserController;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Cart", urlPatterns = {"/cart"})
public class CartView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController(new Database());
        CartController cartController = new CartController(new Database());
        HttpSession session = request.getSession();

        if(!userController.validateSession(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String sessionID = request.getSession().getId();
            List cartList = cartController.getCart(sessionID);
            double totalPrice = cartController.totalPriceOfCart(sessionID);

            session.setAttribute("cartList", cartList);
            session.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}