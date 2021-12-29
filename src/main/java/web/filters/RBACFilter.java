package web.filters;

import persistance.UserMapper;
import util.ArrayHelpers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * A filter for role-based access control.
 * Any page that requires a certain role must be put
 * inside restrictedPages.
 * ADMIN has access to all the pages that CUSTOMER has access to.
 * For unrestricted pages, do nothing.
 */
@WebFilter("/*")
public class RBACFilter implements Filter {

    private HashMap<String, String> restrictedPages;

    final String ADMIN = "Admin";
    final String CUSTOMER = "Customer";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        restrictedPages = new HashMap<>();
        restrictedPages.put("/admin", ADMIN);
        restrictedPages.put("/admin/order", ADMIN);
        restrictedPages.put("/admin/order/update", ADMIN);
        restrictedPages.put("/admin/order/accept", ADMIN);
        restrictedPages.put("/admin/order/reject", ADMIN);
        restrictedPages.put("/admin/remove-user", ADMIN);
        restrictedPages.put("/admin/users", ADMIN);
        restrictedPages.put("/admin/users/user", ADMIN);

        restrictedPages.put("/orders", CUSTOMER);
        restrictedPages.put("/orders/orderId", CUSTOMER);
        restrictedPages.put("/orders/add", CUSTOMER);
        restrictedPages.put("/orders/remove", CUSTOMER);
        restrictedPages.put("/orders/pay", CUSTOMER);

        restrictedPages.put("/profile", CUSTOMER);
        restrictedPages.put("/profile/update", CUSTOMER);
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