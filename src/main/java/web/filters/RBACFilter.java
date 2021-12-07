package web.filters;

import mapper.UserMapper;
import util.ArrayHelpers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebFilter("/*")
public class RBACFilter implements Filter {

    private HashMap<String, String> restrictedPages;
    private HashMap<String, String> userPages;

    final String ADMIN = "Admin";
    final String CUSTOMER = "Customer";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        restrictedPages = new HashMap<>();
        restrictedPages.put("/admin", ADMIN);
        restrictedPages.put("/admin/orders", ADMIN);
        restrictedPages.put("/admin/add-balance", ADMIN);
        restrictedPages.put("/admin/remove-order", ADMIN);
        restrictedPages.put("/admin/remove-user", ADMIN);

        restrictedPages.put("/cart", CUSTOMER);
        restrictedPages.put("/cart/add-to-cart", CUSTOMER);
        restrictedPages.put("/cart/remove-from-cart", CUSTOMER);

        restrictedPages.put("/orders", CUSTOMER);
        restrictedPages.put("/orders/add", CUSTOMER);

        restrictedPages.put("/profile", CUSTOMER);
        restrictedPages.put("/confirm-order", CUSTOMER);
        restrictedPages.put("/shoppingcart", CUSTOMER);
        restrictedPages.put("/thank-you", CUSTOMER);
        restrictedPages.put("/view-orders", CUSTOMER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (!roleHasAccess(req)) {
            // Create a new GET request to the home page
            res.sendRedirect("/");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public boolean roleHasAccess(HttpServletRequest request) {
        UserMapper userMapper = new UserMapper();
        String role = userMapper.getUserRole(request.getSession().getId());

        if (role != null && role.equals(ADMIN)) return true;

        String servletPath = request.getServletPath();
        return roleHasAccess(role, servletPath);
    }

    private boolean roleHasAccess(String role, String servletPath) {
        if (servletPath.contains("/*")) {

            String neededRole = neededRoleForPath(servletPath);
            if (neededRole != null) {
                return neededRole.equals(role);
            }
        }
        if (!pageIsRestricted(servletPath)) {
            return true;
        }

        return restrictedPages.get(servletPath).equals(role);
    }

    private String neededRoleForPath(String path) {
        if (path.contains("/admin/")) {
            return ADMIN;
        }
        // todo: better name
        String[] adminSuperPaths = new String[] { "/cart/", "/orders/" };
        if (ArrayHelpers.stringInArray(path, adminSuperPaths)) {
            return CUSTOMER;
        }
        return null;
    }

    private boolean pageIsRestricted(String servletPath) {
        for (String path : restrictedPages.keySet()) {
            if (servletPath.equals(path)) {
                return true;
            }
        }
        return false;
    }
}